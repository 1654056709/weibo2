package com.sina.weibo.sdk.simple.weibo.presenter;

import android.content.Context;
import android.content.IntentFilter;

import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.simple.weibo.manager.DataManager;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.view.CommentInfoView;
import com.sina.weibo.sdk.simple.weibo.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by John on 2017/4/7.
 */

public class CommentPresenter implements Presenter {
    private Context mContext;
    private CommentInfoView mCommentInfoView;
    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;

    public CommentPresenter(Context context) {
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
        mCommentInfoView = (CommentInfoView) view;
    }


    /**
     * 获取@当前用户评论
     *
     * @param accessToken
     * @param count
     * @param page
     */
    public void getMentionUserComment(Oauth2AccessToken accessToken, int count, int page) {
        mCompositeSubscription.add(
                mDataManager.getMentionUserComment(accessToken.getToken(), count, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Action1<CommonComment>() {
                                    @Override
                                    public void call(CommonComment commonComment) {
                                        mCommentInfoView.onSuccess(commonComment);
                                    }
                                },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        mCommentInfoView.onFailure(throwable.getMessage());
                                    }
                                }

                        )
        );
    }


    /**
     * 获取某条微博评论列表
     *
     * @param accessToken
     * @param weiboId
     * @param count
     * @param page
     */
    public void getWeiboComment(Oauth2AccessToken accessToken, long weiboId, int count, int page) {
        mCompositeSubscription.add(
                mDataManager.getWeiboComment(accessToken.getToken(), weiboId, count, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Action1<CommonComment>() {
                                    @Override
                                    public void call(CommonComment commonComment) {
                                        mCommentInfoView.onSuccess(commonComment);
                                    }
                                },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        mCommentInfoView.onFailure(throwable.getMessage());
                                        Logger.d(throwable.getMessage());
                                    }
                                }
                        )
        );
    }

    /**
     * 评论一条微博
     *
     * @param accessToken
     * @param comment
     * @param weiboId
     */
    public void sendCommentForWeibo(Oauth2AccessToken accessToken, String comment, long weiboId) {
        mDataManager.sendCommentForWeibo(accessToken.getToken(), comment, weiboId)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        mCommentInfoView.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        mCommentInfoView.onFailure(t.getMessage());
                    }
                });
    }

}
