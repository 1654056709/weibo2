package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.presenter.WeiboInfoPresenter;

/**
 * 显示微博具体内容
 */
public class WeiboContentActivity extends AppCompatActivity {
    private static final String WEIBO_ID = "weibo_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_content);
        Intent intent = getIntent();
        long id = 0;
        if (intent != null) {
            id = intent.getLongExtra(WEIBO_ID, 0);
        }
        WeiboInfoPresenter presenter = new WeiboInfoPresenter(this);
        presenter.onCreate();
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);
        if (accessToken.isSessionValid()) {
            presenter.getWeiboContent(accessToken, id);
        }
    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, WeiboContentActivity.class);
        intent.putExtra(WEIBO_ID, id);
        return intent;
    }
}
