package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.MutableDouble;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;

import com.sina.weibo.sdk.simple.weibo.event.CloseEvent;
import com.sina.weibo.sdk.simple.weibo.util.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoadActivity extends AppCompatActivity {
    private static final String TAG = "LoadActivity";

    /**
     * 启动界面图片
     */
    private ImageView mLoadImageView;
    private Oauth2AccessToken mAccessToken;
    private AlphaAnimation mAnimation;
    private boolean mFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        mLoadImageView = (ImageView) findViewById(R.id.activity_load_image_view);
        mAnimation = new AlphaAnimation(0.1f, 1.0f);

        mAnimation.setDuration(5000);
        mLoadImageView.setAnimation(mAnimation);

        //设置动画监听
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                init();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mFlag) {
                    //判断是否授权
                    mAccessToken = AccessTokenKeeper.readAccessToken(LoadActivity.this);
                    if (!mAccessToken.isSessionValid()) {
                        startActivity(OAuthActivity.newIntent(LoadActivity.this, OAuthActivity.FROM_LOAD));
                    } else {
                        startActivity(HomeActivity.newIntent(LoadActivity.this));
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCloseEvent(CloseEvent closeEvent) {
        finish();
    }


    private void init() {
        //判断是否有网络连接
        mFlag = Tools.checkNetWork(LoadActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
