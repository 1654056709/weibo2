package com.sina.weibo.sdk.simple.weibo.event;

import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;

/**
 * Created by John on 2017/4/12.
 * 评论事件
 */

public class CommentEvent {
    private WeiboInfo mWeiboInfo;

    public WeiboInfo getWeiboInfo() {
        return mWeiboInfo;
    }

    public void setWeiboInfo(WeiboInfo weiboInfo) {
        mWeiboInfo = weiboInfo;
    }

    public CommentEvent(WeiboInfo weiboInfo) {
        mWeiboInfo = weiboInfo;
    }
}
