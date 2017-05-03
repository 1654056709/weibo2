package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.event.ShowUserFriendsWebEvent;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFriendsWbActivity extends AppCompatActivity {
    @BindView(R.id.title_bar_title)
    TextView mTitleBarTitle;
    @BindView(R.id.title_bar_write_image_view)
    ImageView mTitleBarWriteImageView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_user_friends_web_show)
    WebView mActivityUserFriendsWebShow;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private CommonFriendsInfo.UsersBean mUserBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_user_friends_wb);

        ButterKnife.bind(this);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle(mUserBean.getScreen_name());
        mTitleBarWriteImageView.setVisibility(View.GONE);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mActivityUserFriendsWebShow.loadUrl(mUserBean.getUrl());
        Logger.d(mUserBean.getUrl());


        mActivityUserFriendsWebShow.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });


        mActivityUserFriendsWebShow.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings settings = mActivityUserFriendsWebShow.getSettings();
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onShowUserFriendsWebEvent(ShowUserFriendsWebEvent event) {
        mUserBean = event.getUsersBean();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mActivityUserFriendsWebShow.canGoBack()) {
            mActivityUserFriendsWebShow.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
