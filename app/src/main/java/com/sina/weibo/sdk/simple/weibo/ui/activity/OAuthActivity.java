package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.util.Constants;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;

/**
 * 微博授权认证
 */
public class OAuthActivity extends AppCompatActivity {

    private Button mOAuthButton;
    private AuthInfo mAuthInfo;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        final View dialogView = View.inflate(this, R.layout.dialog_oauth, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogView).create();
        dialog.setCancelable(false);
        dialog.show();

        mOAuthButton = (Button) dialogView.findViewById(R.id.dialog_oauth_image_button);
        mOAuthButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAuthInfo = new AuthInfo(OAuthActivity.this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
                        mSsoHandler = new SsoHandler(OAuthActivity.this, mAuthInfo);
                        //网页认证
                        mSsoHandler.authorizeWeb(new AuthListener());
                        dialog.dismiss();
                        //关闭认证页面
                        finish();
                    }
                }
        );
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, OAuthActivity.class);
        return intent;
    }


    /**
     * 授权回调方法
     */
    class AuthListener implements WeiboAuthListener {
        //授权完成
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(OAuthActivity.this, mAccessToken);
                //跳转到登录界面
                startActivity(LoginActivity.newIntent(OAuthActivity.this, LoginActivity.FROM_OAUTH_ACTIVITY));
            }
        }


        //取消授权
        @Override
        public void onCancel() {
            finish();
            ToastUtil.showToasts(OAuthActivity.this, getResources().getString(R.string.cancel_oauth));
            //跳转到登录界面
            startActivity(LoginActivity.newIntent(OAuthActivity.this, LoginActivity.FROM_LOAD_ACTIVITY));
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

        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
