package com.sina.weibo.sdk.simple.weibo.view;

import com.sina.weibo.sdk.simple.weibo.model.CommonComment;

/**
 * Created by John on 2017/4/7.
 */

public interface CommentInfoView extends View {
    void onSuccess(CommonComment commonComment);

    void onFailure(String errorMsg);
}
