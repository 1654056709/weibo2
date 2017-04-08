package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.SpinnerAdapter;
import com.sina.weibo.sdk.simple.weibo.model.UserInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.UserInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.view.UserInfoView;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * 用户登录界面
 */
public class LoginActivity extends AppCompatActivity {
    private ImageView mUserInfoAvatarImagView;
    private TextView mUserInfoDescTextView;
    private Button mLoginInButton;
    private Button mLoginOutButton;
    private Oauth2AccessToken mAccessToken;
    private Spinner mUserInfoNameSpinner;
    private UserInfoPresenter mUserInfoPresenter;
    public static final String FROM_LOAD_ACTIVITY = "from_load_activity";
    public static final String FROM_OAUTH_ACTIVITY = "from_oauth_activity";
    public static final String TYPE = "pre_activity_type";
    private String mPreActivity;
    private List<UserInfo> mUserInfos;
    private SpinnerAdapter mSpinnerAdapter;
    private TextView mAddNewUserTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //上一个Activity
        mPreActivity = getIntent().getStringExtra(TYPE);

        mUserInfoPresenter = new UserInfoPresenter(this);
        mUserInfoPresenter.onCreate();

        mUserInfoAvatarImagView = (ImageView) findViewById(R.id.activity_login_user_info_avatar_image_view);
        mUserInfoDescTextView = (TextView) findViewById(R.id.activity_login_user_info_description_text_view);

        //用户登录
        mLoginInButton = (Button) findViewById(R.id.activity_login_login_in_button);
        mLoginInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAccessToken.isSessionValid()) {
                    //主页（公共微博信息页面）
//                    startActivity(HomeActivity.newIntent(LoginActivity.this));
//                    startActivity(HomeTimeLineActivity.newIntent(LoginActivity.this));
//                    startActivity(UserFriendsActivity.newIntent(LoginActivity.this));
                    startActivity(HomeActivity.newIntent(LoginActivity.this));
//                    startActivity(CustomHomeActivity.newIntent(LoginActivity.this));
//                    startActivity(MentionUserActivity.newIntent(LoginActivity.this));
                }
            }
        });

        //用户登出
        mLoginOutButton = (Button) findViewById(R.id.activity_login_login_out_button);
        mLoginOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //添加新用户
        mAddNewUserTextView = (TextView) findViewById(R.id.activity_login_add_user_info);
        mAddNewUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(OAuthActivity.newIntent(LoginActivity.this));
            }
        });


        mUserInfoNameSpinner = (Spinner) findViewById(R.id.activity_login_user_info_name_spinner);
        mUserInfos = mUserInfoPresenter.getAllUser();
        mSpinnerAdapter = new SpinnerAdapter(LoginActivity.this, mUserInfos);
        mUserInfoNameSpinner.setAdapter(mSpinnerAdapter);


        mUserInfoNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                UserInfo userInfo = mUserInfos.get(i);
                mAccessToken = Oauth2AccessToken.parseAccessToken(UserInfo.createBundle(userInfo));
                AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                //更新视图
                updateUserUI(userInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mPreActivity.equals(LoginActivity.FROM_LOAD_ACTIVITY)) {
            if (mAccessToken.isSessionValid()) {
                UserInfo userInfo = mUserInfoPresenter.getLoginUser(mAccessToken.getUid());
                updateUserUI(userInfo);
            }
        } else if (mPreActivity.equals(LoginActivity.FROM_OAUTH_ACTIVITY)) {
            mUserInfoPresenter.getUserInfo(mAccessToken);
            mUserInfoPresenter.onAttachView(new UserInfoView() {

                @Override
                public void onFailure(String errorMsg) {

                }

                @Override
                public void onUserNotExistUpdateView(UserInfo userInfo) {
                    mUserInfos.add(userInfo);
                    updateUserUI(userInfo);
                }

                @Override
                public void onUserExistUpdateView(UserInfo userInfo) {
                    updateUserUI(userInfo);
                }
            });
        }
    }

    /**
     * 更新用户视图
     *
     * @param userInfo
     */
    private void updateUserUI(UserInfo userInfo) {
        mSpinnerAdapter.notifyDataSetChanged();
        mUserInfoNameSpinner.setSelection((int) (userInfo.get_id() - 1));
        Glide.with(LoginActivity.this)
                .load(UserInfo.getByteArrayOutputStream(userInfo.getUserHead()).toByteArray())
                .bitmapTransform(new CropCircleTransformation(LoginActivity.this))
                .into(mUserInfoAvatarImagView);
        mUserInfoDescTextView.setText(userInfo.getDescription());
    }


    public static Intent newIntent(Context context, String type) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(TYPE, type);
        return intent;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPreActivity.equals(LoginActivity.FROM_OAUTH_ACTIVITY)) {
            mUserInfoPresenter.onStop();
        }
    }
}
