package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.WeiboAdapter;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.WeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
import com.sina.weibo.sdk.simple.weibo.ui.view.RefreshHeaderView;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 用自己所发的微博信息
 */
public class UserTimeLineActivity extends AppCompatActivity {


    private static final int WEIBO_COUNT = 10;
    private static int sWeiboPage = 0;
    @BindView(R.id.title_bar_title)
    TextView mTitleBarTitle;

    @BindView(R.id.title_bar_write_image_view)
    ImageView mTitleBarWriteImageView;
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


    private WeiboInfoPresenter mWeiboInfoPresenter;
    private Oauth2AccessToken mAccessToken;
    private List<WeiboInfo> mWeibos;
    private WeiboAdapter mWeiboAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_time_line);
        ButterKnife.bind(this);

        mWeiboInfoPresenter = new WeiboInfoPresenter(this);
        mWeiboInfoPresenter.onCreate();

        mWeibos = new ArrayList<>();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mSwipeTarget.setLayoutManager(mLinearLayoutManager);
        mWeiboAdapter = new WeiboAdapter(this, mWeibos);
        mSwipeTarget.setAdapter(mWeiboAdapter);


        mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData(mSwipeToLoadLayout);
            }
        });

        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(mSwipeToLoadLayout);
            }
        });


        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mAccessToken.isSessionValid()) {
            mSwipeToLoadLayout.setRefreshing(true);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeiboInfoPresenter.onStop();
    }




    /**
     * 下拉加载更多数据
     *
     * @param swipeToLoadLayout
     */
    private void loadMoreData(final SwipeToLoadLayout swipeToLoadLayout) {
        sWeiboPage++;
        mWeiboInfoPresenter.getUserTimeLineInfo(mAccessToken, WEIBO_COUNT, sWeiboPage);
        mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
            @Override
            public void onSuccess(List<WeiboInfo> weibos) {
                int size = weibos.size();
                if (size > 0) {
                    mWeibos.addAll(weibos);
                    mWeiboAdapter.notifyDataSetChanged();
                    Tools.MoveToPosition(mLinearLayoutManager, mSwipeTarget);
                } else {
                    hint();
                }
                swipeToLoadLayout.setLoadingMore(false);
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.d(PublicTimeLineActivity.TAG, errorMsg + "---errorMsg");
            }
        });
    }


    /**
     * 上拉刷新数据
     *
     * @param swipeToLoadLayout
     */
    private void refreshData(final SwipeToLoadLayout swipeToLoadLayout) {
        Log.d(PublicTimeLineActivity.TAG, "hello world ");
        sWeiboPage = 1;
        mWeiboInfoPresenter.getUserTimeLineInfo(mAccessToken, WEIBO_COUNT, sWeiboPage);
        mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
            @Override
            public void onSuccess(List<WeiboInfo> weibos) {
                mWeibos.clear();
                mWeibos.addAll(weibos);
                mWeiboAdapter.notifyDataSetChanged();
                Tools.showEmptyView(mEmptyView, mSwipeToLoadLayout, mSwipeTarget);
                swipeToLoadLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.d(PublicTimeLineActivity.TAG, errorMsg + "---errorMsg");
            }
        });
    }


    /**
     * 数据提示
     */
    private void hint() {
        ToastUtil.showToasts(UserTimeLineActivity.this, "没有数据了");
    }
}
