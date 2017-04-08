package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.UserFansFragment;

/**
 * Created by John on 2017/4/8.
 */

public class UserFansActivity extends BaseActivity {
    @Override
    protected Fragment createFragment() {
        return new UserFansFragment();
    }

    @Override
    protected int createView() {
        return R.layout.activity_fragment;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, UserFansActivity.class);
    }
}
