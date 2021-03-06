package com.sina.weibo.sdk.simple.weibo.manager;

import android.content.Context;

import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;
import com.sina.weibo.sdk.simple.weibo.model.CommonUserInfo;
import com.sina.weibo.sdk.simple.weibo.model.CommonWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.net.RetrofitHelper;
import com.sina.weibo.sdk.simple.weibo.net.RetrofitService;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by John on 2017/3/26.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    private RetrofitService mRetrofitService2Str;

    public DataManager(Context context) {
        mRetrofitService = RetrofitHelper.getInstance(context).getServer();
        mRetrofitService2Str = RetrofitHelper.getInstance(context).getServer2Str();
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
    public Call<String> getSingleWeiboInfo(String token, long id) {
        return mRetrofitService2Str.getSingleWeiboInfo(token, id);
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


    /**
     * 发布微博
     *
     * @param token
     * @param status
     * @return
     */
    public Call<String> sendWeibo(String token, String status) {
        return mRetrofitService2Str.sendWeibo(token, status);
    }


    /**
     * 删除一条微博
     *
     * @param token
     * @param id
     * @return
     */
    public Call<String> deleteWeibo(String token, long id) {
        return mRetrofitService2Str.deleteWeibo(token, id);
    }


    /**
     * 转发一条微博
     *
     * @param token
     * @param weiboId
     * @return
     */
    public Call<String> transpondWeibo(String token, long weiboId) {
        return mRetrofitService2Str.transpondWeibo(token, weiboId);
    }


    /**
     * 获取某条微博评论列表
     *
     * @param token
     * @param weiboId
     * @param count
     * @param page
     * @return
     */
    public Observable<CommonComment> getWeiboComment(String token, long weiboId, int count, int page) {
        return mRetrofitService.getWeiboComment(token, weiboId, count, page);
    }


    /**
     * 评论某条微博
     *
     * @param token
     * @param comment
     * @param weiboId
     * @return
     */
    public Call<String> sendCommentForWeibo(String token, String comment, long weiboId) {
        return mRetrofitService2Str.sendCommonForWeibo(token, comment, weiboId);
    }
}
