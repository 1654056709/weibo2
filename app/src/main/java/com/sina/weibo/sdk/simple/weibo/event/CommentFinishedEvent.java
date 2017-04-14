package com.sina.weibo.sdk.simple.weibo.event;

import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;

/**
 * Created by John on 2017/4/12.
 */

public class CommentFinishedEvent {
    private WeiboInfo mWeiboInfo;

    public WeiboInfo getWeiboInfo() {
        return mWeiboInfo;
    }

    public void setWeiboInfo(WeiboInfo weiboInfo) {
        mWeiboInfo = weiboInfo;
    }

    public CommentFinishedEvent(WeiboInfo weiboInfo) {
        mWeiboInfo = weiboInfo;
    }
}
