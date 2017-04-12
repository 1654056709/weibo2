package com.sina.weibo.sdk.simple.weibo.view;

import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;

/**
 * Created by John on 2017/4/9.
 */

public interface UpdateWeiboInfoView extends View {
    void onSuccess(UpdateWeiboInfo updateWeiboInfo);

    void onSuccess(String weiboInfo);

    void onFailure(String errorMsg);
}
