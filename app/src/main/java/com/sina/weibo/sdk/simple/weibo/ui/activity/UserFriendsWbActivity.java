package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.event.WeiboPublisherEvent;

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

    private WeiboPublisherEvent mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_user_friends_wb);
        ButterKnife.bind(this);
        //加载Toolbar
        initToolbar();
        //加载WebView
        initWebView();
        //加载监听
        initListener();
    }


    @Override
    protected void onPause() {
        pauseWebView();
        super.onPause();
    }


    /**
     * 停止WebView
     */
    private void pauseWebView() {
        mActivityUserFriendsWebShow.onPause();
        mActivityUserFriendsWebShow.pauseTimers();
    }


    /**
     * 恢复WebView
     */
    @Override
    protected void onResume() {
        resumeWebView();
        super.onResume();
    }

    private void resumeWebView() {
        mActivityUserFriendsWebShow.onResume();
        mActivityUserFriendsWebShow.resumeTimers();
    }

    /**
     * 初始化监听
     */
    private void initListener() {

    }

    /**
     * 初始化WebView
     */
    private void initWebView() {
        mActivityUserFriendsWebShow.loadUrl("http://www.weibo.com/" + mEvent.getProfileUrl());

        //网页加载进度回显
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

        mActivityUserFriendsWebShow.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        final WebSettings settings = mActivityUserFriendsWebShow.getSettings();
        mActivityUserFriendsWebShow.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!settings.getLoadsImagesAutomatically()) {
                    settings.setLoadsImagesAutomatically(true);
                }
            }


        });

        loadSetting(settings);

    }

    /**
     * WebView设置
     */
    private void loadSetting(WebSettings settings) {
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }

    /**
     * 初始化工具栏
     */
    private void initToolbar() {
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle(mEvent.getUserName());

        mTitleBarWriteImageView.setVisibility(View.GONE);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onShowUserFriendsWebEvent(WeiboPublisherEvent event) {
        mEvent = event;
    }

    /**
     * 发送消息
     *
     * @param context
     * @param event
     */
    public static void sendShowUserFriendsWebEvent(Context context, WeiboPublisherEvent event) {
        context.startActivity(new Intent(context, UserFriendsWbActivity.class));
        EventBus.getDefault().postSticky(event);
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
