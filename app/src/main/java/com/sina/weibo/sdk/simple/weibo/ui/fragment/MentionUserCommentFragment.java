package com.sina.weibo.sdk.simple.weibo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.CommentAdapter;
import com.sina.weibo.sdk.simple.weibo.event.CommentEvent;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.presenter.CommentPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
import com.sina.weibo.sdk.simple.weibo.ui.view.RefreshHeaderView;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.CommentInfoView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * \@ 我的评论
 */
public class MentionUserCommentFragment extends Fragment {
    Unbinder unbinder;
    private static final int COMMENT_COUNT = 10;
    private static int sCommentPage = 0;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView mSwipeTarget;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.iv_empty)
    ImageView mIvEmpty;
    @BindView(R.id.empty_view)
    RelativeLayout mEmptyView;
    private LinearLayoutManager mLinearLayoutManager;
    private Oauth2AccessToken mAccessToken;
    private CommentPresenter mCommentPresenter;
    private List<CommonComment.CommentsBean> mCommentsBeanList;
    private CommentAdapter mCommentAdapter;


    public MentionUserCommentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mention_user_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
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

        mAccessToken = AccessTokenKeeper.readAccessToken(getActivity());
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
        mCommentPresenter.getMentionUserComment(mAccessToken, COMMENT_COUNT, sCommentPage);
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
        mCommentPresenter.getMentionUserComment(mAccessToken, COMMENT_COUNT, sCommentPage);
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
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCommentPresenter.onStop();
        unbinder.unbind();
    }
}
