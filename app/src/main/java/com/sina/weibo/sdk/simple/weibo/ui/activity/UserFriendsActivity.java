package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.database.MergeCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.UserFriendsAdapter;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.UserFriendsPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
import com.sina.weibo.sdk.simple.weibo.ui.view.RefreshHeaderView;
import com.sina.weibo.sdk.simple.weibo.util.CustomItemDecoration;
import com.sina.weibo.sdk.simple.weibo.view.UserFriendsInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户所关注的用户信息列表
 */
public class UserFriendsActivity extends AppCompatActivity {


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
    private UserFriendsPresenter mUserFriendsPresenter;
    private Oauth2AccessToken mAccessToken;
    List<CommonFriendsInfo.UsersBean> mUsersBeanList;
    private UserFriendsAdapter mUserFriendsAdapter;
    private int mNextCursor;
    private int mPreCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_friends);
        ButterKnife.bind(this);

        mSwipeTarget.setLayoutManager(new LinearLayoutManager(this));
        mUsersBeanList = new ArrayList<>();
        mUserFriendsAdapter = new UserFriendsAdapter(this, mUsersBeanList);
        mSwipeTarget.setAdapter(mUserFriendsAdapter);

        mUserFriendsPresenter = new UserFriendsPresenter(this);
        mUserFriendsPresenter.onCreate();

        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mAccessToken.isSessionValid()) {
            mSwipeToLoadLayout.setRefreshing(true);
        }

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
     * 加载更多数据
     */
    private void loadMoreData() {
        mUserFriendsPresenter.getUserFriendsInfo(mAccessToken, Long.valueOf(mAccessToken.getUid()), mNextCursor);
        mUserFriendsPresenter.onAttachView(new UserFriendsInfoView() {
            @Override
            public void onSuccess(CommonFriendsInfo commonFriendsInfo) {
                List<CommonFriendsInfo.UsersBean> usersBeanList = commonFriendsInfo.getUsers();
                mUsersBeanList.clear();
                mUsersBeanList.addAll(usersBeanList);
                mUserFriendsAdapter.notifyDataSetChanged();
                mNextCursor = commonFriendsInfo.getNext_cursor();
                mPreCursor = commonFriendsInfo.getPrevious_cursor();
                initTitle(mNextCursor, commonFriendsInfo.getTotal_number());
                mSwipeToLoadLayout.setLoadingMore(false);
                Log.d(PublicTimeLineActivity.TAG, mPreCursor + "---" + mNextCursor);
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.d(PublicTimeLineActivity.TAG, errorMsg);
            }
        });
    }


    /**
     * 刷新数据
     */
    private void refreshData() {
        int preCursor = 0;
        if (mPreCursor != 0) {
            preCursor = mPreCursor - 50;
        }
        mUserFriendsPresenter.getUserFriendsInfo(mAccessToken, Long.valueOf(mAccessToken.getUid()), preCursor);
        mUserFriendsPresenter.onAttachView(new UserFriendsInfoView() {
            @Override
            public void onSuccess(CommonFriendsInfo commonFriendsInfo) {
                List<CommonFriendsInfo.UsersBean> usersBeanList = commonFriendsInfo.getUsers();
                mUsersBeanList.clear();
                mUsersBeanList.addAll(usersBeanList);
                mUserFriendsAdapter.notifyDataSetChanged();
                mNextCursor = commonFriendsInfo.getNext_cursor();
                mPreCursor = commonFriendsInfo.getPrevious_cursor();
                initTitle(mNextCursor, commonFriendsInfo.getTotal_number());
                mSwipeToLoadLayout.setRefreshing(false);
                Log.d(PublicTimeLineActivity.TAG, mPreCursor + "---" + mNextCursor);
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.d(PublicTimeLineActivity.TAG, errorMsg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserFriendsPresenter.onStop();
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UserFriendsActivity.class);
        return intent;
    }

    @OnClick(R.id.title_bar_write_image_view)
    public void onClick() {
    }


    /**
     * 初始化标题
     * @param nextCursor
     * @param totalNum
     */
    private void initTitle(int nextCursor, int totalNum) {
        int totalPage = 0;
        if (totalNum % 50 == 0) {
            totalPage = totalNum / 50;
        } else {
            totalPage = totalNum / 50 + 1;
        }

        int page = nextCursor / 50;
        if (nextCursor == 0) {
            page = totalPage;
        }
        mTitleBarTitle.setText(page + "/" + totalPage);
    }
}
