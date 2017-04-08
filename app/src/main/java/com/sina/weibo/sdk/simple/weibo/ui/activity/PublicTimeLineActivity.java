package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.WeiboAdapter;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.WeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.PublicTimeLineFragment;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import java.util.ArrayList;
import java.util.List;


/**
 * 显示最新公共微博
 */
public class PublicTimeLineActivity extends BaseActivity {
    public static final String TAG = "PublicTimeLineActivity";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, PublicTimeLineActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new PublicTimeLineFragment();
    }

    @Override
    protected int createView() {
        return R.layout.activity_fragment;
    }
}
