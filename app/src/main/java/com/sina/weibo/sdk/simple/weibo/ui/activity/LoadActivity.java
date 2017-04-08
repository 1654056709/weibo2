package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;

import com.sina.weibo.sdk.simple.weibo.util.Tools;

public class LoadActivity extends AppCompatActivity {
    private static final String TAG = "LoadActivity";

    /**
     * 启动界面图片
     */
    private ImageView mLoadImageView;
    private Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        mLoadImageView = (ImageView) findViewById(R.id.activity_load_image_view);
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(3000);
        mLoadImageView.setAnimation(animation);

        //设置动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                init();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断是否授权
                mAccessToken = AccessTokenKeeper.readAccessToken(LoadActivity.this);
                if (!mAccessToken.isSessionValid()) {
                    startActivity(OAuthActivity.newIntent(LoadActivity.this));
                } else {
                    startActivity(LoginActivity.newIntent(LoadActivity.this, LoginActivity.FROM_LOAD_ACTIVITY));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void init() {
        //判断是否有网络连接
        Tools.checkNetWork(LoadActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
