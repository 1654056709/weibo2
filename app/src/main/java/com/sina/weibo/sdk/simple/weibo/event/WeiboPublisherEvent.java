package com.sina.weibo.sdk.simple.weibo.event;

/**
 * Created by John on 2017/4/15.
 * 微博发布者事件
 */

public class WeiboPublisherEvent {
    private String mProfileUrl;
    private String mUserName;

    public WeiboPublisherEvent(String profileUrl, String userName) {
        mProfileUrl = profileUrl;
        mUserName = userName;
    }

    public String getProfileUrl() {
        return mProfileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        mProfileUrl = profileUrl;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }
}
