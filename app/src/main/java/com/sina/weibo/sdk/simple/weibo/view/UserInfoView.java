package com.sina.weibo.sdk.simple.weibo.view;

import com.sina.weibo.sdk.simple.weibo.model.UserInfo;

/**
 * Created by John on 2017/3/26.
 */

public interface UserInfoView extends View {

    void onFailure(String errorMsg);

    void onUserNotExistUpdateView(UserInfo userInfo);

    void onUserExistUpdateView(UserInfo userInfo);
}
