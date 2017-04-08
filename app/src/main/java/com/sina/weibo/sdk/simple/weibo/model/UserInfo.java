package com.sina.weibo.sdk.simple.weibo.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.simple.weibo.db.DBInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by John on 2017/3/22.
 */

public class UserInfo implements Serializable {
    //主键
    private long _id;
    //用户编号
    private String mUserId;
    //姓名
    private String mUserName;

    //access token
    private String mAccessToken;

    //电话号码
    private String mPhoneNumber;

    //描述
    private String mDescription;
    //头像
    private Bitmap mUserHead;


    public UserInfo(long _id, String userId, String userName, String accessToken, String phoneNumber, String description, Bitmap userHead) {
        this._id = _id;
        mUserId = userId;
        mUserName = userName;
        mAccessToken = accessToken;
        mPhoneNumber = phoneNumber;
        mDescription = description;
        mUserHead = userHead;
    }

    public static ContentValues getContentValues(UserInfo userInfo) {
        ContentValues values = new ContentValues();
        values.put(DBInfo.Table.USER_ID, userInfo.getUserId());
        values.put(DBInfo.Table.USER_NAME, userInfo.getUserName());
        values.put(DBInfo.Table.PHONE_NUMBER, userInfo.getPhoneNumber());
        values.put(DBInfo.Table.ACCESS_TOKEN, userInfo.getAccessToken());
        values.put(DBInfo.Table.DESCRIPTION, userInfo.getDescription());

        ByteArrayOutputStream baos = getByteArrayOutputStream(userInfo.getUserHead());
        values.put(DBInfo.Table.USER_HEAD, baos.toByteArray());
        return values;
    }

    @NonNull
    public static ByteArrayOutputStream getByteArrayOutputStream(Bitmap img) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos;
    }


    public UserInfo(String userId, String userName, String accessToken, String phoneNumber, String description, Bitmap userHead) {
        mUserId = userId;
        mUserName = userName;
        mAccessToken = accessToken;
        mPhoneNumber = phoneNumber;
        mDescription = description;
        mUserHead = userHead;
    }

    /**
     * 由cursor创建 user
     *
     * @param cursor
     * @return
     */
    public static UserInfo createUserFromCursor(Cursor cursor) {
        long _id = cursor.getLong(cursor.getColumnIndex(DBInfo.Table._ID));
        String userId = cursor.getString(cursor.getColumnIndex(DBInfo.Table.USER_ID));
        String userName = cursor.getString(cursor.getColumnIndex(DBInfo.Table.USER_NAME));
        String accessToken = cursor.getString(cursor.getColumnIndex(DBInfo.Table.ACCESS_TOKEN));
        String phoneNumber = cursor.getString(cursor.getColumnIndex(DBInfo.Table.PHONE_NUMBER));
        String description = cursor.getString(cursor.getColumnIndex(DBInfo.Table.DESCRIPTION));

        byte[] userHeadByte = cursor.getBlob(cursor.getColumnIndex(DBInfo.Table.USER_HEAD));
        ByteArrayInputStream bais = new ByteArrayInputStream(userHeadByte);
        Bitmap userHead = BitmapFactory.decodeStream(bais);
        return new UserInfo(_id, userId, userName, accessToken, phoneNumber, description, userHead);
    }


    public static Bundle createBundle(UserInfo userInfo) {
        Bundle tokenBundle = new Bundle();
        tokenBundle.putString(Oauth2AccessToken.KEY_UID, userInfo.getUserId());
        tokenBundle.putString(Oauth2AccessToken.KEY_ACCESS_TOKEN, userInfo.getAccessToken());
        tokenBundle.putString(Oauth2AccessToken.KEY_PHONE_NUM, userInfo.getPhoneNumber());
        tokenBundle.putString(Oauth2AccessToken.KEY_EXPIRES_IN, "");
        tokenBundle.putString(Oauth2AccessToken.KEY_REFRESH_TOKEN, "");
        return tokenBundle;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Bitmap getUserHead() {
        return mUserHead;
    }

    public void setUserHead(Bitmap userHead) {
        mUserHead = userHead;
    }
}
