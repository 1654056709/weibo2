package com.sina.weibo.sdk.simple.weibo.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.dao.UserDao;
import com.sina.weibo.sdk.simple.weibo.manager.DataManager;
import com.sina.weibo.sdk.simple.weibo.model.UserInfo;
import com.sina.weibo.sdk.simple.weibo.model.CommonUserInfo;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.view.UserInfoView;
import com.sina.weibo.sdk.simple.weibo.view.View;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by John on 2017/3/26.
 * 处理用户
 */

public class UserInfoPresenter implements Presenter {
    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private UserInfoView mUserInfoView;
    private CommonUserInfo mCommonUserInfo;

    public UserInfoPresenter(Context context) {
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
        mUserInfoView = (UserInfoView) view;
    }

    public void getUserInfo(final Oauth2AccessToken accessToken) {
        mCompositeSubscription.add(
                mDataManager.getUserInfo(accessToken.getToken(), accessToken.getUid())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Subscriber<CommonUserInfo>() {
                                    @Override
                                    public void onCompleted() {
                                        Logger.d("onCompleted");
                                        isPersistentUser(accessToken);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        mUserInfoView.onFailure(e.getMessage());
                                        Logger.d("error : ---- " + e.getMessage());
                                    }

                                    @Override
                                    public void onNext(CommonUserInfo commonUserInfo) {
                                        mCommonUserInfo = commonUserInfo;
                                    }
                                }
                        )
        );
    }

    /**
     * 判断是否对将用户存入到数据库
     *
     * @param accessToken
     */
    private void isPersistentUser(Oauth2AccessToken accessToken) {
        UserInfo userInfo = getLoginUser(String.valueOf(mCommonUserInfo.getId()));
        if (userInfo != null) {
            ToastUtil.showToasts(mContext, "该用户已存在,请直接登录");
/*            AccessTokenKeeper.writeAccessToken(
                    mContext,
                    Oauth2AccessToken.parseAccessToken(UserInfo.createBundle(userInfo))
            );*/
            mUserInfoView.onUserExistUpdateView(userInfo);
        } else {
            persistentUser(accessToken);
        }
    }

    private void persistentUser(final Oauth2AccessToken accessToken) {
        Glide.with(mContext)
                .load(mCommonUserInfo.getAvatar_hd())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Observable.create(new Observable.OnSubscribe<UserInfo>() {
                            @Override
                            public void call(Subscriber<? super UserInfo> subscriber) {
                                //插入数据到数据库
                                UserInfo userInfo = new UserInfo(
                                        accessToken.getUid(),
                                        mCommonUserInfo.getName(),
                                        accessToken.getToken(),
                                        accessToken.getPhoneNum(),
                                        mCommonUserInfo.getDescription(),
                                        resource);
                                UserDao.getInstance(mContext)
                                        .insertUser(userInfo);
                                subscriber.onNext(userInfo);
                            }
                        })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        new Action1<UserInfo>() {
                                            @Override
                                            public void call(UserInfo userInfo) {
                                                mUserInfoView.onUserNotExistUpdateView(userInfo);
                                            }
                                        }
                                );

                    }
                });
    }

    public UserInfo getLoginUser(String uId) {
        UserInfo userInfo = UserDao.getInstance(mContext).findUser(uId);
        return userInfo;
    }


    public List<UserInfo> getAllUser() {
        return UserDao.getInstance(mContext).findAll();
    }
}
