package com.sina.weibo.sdk.simple.weibo.net;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.simple.weibo.util.RequestLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private Retrofit mRetrofit2Str;

    private RetrofitHelper(Context context) {
//        OkHttpClient.Builder client = new OkHttpClient.Builder()
//                .addInterceptor(new RequestLoggingInterceptor())
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true);
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
//                new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(String message) {
//                        Logger.d("收到响应: " + message);
//                    }
//                }
//        );

//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        client.addInterceptor(logging);
        this.mContext = context;
        //初始化Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mRetrofit2Str = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
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


    public RetrofitService getServer2Str() {
        RetrofitService retrofitService = mRetrofit2Str.create(RetrofitService.class);
        return retrofitService;
    }


}
