package com.sina.weibo.sdk.simple.weibo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.CommentAdapter;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.presenter.CommentPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.dialog.WriteInfoDialog;
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
import com.sina.weibo.sdk.simple.weibo.ui.view.RefreshHeaderView;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.CommentInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 获取某条微博评论
 */
public class CommentFragment extends Fragment {
    private static final String TAG = "CommentFragment";
    @BindView(R.id.title_bar_title)
    TextView mTitleBarTitle;
    @BindView(R.id.title_bar_write_image_view)
    ImageView mTitleBarWriteImageView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView mSwipeTarget;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.empty_view)
    RelativeLayout mEmptyView;

    Unbinder unbinder;

    private static final String WEIBO_ID = "weibo_id";

    private long mWeiboId;
    private Context mContext;
    private CommentPresenter mCommentPresenter;
    private Oauth2AccessToken mAccessToken;
    private static final int COMMENT_COUNT = 10;
    private static int sCommentPage = 0;
    private List<CommonComment.CommentsBean> mCommentsBeanList;
    private LinearLayoutManager mLinearLayoutManager;
    private CommentAdapter mCommentAdapter;

    public static CommentFragment newCommentFragment(long weiboId) {
        CommentFragment commentFragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putLong(WEIBO_ID, weiboId);
        commentFragment.setArguments(args);
        return commentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_comment, null);
        unbinder = ButterKnife.bind(this, view);
        Bundle args = getArguments();
        mWeiboId = args.getLong(WEIBO_ID, -1);
        mContext = getActivity();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mCommentPresenter = new CommentPresenter(getActivity());
        mCommentPresenter.onCreate();

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mSwipeTarget.setLayoutManager(mLinearLayoutManager);
        mCommentsBeanList = new ArrayList<>();
        mCommentAdapter = new CommentAdapter(getActivity(), mCommentsBeanList);
        mSwipeTarget.setAdapter(mCommentAdapter);

        mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                LoadMoreData(mSwipeToLoadLayout);
            }
        });

        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(mSwipeToLoadLayout);
            }
        });

        mAccessToken = AccessTokenKeeper.readAccessToken(mContext);
        if (mAccessToken.isSessionValid()) {
            mSwipeToLoadLayout.setRefreshing(true);
        }

        return view;
    }


    /**
     * 向下加载跟多数据
     *
     * @param swipeToLoadLayout
     */
    private void LoadMoreData(final SwipeToLoadLayout swipeToLoadLayout) {
        sCommentPage++;
        mCommentPresenter.getWeiboComment(mAccessToken, mWeiboId, COMMENT_COUNT, sCommentPage);
        mCommentPresenter.onAttachView(new CommentInfoView() {
            @Override
            public void onSuccess(CommonComment commonComment) {
                List<CommonComment.CommentsBean> data = commonComment.getComments();
                if (data.size() > 0) {
                    mCommentsBeanList.addAll(data);
                    mCommentAdapter.notifyDataSetChanged();
                    Tools.MoveToPosition(mLinearLayoutManager, mSwipeTarget);
                } else {
                    ToastUtil.showToasts(getActivity(), "没有数据了");
                }
                swipeToLoadLayout.setLoadingMore(false);
            }

            @Override
            public void onSuccess(String commonComment) {

            }

            @Override
            public void onFailure(String errorMsg) {
                Logger.d(errorMsg);
            }
        });

    }


    /**
     * 下拉刷新数据
     */
    private void refreshData(final SwipeToLoadLayout swipeToLoadLayout) {
        sCommentPage = 1;
        mCommentPresenter.getWeiboComment(mAccessToken, mWeiboId, COMMENT_COUNT, sCommentPage);
        mCommentPresenter.onAttachView(new CommentInfoView() {
            @Override
            public void onSuccess(CommonComment commonComment) {
                mCommentsBeanList.clear();
                mCommentsBeanList.addAll(commonComment.getComments());
                mCommentAdapter.notifyDataSetChanged();
                Tools.showEmptyView(mEmptyView, mSwipeToLoadLayout, mSwipeTarget);
                swipeToLoadLayout.setRefreshing(false);
            }

            @Override
            public void onSuccess(String commonComment) {

            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mCommentPresenter.onStop();
    }


    @OnClick(R.id.title_bar_write_image_view)
    public void onClick() {
        Tools.openWriteComment(getActivity().getSupportFragmentManager(), TAG, mWeiboId)
                .addCommentFinishedCallback(new WriteInfoDialog.CommentFinishedCallback() {
                    @Override
                    public void success(long weiboId) {
                        mSwipeToLoadLayout.setRefreshing(true);
                    }

                    @Override
                    public void failure() {

                    }
                });
    }
}
