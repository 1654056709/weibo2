package com.sina.weibo.sdk.simple.weibo.presenter;

import android.content.Context;
import android.media.session.MediaSession;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.simple.weibo.manager.DataManager;
import com.sina.weibo.sdk.simple.weibo.model.CommonWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.view.UpdateWeiboInfoView;
import com.sina.weibo.sdk.simple.weibo.view.View;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by John on 2017/4/9.
 */

public class UpdateWeiboInfoPresenter implements Presenter {
    private Context mContext;
    private UpdateWeiboInfoView mUpdateWeiboInfoView;
    private CompositeSubscription mCompositeSubscription;
    private DataManager mDataManager;

    public UpdateWeiboInfoPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        mDataManager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onAttachView(View view) {
        mUpdateWeiboInfoView = (UpdateWeiboInfoView) view;
    }

    /**
     * 发送微博
     *
     * @param accessToken
     * @param status
     */
    public void sendWeibo(Oauth2AccessToken accessToken, String status) {
        mDataManager.sendWeibo(accessToken.getToken(), status)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        UpdateWeiboInfo updateWeiboInfo = new Gson().fromJson(response.body(), UpdateWeiboInfo.class);
                        mUpdateWeiboInfoView.onSuccess(updateWeiboInfo);

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Logger.d(t.getMessage());
                        mUpdateWeiboInfoView.onFailure(t.getMessage());
                    }
                });
    }


    /**
     * 删除一条微博信息
     *
     * @param accessToken
     * @param id
     */
    public void deleteWeiboinfo(Oauth2AccessToken accessToken, long id) {
        mDataManager.deleteWeibo(accessToken.getToken(), id)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        mUpdateWeiboInfoView.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        mUpdateWeiboInfoView.onFailure(t.getMessage());
                    }
                });
    }


    /**
     * 转发一条微博
     *
     * @param accessToken
     * @param weiboId
     */
    public void transpondWeibo(Oauth2AccessToken accessToken, long weiboId) {
        mDataManager.transpondWeibo(accessToken.getToken(), weiboId)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        mUpdateWeiboInfoView.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        mUpdateWeiboInfoView.onFailure(t.getMessage());
                    }
                });
    }
}
