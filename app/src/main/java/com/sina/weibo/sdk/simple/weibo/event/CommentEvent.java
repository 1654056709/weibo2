package com.sina.weibo.sdk.simple.weibo.event;

/**
 * Created by John on 2017/4/12.
 */

public class CommentEvent {
    private CommentEvent mCommentEvent;

    public CommentEvent getCommentEvent() {
        return mCommentEvent;
    }

    public void setCommentEvent(CommentEvent commentEvent) {
        mCommentEvent = commentEvent;
    }

    public CommentEvent(CommentEvent commentEvent) {
        mCommentEvent = commentEvent;
    }
}
