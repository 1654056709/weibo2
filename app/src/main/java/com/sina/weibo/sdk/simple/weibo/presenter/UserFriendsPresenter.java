package com.sina.weibo.sdk.simple.weibo.presenter;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.simple.weibo.manager.DataManager;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;
import com.sina.weibo.sdk.simple.weibo.view.UserFriendsInfoView;
import com.sina.weibo.sdk.simple.weibo.view.View;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by John on 2017/4/2.
 */

public class UserFriendsPresenter implements Presenter {
    private UserFriendsInfoView mUserFriendsInfoView;
    private DataManager mDataManager;
    private CommonFriendsInfo mCommonFriendsInfo;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;

    public UserFriendsPresenter(Context context) {
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
        mUserFriendsInfoView = (UserFriendsInfoView) view;
    }

    /**
     * 得到用户所关注的所有用户
     * @param accessToken
     * @param uId
     * @param cursor
     */
    public void getUserFriendsInfo(Oauth2AccessToken accessToken, long uId, int cursor) {
        mCompositeSubscription.add(
                mDataManager.getUserFriendsInfo(accessToken.getToken(), uId, cursor)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Action1<CommonFriendsInfo>() {
                                    @Override
                                    public void call(CommonFriendsInfo commonFriendsInfo) {
                                        mUserFriendsInfoView.onSuccess(commonFriendsInfo);
                                    }
                                }
                                ,
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        mUserFriendsInfoView.onFailure(throwable.getMessage());
                                    }
                                }

                        )
        );


    }
}
