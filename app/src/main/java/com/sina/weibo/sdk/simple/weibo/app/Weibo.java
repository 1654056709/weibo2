package com.sina.weibo.sdk.simple.weibo.app;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by John on 2017/4/9.
 */

public class Weibo extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("weibo")
                .methodCount(0)
                .logLevel(LogLevel.FULL)
                .methodOffset(2);
    }
}
