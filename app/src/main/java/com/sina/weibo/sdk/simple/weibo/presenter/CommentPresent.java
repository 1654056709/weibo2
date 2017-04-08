package com.sina.weibo.sdk.simple.weibo.presenter;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.simple.weibo.manager.DataManager;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.view.CommentInfoView;
import com.sina.weibo.sdk.simple.weibo.view.View;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by John on 2017/4/7.
 */

public class CommentPresent implements Presenter {
    private Context mContext;
    private CommentInfoView mCommentInfoView;
    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;

    public CommentPresent(Context context) {
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
}
