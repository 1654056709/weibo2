package com.sina.weibo.sdk.simple.weibo.net;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by John on 2017/3/26.
 * 简单处理Retrofit
 */

public class RetrofitHelper {
    //BaseUrl
    private static final String BASE_URL = "https://api.weibo.com/";
    private Context mContext;
    private static RetrofitHelper mRetrofitHelper;
    private Retrofit mRetrofit;

    private RetrofitHelper(Context context) {
        this.mContext = context;
        //初始化Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    /**
     * 单例
     *
     * @param context
     * @return
     */
    public static RetrofitHelper getInstance(Context context) {
        if (mRetrofitHelper == null) {
            mRetrofitHelper = new RetrofitHelper(context);
        }
        return mRetrofitHelper;
    }

    /**
     * 获取接口的实例化
     *
     * @return
     */
    public RetrofitService getServer() {
        RetrofitService retrofitService = mRetrofit.create(RetrofitService.class);
        return retrofitService;
    }


}
