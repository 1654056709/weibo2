package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.CommentFragment;

/**
 * Created by John on 2017/4/12.
 */

public class CommentActivity extends BaseActivity {
    private static final String WEIBO_ID = "weibo_id";

    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        long weiboId = -1;
        if (intent != null) {
            weiboId = intent.getLongExtra(WEIBO_ID, -1);
        }
        return CommentFragment.newCommentFragment(weiboId);
    }

    @Override
    protected int createView() {
        return R.layout.activity_fragment;
    }


    public static Intent newIntent(Context context, long weiboId) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(WEIBO_ID, weiboId);
        return intent;
    }
}
