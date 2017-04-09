package com.sina.weibo.sdk.simple.weibo.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.UpdateWeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.presenter.WeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.activity.PublicTimeLineActivity;
import com.sina.weibo.sdk.simple.weibo.util.HttpUtil;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.view.UpdateWeiboInfoView;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by John on 2017/4/8.
 */

public class WriteWeiboDialog extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.layout_write_weibo_send)
    TextView mLayoutWriteWeiboSend;
    @BindView(R.id.layout_write_weibo_body)
    EditText mLayoutWriteWeiboBody;
    @BindView(R.id.layout_write_weibo_word_count)
    TextView mLayoutWriteWeiboWordCount;

    private Oauth2AccessToken mAccessToken;
    private Context mContext;
//    private UpdateWeiboInfoPresenter mUpdateWeiboInfoPresenter;

    public WriteWeiboDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(getActivity(), R.layout.layout_write_weibo, null);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
//        mUpdateWeiboInfoPresenter = new UpdateWeiboInfoPresenter(getActivity());
//        mUpdateWeiboInfoPresenter.onCreate();

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


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
//        mUpdateWeiboInfoPresenter.onStop();
    }

    @OnClick({R.id.layout_write_weibo_send, R.id.layout_write_weibo_body})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_write_weibo_send:
                if (mAccessToken.isSessionValid()) {
                    String status = mLayoutWriteWeiboBody.getText().toString();
                    if (!TextUtils.isEmpty(status)) {
                        onSendWeibo(mAccessToken, status);
                    }
                }
                break;
            case R.id.layout_write_weibo_body:
                mLayoutWriteWeiboBody.setCursorVisible(true);
                break;
        }
    }

    /**
     * 发送微博信息
     */
    private void onSendWeibo(Oauth2AccessToken accessToken, String status) {
        sendWeibo(accessToken, status);
        dismiss();
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ToastUtil.showToasts(mContext, "微博发送失败");
                    break;
                case 1:
                    ToastUtil.showToasts(mContext, "微博发送成功");
                    break;
            }
        }
    };

    private void sendWeibo(Oauth2AccessToken accessToken, String status) {
        HttpUtil.sendWeibo(accessToken, status, new HttpUtil.HttpCallback() {
            @Override
            public void onSuccess(final String msg) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(String msg) {
                mHandler.sendEmptyMessage(0);
            }
        });
    }

    @OnClick(R.id.layout_write_weibo_send)
    public void onClick() {
    }
}
