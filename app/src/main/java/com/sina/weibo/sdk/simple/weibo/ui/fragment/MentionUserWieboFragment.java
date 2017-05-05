package com.sina.weibo.sdk.simple.weibo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.WeiboAdapter;
import com.sina.weibo.sdk.simple.weibo.event.CommentEvent;
import com.sina.weibo.sdk.simple.weibo.event.CommentFinishedEvent;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.WeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.activity.PublicTimeLineActivity;
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
import com.sina.weibo.sdk.simple.weibo.ui.view.RefreshHeaderView;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * \@ 我的微博
 */
public class MentionUserWieboFragment extends Fragment {


    Unbinder unbinder;
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


    private List<WeiboInfo> mWeibos;
    private WeiboAdapter mWeiboAdapter;
    private static final int WEIBO_COUNT = 10;
    private static int sWeiboPage = 0;
    private LinearLayoutManager mLinearLayoutManager;

    private WeiboInfoPresenter mWeiboInfoPresenter;
    private Oauth2AccessToken mAccessToken;

    public MentionUserWieboFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mention_user, container, false);
        unbinder = ButterKnife.bind(this, view);

        //加载数据
        initData();

        //加载监听
        initListener();

        return view;
    }


    /**
     * 初始化监听
     */
    private void initListener() {
        //加载更多
        mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                LoadMoreData(mSwipeToLoadLayout);
            }
        });

        //上拉刷新
        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(mSwipeToLoadLayout);
            }
        });
    }


    /**
     * 初始化数据
     */
    private void initData() {
        mWeibos = new ArrayList<>();
        mWeiboAdapter = new WeiboAdapter(getActivity(), mWeibos);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mSwipeTarget.setLayoutManager(mLinearLayoutManager);
        mSwipeTarget.setAdapter(mWeiboAdapter);


        mWeiboInfoPresenter = new WeiboInfoPresenter(getActivity());
        mWeiboInfoPresenter.onCreate();

        mAccessToken = AccessTokenKeeper.readAccessToken(getActivity());


        if (mAccessToken.isSessionValid()) {
            mSwipeToLoadLayout.setRefreshing(true);
        }
    }

    /**
     * 向下加载跟多数据
     *
     * @param swipeToLoadLayout
     */
    private void LoadMoreData(final SwipeToLoadLayout swipeToLoadLayout) {
        sWeiboPage++;
        mWeiboInfoPresenter.getMentionUserWeiboInfo(mAccessToken, WEIBO_COUNT, sWeiboPage);
        mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
            @Override
            public void onSuccess(final List<WeiboInfo> weibos) {
                Tools.decideData(getActivity(), weibos, new Tools.LoadMoreCallback() {
                    @Override
                    public void loadMoreData() {
                        mWeibos.addAll(weibos);
                        mWeiboAdapter.notifyDataSetChanged();
                        Tools.MoveToPosition(mLinearLayoutManager, mSwipeTarget);
                    }
                });

                swipeToLoadLayout.setLoadingMore(false);
            }

            @Override
            public void onFailure(String errorMsg) {
                Logger.d(errorMsg);
            }
        });

    }


    /**
     * 下拉刷新数据
     *
     * @param swipeToLoadLayout
     */
    private void refreshData(final SwipeToLoadLayout swipeToLoadLayout) {
        sWeiboPage = 1;
        mWeiboInfoPresenter.getMentionUserWeiboInfo(mAccessToken, WEIBO_COUNT, sWeiboPage);
        mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
            @Override
            public void onSuccess(final List<WeiboInfo> weibos) {

                Tools.decideData(getActivity(), weibos, new Tools.LoadMoreCallback() {
                    @Override
                    public void loadMoreData() {
                        mWeibos.clear();
                        mWeibos.addAll(weibos);
                        mWeiboAdapter.notifyDataSetChanged();
                        Tools.showEmptyView(mEmptyView, swipeToLoadLayout, mSwipeTarget);
                    }
                });

                swipeToLoadLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorMsg) {
                Logger.d(errorMsg);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mWeiboInfoPresenter.onStop();
        unbinder.unbind();
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onCommentEvent(CommentFinishedEvent commentEvent) {
        int index = mWeibos.indexOf(commentEvent.getWeiboInfo());
        long count = commentEvent.getWeiboInfo().getComment() + 1;
        mWeibos.get(index).setComment(count);
        mWeiboAdapter.notifyItemChanged(index);
    }
}
