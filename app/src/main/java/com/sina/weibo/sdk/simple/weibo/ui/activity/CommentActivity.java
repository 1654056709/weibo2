package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.CommentFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by John on 2017/4/12.
 * 评论Activity
 */

public class CommentActivity extends BaseActivity {
    private static final String WEIBO_ID = "weibo_id";

    @Override
    protected Fragment createFragment() {
        return new CommentFragment();
    }

    @Override
    protected int createView() {
        return R.layout.activity_fragment;
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CommentActivity.class);
        return intent;
    }
}
