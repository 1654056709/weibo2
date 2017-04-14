package com.sina.weibo.sdk.simple.weibo.net;

import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;
import com.sina.weibo.sdk.simple.weibo.model.CommonWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.CommonUserInfo;
import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by John on 2017/3/26.
 */

public interface RetrofitService {

    /**
     * 获取到用户基本信息
     *
     * @param token
     * @param uId
     * @return
     */
    @GET("2/users/show.json")
    Observable<CommonUserInfo> getUserInfo(@Query("access_token") String token, @Query("uid") String uId);


    /**
     * 返回最新的公共微博(只给显示50条)
     *
     * @param count 请求数量
     * @return
     */
    @GET("2/statuses/public_timeline.json")
    Observable<CommonWeiboInfo> getPublicTimeLineWeiboInfo(@Query("access_token") String token, @Query("count") int count);

    /**
     * 获取用户及所关注用户的最新微博
     */
    @GET("2/statuses/home_timeline.json")
    Observable<CommonWeiboInfo> getHomeTimeLineWeiboInfo(@Query("access_token") String token, @Query("count") int count, @Query("page") int page);


    /**
     * 获取单条微博信息
     *
     * @param token
     * @param id
     * @return
     */
    @GET("2/statuses/show.json")
    Call<String> getSingleWeiboInfo(@Query("access_token") String token, @Query("id") long id);


    /**
     * 获取用户自己发布的微博信息
     *
     * @return
     */
    @GET("2/statuses/user_timeline.json")
    Observable<CommonWeiboInfo> getUserTimeLineInfo(@Query("access_token") String token, @Query("count") int count, @Query("page") int page);


    /**
     * 获取用户关注列表
     *
     * @param token
     * @param uId
     * @param cursor
     * @return
     */
    @GET("2/friendships/friends.json")
    Observable<CommonFriendsInfo> getUserFriendsInfo(@Query("access_token") String token, @Query("uid") long uId, @Query("cursor") int cursor);


    /**
     * 获取最新的提到登录用户的微博列表，即@我的微博
     *
     * @param token
     * @param count
     * @param page
     * @return
     */
    @GET("2/statuses/mentions.json")
    Observable<CommonWeiboInfo> getMentionUserWeiboInfo(@Query("access_token") String token, @Query("count") int count, @Query("page") int page);


    /**
     * 获取@我的评论
     *
     * @param token
     * @param count
     * @param page
     */
    @GET("2/comments/mentions.json")
    Observable<CommonComment> getMentionUserComment(@Query("access_token") String token, @Query("count") int count, @Query("page") int page);


    /**
     * 获取粉丝关注列表
     *
     * @param token
     * @param uId
     * @param cursor
     * @return
     */
    @GET("2/friendships/followers.json")
    Observable<CommonFriendsInfo> getUserFansInfo(@Query("access_token") String token, @Query("uid") long uId, @Query("cursor") int cursor);


    /**
     * 用户发布微博
     *
     * @param token
     * @param status 用户所发微博内容
     * @return
     */
    @FormUrlEncoded
    @POST("2/statuses/update.json")
    Call<String> sendWeibo(@Field("access_token") String token, @Field("status") String status);


    /**
     * 删除一条微博
     *
     * @param token
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("2/statuses/destroy.json")
    Call<String> deleteWeibo(@Field("access_token") String token, @Field("id") long id);


    /**
     * 转发微博
     *
     * @param token
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("2/statuses/repost.json")
    Call<String> transpondWeibo(@Field("access_token") String token, @Field("id") long id);

    /**
     * 获取某条微博评论
     *
     * @param token
     * @param id
     * @param count
     * @param page
     * @return
     */
    @GET("2/comments/show.json")
    Observable<CommonComment> getWeiboComment(@Query("access_token") String token, @Query("id") long id, @Query("count") int count, @Query("page") int page);


    /**
     * 获取某条微博评论
     *
     * @param token
     * @param id
     * @param count
     * @param page
     * @return
     */
    @GET("2/comments/show.json")
    Call<String> getWeiboComment2Str(@Query("access_token") String token, @Query("id") long id, @Query("count") int count, @Query("page") int page);


    /**
     * 评论某条微博
     *
     * @param token
     * @param comment
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("2/comments/create.json")
    Call<String> sendCommonForWeibo(@Field("access_token") String token, @Field("comment") String comment, @Field("id") long id);
}

