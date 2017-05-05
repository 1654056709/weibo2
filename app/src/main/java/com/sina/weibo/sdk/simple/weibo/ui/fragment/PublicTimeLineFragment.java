package com.sina.weibo.sdk.simple.weibo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.sina.weibo.sdk.simple.weibo.adapter.WeiboAdapter;
import com.sina.weibo.sdk.simple.weibo.event.CommentEvent;
import com.sina.weibo.sdk.simple.weibo.event.CommentFinishedEvent;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.WeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.activity.PublicTimeLineActivity;
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
import com.sina.weibo.sdk.simple.weibo.ui.view.RefreshHeaderView;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 公共微博信息
 */
public class PublicTimeLineFragment extends Fragment {

    Unbinder unbinder;
    private static int sCount = 10;
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

    private List<WeiboInfo> mWeiboInfos;
    private Oauth2AccessToken mAccessToken;
    private WeiboInfoPresenter mWeiboInfoPresenter;
    private WeiboAdapter mWeiboAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public PublicTimeLineFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_public_time_line, null);
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
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });

        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }


    /**
     * 初始化数据
     */
    private void initData() {
        mTitleBarTitle.setText(getResources().getString(R.string.public_weibo));
        mTitleBarWriteImageView.setVisibility(View.GONE);


        mWeiboInfos = new ArrayList<>();
        mWeiboAdapter = new WeiboAdapter(getActivity(), mWeiboInfos);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mWeiboInfoPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 加载更多
     */
    private void loadMoreData() {
        sCount += 10;
        mWeiboInfoPresenter.getPublicTimeLineWeiboInfo(mAccessToken, sCount);
        mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
            @Override
            public void onSuccess(List<WeiboInfo> weibos) {
                int size = weibos.size();
                if (size > 0) {
                    mWeiboInfos.clear();
                    mWeiboInfos.addAll(weibos);
                    mWeiboAdapter.notifyDataSetChanged();
                    Tools.MoveToPosition(mLinearLayoutManager, mSwipeTarget);
                } else {
                    ToastUtil.showToasts(getActivity(), "没有数据了");
                }
                mSwipeToLoadLayout.setLoadingMore(false);
            }

            @Override
            public void onFailure(String errorMsg) {
                Logger.d(errorMsg);
            }
        });
    }


    /**
     * 上拉刷新数据
     */
    private void refreshData() {
        sCount = 10;
        mWeiboInfoPresenter.getPublicTimeLineWeiboInfo(mAccessToken, sCount);
        mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
            @Override
            public void onSuccess(List<WeiboInfo> weibos) {
                mWeiboInfos.clear();
                mWeiboInfos.addAll(weibos);
                mWeiboAdapter.notifyDataSetChanged();
                Tools.showEmptyView(mEmptyView, mSwipeToLoadLayout, mSwipeTarget);
                mSwipeToLoadLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.d(PublicTimeLineActivity.TAG, errorMsg + "---errorMsg");
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onCommentEvent(CommentFinishedEvent commentEvent) {
        int index = mWeiboInfos.indexOf(commentEvent.getWeiboInfo());
        long count = commentEvent.getWeiboInfo().getComment() + 1;
        mWeiboInfos.get(index).setComment(count);
        mWeiboAdapter.notifyItemChanged(index);
    }

}
