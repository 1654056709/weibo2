package com.sina.weibo.sdk.simple.weibo.manager;

import android.content.Context;
import android.provider.UserDictionary;

import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;
import com.sina.weibo.sdk.simple.weibo.model.CommonWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.CommonUserInfo;
import com.sina.weibo.sdk.simple.weibo.net.RetrofitHelper;
import com.sina.weibo.sdk.simple.weibo.net.RetrofitService;

import rx.Observable;

/**
 * Created by John on 2017/3/26.
 */

public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    /**
     * 得到用户信息
     *
     * @param token
     * @param uId
     * @return
     */
    public Observable<CommonUserInfo> getUserInfo(String token, String uId) {
        return mRetrofitService.getUserInfo(token, uId);
    }


    /**
     * 获取最新公共微博信息
     *
     * @param token
     * @param count
     * @return
     */
    public Observable<CommonWeiboInfo> getPublicTimeLineWeiboInfo(String token, int count) {
        return mRetrofitService.getPublicTimeLineWeiboInfo(token, count);
    }


    /**
     * 获取当前登录用户及其所关注（授权）用户的最新微博
     *
     * @param token
     * @return
     */
    public Observable<CommonWeiboInfo> getHomeTimeLineWeiboInfo(String token, int count, int page) {
        return mRetrofitService.getHomeTimeLineWeiboInfo(token, count, page);
    }


    /**
     * 获取微博信息具体内容
     *
     * @param token
     * @param id
     * @return
     */
    public Observable<CommonWeiboInfo> getSingleWeiboInfo(String token, long id) {
        return mRetrofitService.getSingleWeiboInfo(token, id);
    }


    /**
     * 获取用户自己所发的微博信息
     *
     * @param token
     * @return
     */
    public Observable<CommonWeiboInfo> getUserTimeLineInfo(String token, int count, int page) {
        return mRetrofitService.getUserTimeLineInfo(token, count, page);
    }


    /**
     * 获取所关注的用户列表
     *
     * @param token
     * @param uId
     * @param cursor
     * @return
     */
    public Observable<CommonFriendsInfo> getUserFriendsInfo(String token, long uId, int cursor) {
        return mRetrofitService.getUserFriendsInfo(token, uId, cursor);
    }


    /**
     * 获取到@用户的微博
     *
     * @param token
     * @param count
     * @param page
     * @return
     */
    public Observable<CommonWeiboInfo> getMentionUserWeiboInfo(String token, int count, int page) {
        return mRetrofitService.getMentionUserWeiboInfo(token, count, page);
    }


    /**
     * 获取@用户的评论
     *
     * @param token
     * @param count
     * @param page
     * @return
     */
    public Observable<CommonComment> getMentionUserComment(String token, int count, int page) {
        return mRetrofitService.getMentionUserComment(token, count, page);
    }


    /**
     * 获取用户粉丝
     *
     * @param token
     * @param uId
     * @param cursor
     * @return
     */

    public Observable<CommonFriendsInfo> getUserFansInfo(String token, long uId, int cursor) {
        return mRetrofitService.getUserFansInfo(token, uId, cursor);
    }
}
