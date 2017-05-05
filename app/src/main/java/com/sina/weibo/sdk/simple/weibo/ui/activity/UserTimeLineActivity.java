package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
import com.sina.weibo.sdk.simple.weibo.ui.view.RefreshHeaderView;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.ManagerFactoryParameters;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 个人微博
 */
public class UserTimeLineActivity extends AppCompatActivity {
    public static final String DELETE = "UserTimeLineActivity_delete";
    public static final String CANCEL = "UserTimeLineActivity_cancel";

    private static final int WEIBO_COUNT = 10;
    private static int sWeiboPage = 0;
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
    @BindView(R.id.iv_empty)
    ImageView mIvEmpty;
    @BindView(R.id.empty_view)
    RelativeLayout mEmptyView;

    private WeiboInfoPresenter mWeiboInfoPresenter;
    private Oauth2AccessToken mAccessToken;
    private static List<WeiboInfo> sWeiboInfos;
    private static WeiboAdapter sWeiboAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    public static Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            WeiboInfo weiboInfo = (WeiboInfo) msg.obj;
            int index = sWeiboInfos.indexOf(weiboInfo);
            sWeiboInfos.remove(index);
            sWeiboAdapter.notifyItemRemoved(index);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_user_time_line);
        ButterKnife.bind(this);

        //加载数据
        initData();
        //记载监听
        initListener();


    }

    /**
     * 初始化监听
     */
    private void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        mTitleBarTitle.setText(getResources().getString(R.string.personal_weibo));

        mWeiboInfoPresenter = new WeiboInfoPresenter(this);
        mWeiboInfoPresenter.onCreate();

        sWeiboInfos = new ArrayList<>();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mSwipeTarget.setLayoutManager(mLinearLayoutManager);
        sWeiboAdapter = new WeiboAdapter(this, sWeiboInfos);
        mSwipeTarget.setAdapter(sWeiboAdapter);

        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mAccessToken.isSessionValid()) {
            mSwipeToLoadLayout.setRefreshing(true);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeiboInfoPresenter.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onCommentEvent(CommentFinishedEvent commentEvent) {
        Logger.d("UserTimeLine");
        int index = sWeiboInfos.indexOf(commentEvent.getWeiboInfo());
        long count = commentEvent.getWeiboInfo().getComment() + 1;
        sWeiboInfos.get(index).setComment(count);
        sWeiboAdapter.notifyItemChanged(index);
    }

    /**
     * 下拉加载更多数据
     */
    private void loadMoreData() {
        sWeiboPage++;
        mWeiboInfoPresenter.getUserTimeLineInfo(mAccessToken, WEIBO_COUNT, sWeiboPage);
        mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
            @Override
            public void onSuccess(List<WeiboInfo> weibos) {
                int size = weibos.size();
                if (size > 0) {
                    sWeiboInfos.addAll(weibos);
                    sWeiboAdapter.notifyDataSetChanged();
                    Tools.MoveToPosition(mLinearLayoutManager, mSwipeTarget);
                } else {
                    hint();
                }
                mSwipeToLoadLayout.setLoadingMore(false);
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.d(PublicTimeLineActivity.TAG, errorMsg + "---errorMsg");
            }
        });
    }


    /**
     * 上拉刷新数据
     */
    private void refreshData() {
        sWeiboPage = 1;
        mWeiboInfoPresenter.getUserTimeLineInfo(mAccessToken, WEIBO_COUNT, sWeiboPage);
        mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
            @Override
            public void onSuccess(List<WeiboInfo> weibos) {
                sWeiboInfos.clear();
                sWeiboInfos.addAll(weibos);
                sWeiboAdapter.notifyDataSetChanged();
                Tools.showEmptyView(mEmptyView, mSwipeToLoadLayout, mSwipeTarget);
                mSwipeToLoadLayout.setRefreshing(false);
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

    private boolean mFlag;

    @OnClick(R.id.title_bar_write_image_view)
    public void onClick() {
        if (!mFlag) {
            sWeiboAdapter.setEditType(CANCEL);
            mTitleBarWriteImageView.setBackgroundResource(R.drawable.ic_action_write);
        } else {
            sWeiboAdapter.setEditType(DELETE);
            mTitleBarWriteImageView.setBackgroundResource(R.drawable.ic_action_close);
        }
        mFlag = !mFlag;
    }
}
