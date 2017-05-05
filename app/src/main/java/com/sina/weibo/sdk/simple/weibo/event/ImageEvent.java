package com.sina.weibo.sdk.simple.weibo.event;

import java.util.List;

/**
 * Created by John on 2017/5/3.
 * 图片事件
 */

public class ImageEvent {
    //当前图片位置
    private int mCurrentPos;
    //所有图片资源
    private List<String> mUrls;

    public ImageEvent() {

    }

    public ImageEvent(int currentPos, List<String> urls) {
        mCurrentPos = currentPos;
        mUrls = urls;
    }

    public int getCurrentPos() {
        return mCurrentPos;
    }

    public void setCurrentPos(int currentPos) {
        mCurrentPos = currentPos;
    }

    public List<String> getUrls() {
        return mUrls;
    }

    public void setUrls(List<String> urls) {
        mUrls = urls;
    }
}
