package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.event.CloseEvent;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.HomeFragment;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

public class HomeActivity extends BaseActivity {
    public static final String TAG = "HomeActivity";

    @Override
    protected Fragment createFragment() {
        return new HomeFragment();
    }

    @Override
    protected int createView() {
        EventBus.getDefault().post(new CloseEvent());
        return R.layout.activity_fragment;
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }
}
