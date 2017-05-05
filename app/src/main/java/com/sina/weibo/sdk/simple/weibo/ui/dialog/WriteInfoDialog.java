package com.sina.weibo.sdk.simple.weibo.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.CommentPresenter;
import com.sina.weibo.sdk.simple.weibo.presenter.UpdateWeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.CommentInfoView;
import com.sina.weibo.sdk.simple.weibo.view.UpdateWeiboInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by John on 2017/4/8.
 * 写信息Dialog
 */

public class WriteInfoDialog extends DialogFragment {
    Unbinder unbinder;
    @BindView(R.id.laytout_write_weibo_title)
    TextView mLaytoutWriteWeiboTitle;
    @BindView(R.id.layout_write_weibo_send)
    TextView mLayoutWriteWeiboSend;
    @BindView(R.id.layout_write_weibo_body)
    EditText mLayoutWriteWeiboBody;
    @BindView(R.id.layout_write_weibo_word_count)
    TextView mLayoutWriteWeiboWordCount;

    private Oauth2AccessToken mAccessToken;
    private Context mContext;
    private UpdateWeiboInfoPresenter mUpdateWeiboInfoPresenter;
    private String mSendType;
    private long mWeiboId;
    private CommentPresenter mCommentPresenter;
    private final static String SEND_TYPE = "send_type";
    private final static String WEIBO_ID = "weibo_id";
    private CommentFinishedCallback mCommentFinishedCallback;

    public WriteInfoDialog() {
    }

    public static WriteInfoDialog newWriteInfoDialog(String sendType, long weiboId) {
        WriteInfoDialog writeInfoDialog = new WriteInfoDialog();
        Bundle args = new Bundle();
        args.putString(SEND_TYPE, sendType);
        args.putLong(WEIBO_ID, weiboId);
        writeInfoDialog.setArguments(args);
        return writeInfoDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        mSendType = args.getString(SEND_TYPE);
        mWeiboId = args.getLong(WEIBO_ID, -1);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(getActivity(), R.layout.layout_write_weibo, null);

        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();

        mAccessToken = AccessTokenKeeper.readAccessToken(getActivity());
        mLayoutWriteWeiboBody.setCursorVisible(false);
        mLayoutWriteWeiboBody.setFilters(new InputFilter[]{new InputFilter.LengthFilter(140)});
        mLayoutWriteWeiboBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mLayoutWriteWeiboWordCount.setTextColor(Color.GRAY);
                } else {
                    mLayoutWriteWeiboWordCount.setTextColor(Color.RED);
                }
                mLayoutWriteWeiboWordCount.setText(s.length() + "/" + 140);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (mSendType.equals(Tools.WRITE_WEIBO)) {
            mUpdateWeiboInfoPresenter = new UpdateWeiboInfoPresenter(mContext);
            mUpdateWeiboInfoPresenter.onCreate();
        } else if (mSendType.equals(Tools.WRITE_COMMENT)) {
            mLaytoutWriteWeiboTitle.setText("写评论");
            mCommentPresenter = new CommentPresenter(mContext);
            mCommentPresenter.onCreate();
        }


        return view;
    }


    public interface CommentFinishedCallback {
        void success(long weiboId);

        void failure();
    }

    public void addCommentFinishedCallback(CommentFinishedCallback callback) {
        mCommentFinishedCallback = callback;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mUpdateWeiboInfoPresenter != null) {
            mUpdateWeiboInfoPresenter.onStop();
        }
        if (mCommentPresenter != null) {
            mCommentPresenter.onStop();
        }
    }

    @OnClick({R.id.layout_write_weibo_send, R.id.layout_write_weibo_body})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_write_weibo_send:
                if (mAccessToken.isSessionValid()) {
                    String status = mLayoutWriteWeiboBody.getText().toString();
                    if (!TextUtils.isEmpty(status)) {
                        switch (mSendType) {
                            case Tools.WRITE_WEIBO:
                                sendWeibo(mAccessToken, status);
                                break;
                            case Tools.WRITE_COMMENT:
                                sendComment(mAccessToken, status, mWeiboId);
                                break;
                        }
                    }
                }
                break;

            case R.id.layout_write_weibo_body:
                mLayoutWriteWeiboBody.setCursorVisible(true);
                break;
        }
    }

    /**
     * 为微博评论
     *
     * @param accessToken
     * @param status
     * @param weiboId
     */

    private void sendComment(Oauth2AccessToken accessToken, String status, long weiboId) {
        mCommentPresenter.sendCommentForWeibo(accessToken, status, weiboId);
        mCommentPresenter.onAttachView(new CommentInfoView() {
            @Override
            public void onSuccess(CommonComment commonComment) {

            }

            @Override
            public void onSuccess(String commonComment) {
                ToastUtil.showToasts(mContext, "评论成功");
                if (mCommentFinishedCallback != null) {
                    mCommentFinishedCallback.success(mWeiboId);
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                ToastUtil.showToasts(mContext, "评论失败");

                if (mCommentFinishedCallback != null) {
                    mCommentFinishedCallback.failure();
                }
            }
        });
        dismiss();
    }


    /**
     * 发微博
     *
     * @param accessToken
     * @param status
     */
    private void sendWeibo(Oauth2AccessToken accessToken, String status) {
        mUpdateWeiboInfoPresenter.sendWeibo(accessToken, status);
        mUpdateWeiboInfoPresenter.onAttachView(new UpdateWeiboInfoView() {
            @Override
            public void onSuccess(UpdateWeiboInfo updateWeiboInfo) {
                ToastUtil.showToasts(mContext, "微博发送成功");
                if (mCommentFinishedCallback != null) {
                    mCommentFinishedCallback.success(mWeiboId);
                }
            }

            @Override
            public void onSuccess(String weiboInfo) {

            }

            @Override
            public void onFailure(String errorMsg) {
                ToastUtil.showToasts(mContext, "微博发送失败");
                if (mCommentFinishedCallback != null) {
                    mCommentFinishedCallback.failure();
                }
            }
        });
        dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
