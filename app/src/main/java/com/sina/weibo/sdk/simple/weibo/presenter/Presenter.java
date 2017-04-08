package com.sina.weibo.sdk.simple.weibo.presenter;

import com.sina.weibo.sdk.simple.weibo.view.View;

/**
 * Created by John on 2017/3/26.
 */

public interface Presenter {
    void onCreate();

    void onStart();

    void onStop();

    void onAttachView(View view);
}
