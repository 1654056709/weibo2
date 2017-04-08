package com.sina.weibo.sdk.simple.weibo.view;

import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;

import java.util.List;

/**
 * Created by John on 2017/3/29.
 */

public interface WeiboInfoView extends View {
    void onSuccess(List<WeiboInfo> weibos);

    void onFailure(String errorMsg);
}
