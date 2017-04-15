package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.SpinnerAdapter;
import com.sina.weibo.sdk.simple.weibo.dao.UserDao;
import com.sina.weibo.sdk.simple.weibo.event.CloseEvent;
import com.sina.weibo.sdk.simple.weibo.model.UserInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.UserInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.UserInfoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.AlgorithmConstraints;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * 用户登录界面
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
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
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
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
                    startActivity(HomeActivity.newIntent(LoginActivity.this));
                    finish();
                }
            }
        });

        //用户登出
        mLoginOutButton = (Button) findViewById(R.id.activity_login_login_out_button);
        mLoginOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //添加新用户
        mAddNewUserTextView = (TextView) findViewById(R.id.activity_login_add_user_info);
        mAddNewUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(OAuthActivity.newIntent(LoginActivity.this, OAuthActivity.FROM_LOGIN), REQUEST_CODE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.d("onActivityResult");
        if (requestCode == REQUEST_CODE) {
            if (data != null) {
                mPreActivity = data.getStringExtra(TYPE);
                Logger.d(mPreActivity);
            } else {
                Logger.d("data == null");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("onResume----" + mPreActivity);

        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mPreActivity.equals(LoginActivity.FROM_LOAD_ACTIVITY)) {
            if (mAccessToken.isSessionValid()) {
                UserInfo userInfo = mUserInfoPresenter.getLoginUser(mAccessToken.getUid());
                updateUserUI(userInfo);
            }
        } else if (mPreActivity.equals(LoginActivity.FROM_OAUTH_ACTIVITY)) {
            Logger.d("mPreActivity===from_oauth");
            mUserInfoPresenter.getUserInfo(mAccessToken);
            mUserInfoPresenter.onAttachView(new UserInfoView() {
                @Override
                public void onFailure(String errorMsg) {
                    Logger.d(errorMsg);
                    ToastUtil.showToasts(LoginActivity.this, "网络错误");
                    //清除保留的信息
                    AccessTokenKeeper.clear(LoginActivity.this);
                }

                @Override
                public void onUserNotExistUpdateView(UserInfo userInfo) {
                    Logger.d("onUserNotExistUpdateView");
                    mAccessToken = Oauth2AccessToken.parseAccessToken(UserInfo.createBundle(userInfo));
                    AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                    mUserInfos.add(userInfo);
                    updateUserUI(userInfo);
                }

                @Override
                public void onUserExistUpdateView(UserInfo userInfo) {
                    mAccessToken = Oauth2AccessToken.parseAccessToken(UserInfo.createBundle(userInfo));
                    AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                    Logger.d("onUserExistUpdateView");
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
        mUserInfoNameSpinner.setSelection((int) (userInfo.get_id() - 1),true);
        mSpinnerAdapter.notifyDataSetChanged();

        Glide.with(LoginActivity.this)
                .load(UserInfo.getByteArrayOutputStream(userInfo.getUserHead()).toByteArray())
                .bitmapTransform(new RoundedCornersTransformation(this, 10, 0, RoundedCornersTransformation.CornerType.ALL))
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
