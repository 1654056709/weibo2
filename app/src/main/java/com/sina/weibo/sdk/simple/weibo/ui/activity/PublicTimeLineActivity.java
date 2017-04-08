package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import java.util.ArrayList;
import java.util.List;


/**
 * 显示最新公共微博
 */
public class PublicTimeLineActivity extends AppCompatActivity {
    public static final String TAG = "PublicTimeLineActivity";
    private View mTitleBarView;
    private RecyclerView mShowWeiboRecyclerView;
    private ImageView mRefreshWeiboImageView;
    private ImageView mWriteWeiboImageView;
    private WeiboInfoPresenter mWeiboInfoPresenter;
    private Oauth2AccessToken mAccessToken;
    private List<WeiboInfo> mWeibos;
    private WeiboAdapter mWeiboAdapter;
    private static final int WEIBO_COUNT = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_time_line);
        mWeiboInfoPresenter = new WeiboInfoPresenter(this);
        mWeiboInfoPresenter.onCreate();

        mTitleBarView = findViewById(R.id.activity_public_time_line_title_bar);




        //写微博
        mWriteWeiboImageView = (ImageView) mTitleBarView.findViewById(R.id.title_bar_write_image_view);
        mWriteWeiboImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        mShowWeiboRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        mShowWeiboRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWeibos = new ArrayList<>();
        mWeiboAdapter = new WeiboAdapter(PublicTimeLineActivity.this, mWeibos);
        mShowWeiboRecyclerView.setAdapter(mWeiboAdapter);


        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        updatePublicTimeLineWeibo();
    }


    /**
     * 更新公共微博信息
     */
    private void updatePublicTimeLineWeibo() {
        Tools.progressCallback(PublicTimeLineActivity.this, new Tools.ProgressFinishedCallBack() {
            @Override
            public void onSuccess(final ProgressDialog progressDialog) {
                if (mAccessToken.isSessionValid()) {
                    mWeiboInfoPresenter.getPublicTimeLineWeiboInfo(mAccessToken, WEIBO_COUNT);
                    mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
                        @Override
                        public void onSuccess(List<WeiboInfo> weibos) {
                            mWeibos.clear();
                            mWeibos.addAll(weibos);
                            mWeiboAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(String errorMsg) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, PublicTimeLineActivity.class);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeiboInfoPresenter.onStop();
    }
}
