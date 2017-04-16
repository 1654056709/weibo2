package com.sina.weibo.sdk.simple.weibo.event;

import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;

/**
 * Created by John on 2017/4/15.
 */

public class ShowUserFriendsWebEvent {
    private CommonFriendsInfo.UsersBean usersBean;

    public CommonFriendsInfo.UsersBean getUsersBean() {
        return usersBean;
    }

    public void setUsersBean(CommonFriendsInfo.UsersBean usersBean) {
        this.usersBean = usersBean;
    }

    public ShowUserFriendsWebEvent(CommonFriendsInfo.UsersBean usersBean) {
        this.usersBean = usersBean;
    }
}
