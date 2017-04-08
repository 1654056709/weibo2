package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.UserFriendsAdapter;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.UserFriendsPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_friends);
        ButterKnife.bind(this);
        mSwipeTarget.setLayoutManager(new LinearLayoutManager(this));
        mUsersBeanList = new ArrayList<>();
        mUserFriendsAdapter = new UserFriendsAdapter(this, mUsersBeanList);
        mSwipeTarget.setAdapter(mUserFriendsAdapter);
        mSwipeTarget.addItemDecoration(new CustomItemDecoration());

        mUserFriendsPresenter = new UserFriendsPresenter(this);
        mUserFriendsPresenter.onCreate();

        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mAccessToken.isSessionValid()) {
            mUserFriendsPresenter.getUserFriendsInfo(mAccessToken, Long.valueOf(mAccessToken.getUid()), 0);
            mUserFriendsPresenter.onAttachView(new UserFriendsInfoView() {
                @Override
                public void onSuccess(CommonFriendsInfo commonFriendsInfo) {
                    List<CommonFriendsInfo.UsersBean> usersBeanList = commonFriendsInfo.getUsers();
                    mUsersBeanList.addAll(usersBeanList);
                    mUserFriendsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(String errorMsg) {
                    Log.d(PublicTimeLineActivity.TAG, errorMsg);
                }
            });
        }
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
}
