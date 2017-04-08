package com.sina.weibo.sdk.simple.weibo.view;

import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;

/**
 * Created by John on 2017/4/2.
 */

public interface UserFriendsInfoView extends View {
    void onSuccess(CommonFriendsInfo commonFriendsInfo);

    void onFailure(String errorMsg);
}
