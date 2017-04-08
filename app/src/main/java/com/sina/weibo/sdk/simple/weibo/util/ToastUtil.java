package com.sina.weibo.sdk.simple.weibo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by John on 2017/3/26.
 */

public class ToastUtil {
    public static void showToasts(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
