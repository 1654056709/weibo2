package com.sina.weibo.sdk.simple.weibo.util;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by John on 2017/4/10.
 * OkHttp拦截器
 */

public class RequestLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Logger.d("请求：%s", request.url());
        return chain.proceed(request);
    }
}
