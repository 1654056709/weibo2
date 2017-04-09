package com.sina.weibo.sdk.simple.weibo.presenter;

import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.simple.weibo.manager.DataManager;
import com.sina.weibo.sdk.simple.weibo.model.CommonWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.ui.activity.PublicTimeLineActivity;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.view.View;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by John on 2017/3/29.
 * 处理微博信息相关的逻辑操作
 */

public class WeiboInfoPresenter implements Presenter {
    private Context mContext;
    private DataManager mDataManager;
    private WeiboInfoView mWeiboInfoView;
    private CompositeSubscription mCompositeSubscription;

    public WeiboInfoPresenter(Context context) {
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
        mWeiboInfoView = (WeiboInfoView) view;
    }


    /**
     * 获取公共微博信息
     *
     * @param accessToken
     */
    public void getPublicTimeLineWeiboInfo(Oauth2AccessToken accessToken, int count) {
        mCompositeSubscription.add(
                mDataManager.getPublicTimeLineWeiboInfo(accessToken.getToken(), count)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map(new Func1<CommonWeiboInfo, List<WeiboInfo>>() {
                            @Override
                            public List<WeiboInfo> call(CommonWeiboInfo commonWeiboInfo) {
                                List<WeiboInfo> weibos = WeiboInfo.transformToWiebo(commonWeiboInfo);
                                return weibos;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<WeiboInfo>>() {
                            @Override
                            public void call(List<WeiboInfo> weibos) {
                                mWeiboInfoView.onSuccess(weibos);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                errorHint();
                            }
                        })
        );
    }


    /**
     * 获取用户及所关注关注用户的最新微博信息
     *
     * @param accessToken
     */
    public void getHomeTimeLineWeiboInfo(final Oauth2AccessToken accessToken, int count, int page) {
        mCompositeSubscription.add(
                mDataManager.getHomeTimeLineWeiboInfo(accessToken.getToken(), count, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map(
                                new Func1<CommonWeiboInfo, List<WeiboInfo>>() {
                                    @Override
                                    public List<WeiboInfo> call(CommonWeiboInfo commonWeiboInfo) {
                                        List<WeiboInfo> weibos = WeiboInfo.transformToWiebo(commonWeiboInfo);
                                        return weibos;
                                    }
                                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Action1<List<WeiboInfo>>() {
                                    @Override
                                    public void call(List<WeiboInfo> weibos) {
                                        mWeiboInfoView.onSuccess(weibos);
                                    }
                                },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        Logger.d(throwable.getMessage());
                                        errorHint();
                                    }
                                }
                        )
        );

    }

    public void getWeiboContent(Oauth2AccessToken accessToken, long id) {
        mCompositeSubscription.add(
                mDataManager.getSingleWeiboInfo(accessToken.getToken(), id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Action1<CommonWeiboInfo>() {
                                    @Override
                                    public void call(CommonWeiboInfo commonWeiboInfo) {
                                        Log.d(PublicTimeLineActivity.TAG, commonWeiboInfo.getStatuses().size() + " ");
                                    }
                                },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        Log.d(PublicTimeLineActivity.TAG, throwable.getMessage());
                                    }
                                }

                        )
        );

    }

    /**
     * 获取用户自己所发微博信息
     *
     * @param accessToken
     */
    public void getUserTimeLineInfo(Oauth2AccessToken accessToken, int count, int page) {
        mCompositeSubscription.add(
                mDataManager.getUserTimeLineInfo(accessToken.getToken(), count, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map(new Func1<CommonWeiboInfo, List<WeiboInfo>>() {
                            @Override
                            public List<WeiboInfo> call(CommonWeiboInfo commonWeiboInfo) {
                                List<WeiboInfo> weibos = WeiboInfo.transformToWiebo(commonWeiboInfo);
                                return weibos;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Action1<List<WeiboInfo>>() {
                                    @Override
                                    public void call(List<WeiboInfo> weiboInfos) {
                                        mWeiboInfoView.onSuccess(weiboInfos);
                                    }
                                },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        mWeiboInfoView.onFailure(throwable.getMessage());
                                    }
                                }

                        )
        );
    }


    /**
     * 获取@当前用户的微博
     *
     * @param accessToken
     * @param count
     * @param page
     */
    public void getMentionUserWeiboInfo(Oauth2AccessToken accessToken, int count, int page) {
        mCompositeSubscription.add(
                mDataManager.getMentionUserWeiboInfo(accessToken.getToken(), count, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map(new Func1<CommonWeiboInfo, List<WeiboInfo>>() {
                            @Override
                            public List<WeiboInfo> call(CommonWeiboInfo commonWeiboInfo) {
                                List<WeiboInfo> weibos = WeiboInfo.transformToWiebo(commonWeiboInfo);
                                return weibos;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Action1<List<WeiboInfo>>() {
                                    @Override
                                    public void call(List<WeiboInfo> weiboInfos) {
                                        mWeiboInfoView.onSuccess(weiboInfos);
                                    }
                                },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        mWeiboInfoView.onFailure(throwable.getMessage());
                                        Log.d(PublicTimeLineActivity.TAG, throwable.getMessage());
                                    }
                                }
                        )
        );

    }


    /**
     * 错误提示
     */
    private void errorHint() {
        ToastUtil.showToasts(mContext, "网络未连接");
    }
}
