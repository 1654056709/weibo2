package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.WeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import java.util.List;

/**
 * 显示单条微博内容
 *
 * @author John
 * @version 1.0
 * @date 2017/4/13
 */
public class WeiboContentActivity extends AppCompatActivity {
    private static final String WEIBO_ID = "weibo_id";
    private WeiboInfoPresenter mWeiboInfoPresenter;
    private Oauth2AccessToken mAccessToken;
    private long mWeiboId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_content);
        Intent intent = getIntent();
        if (intent != null) {
            mWeiboId = intent.getLongExtra(WEIBO_ID, 0);
        }
        mWeiboInfoPresenter = new WeiboInfoPresenter(this);
        mWeiboInfoPresenter.onCreate();

        mAccessToken = AccessTokenKeeper.readAccessToken(this);

        if (mAccessToken.isSessionValid()) {
            mWeiboInfoPresenter.getWeiboContent(mAccessToken, mWeiboId);
            mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
                @Override
                public void onSuccess(List<WeiboInfo> weibos) {

                }

                @Override
                public void onFailure(String errorMsg) {

                }
            });
        }
    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, WeiboContentActivity.class);
        intent.putExtra(WEIBO_ID, id);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeiboInfoPresenter.onStop();
    }
}
