package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sina.weibo.sdk.simple.weibo.R;

/**
 * Created by John on 2017/4/4.
 * 用户主页面
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @LayoutRes
    protected abstract int createView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createView());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_fragment_container, createFragment())
                .commit();
    }
}
