package com.sina.weibo.sdk.simple.weibo.presenter;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.simple.weibo.manager.DataManager;
import com.sina.weibo.sdk.simple.weibo.model.CommonWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.view.UpdateWeiboInfoView;
import com.sina.weibo.sdk.simple.weibo.view.View;

import java.util.List;

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
     * 发微博
     *
     * @param accessToken
     * @param status
     */
    public void setUserWeiboInfo(Oauth2AccessToken accessToken, String status) {
        mCompositeSubscription.add(
                mDataManager.setUserWeiboInfo(accessToken.getToken(), status)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Action1<UpdateWeiboInfo>() {
                                    @Override
                                    public void call(UpdateWeiboInfo updateWeiboInfo) {
                                        mUpdateWeiboInfoView.onSuccess(updateWeiboInfo);
                                    }
                                },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        mUpdateWeiboInfoView.onFailure(throwable.getMessage());
                                    }
                                }
                        )
        );
    }
}
