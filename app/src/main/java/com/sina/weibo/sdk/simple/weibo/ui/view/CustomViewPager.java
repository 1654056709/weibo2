package com.sina.weibo.sdk.simple.weibo.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by John on 2017/5/4.
 */

public class CustomViewPager extends ViewPager {
    private int mTouchSlop;
    private float mStartX;
    private float mStartY;

    public CustomViewPager(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.getTouchSlop() * 2;
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.getTouchSlop() * 2;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();
                if (Math.pow(endX - mStartX, 2) > Math.pow(endY - mStartY, 2)) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
