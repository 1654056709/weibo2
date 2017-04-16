package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sina.weibo.sdk.ApiUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.event.CloseEvent;
import com.sina.weibo.sdk.simple.weibo.util.Constants;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 微博授权认证
 */
public class OAuthActivity extends AppCompatActivity {

    private Button mOAuthButton;
    private AuthInfo mAuthInfo;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    private AlertDialog mDialog;
    public static final String TYPE = "type";
    public static final String FROM_LOAD = "from_load";
    public static final String FROM_LOGIN = "from_login";

    private String mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            mType = intent.getStringExtra(TYPE);
        }

        setContentView(R.layout.activity_oauth);
        final View dialogView = View.inflate(this, R.layout.dialog_oauth, null);
        mDialog = new AlertDialog.Builder(this).setView(dialogView).create();
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent();
                intent.putExtra(LoginActivity.TYPE, LoginActivity.FROM_LOAD_ACTIVITY);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mDialog.show();

        mOAuthButton = (Button) dialogView.findViewById(R.id.dialog_oauth_image_button);
        mOAuthButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAuthInfo = new AuthInfo(OAuthActivity.this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
                        mSsoHandler = new SsoHandler(OAuthActivity.this, mAuthInfo);
                        mSsoHandler.authorize(new AuthListener());
                    }
                }
        );
    }


    public static Intent newIntent(Context context, String type) {
        Intent intent = new Intent(context, OAuthActivity.class);
        intent.putExtra(TYPE, type);
        return intent;
    }


    /**
     * 授权回调方法
     */
    class AuthListener implements WeiboAuthListener {
        //授权完成
        @Override
        public void onComplete(Bundle values) {
            EventBus.getDefault().post(new CloseEvent());
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(OAuthActivity.this, mAccessToken);
                if (mType.equals(FROM_LOAD)) {
                    //跳转到登录界面
                    startActivity(LoginActivity.newIntent(OAuthActivity.this, LoginActivity.FROM_OAUTH_ACTIVITY));
                } else if (mType.equals(FROM_LOGIN)) {
                    Intent intent = new Intent();
                    intent.putExtra(LoginActivity.TYPE, LoginActivity.FROM_OAUTH_ACTIVITY);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        }


        //取消授权
        @Override
        public void onCancel() {
            ToastUtil.showToasts(OAuthActivity.this, getResources().getString(R.string.cancel_oauth));
            if (mType.equals(FROM_LOGIN)) {
                Intent intent = new Intent();
                intent.putExtra(LoginActivity.TYPE, LoginActivity.FROM_LOAD_ACTIVITY);
                setResult(RESULT_OK, intent);
            } else if (mType.equals(FROM_LOAD)) {
                startActivity(LoginActivity.newIntent(OAuthActivity.this, LoginActivity.FROM_LOAD_ACTIVITY));
            }
            finish();
        }


        //授权过程发生异常
        @Override
        public void onWeiboException(WeiboException e) {
            ToastUtil.showToasts(OAuthActivity.this, getResources().getString(R.string.oauth_error));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
