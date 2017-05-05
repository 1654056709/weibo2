package com.sina.weibo.sdk.simple.weibo.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sina.weibo.sdk.simple.weibo.db.DBHelper;
import com.sina.weibo.sdk.simple.weibo.db.DBInfo;
import com.sina.weibo.sdk.simple.weibo.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/22.
 * 用户管理
 */

public class UserDao {
    private static volatile UserDao sUserDao;
    private DBHelper mDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private UserDao(Context context) {
        mDBHelper = new DBHelper(context);
    }


    /**
     * 单利模式 （双重校验锁）
     *
     * @param context
     * @return
     */
    public static UserDao getInstance(Context context) {
        if (sUserDao == null) {
            synchronized (UserDao.class) {
                if (sUserDao == null) {
                    sUserDao = new UserDao(context);
                }
            }
        }
        return sUserDao;
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @return
     */
    public long insertUser(UserInfo userInfo) {
        mSQLiteDatabase = mDBHelper.getReadableDatabase();
        long rowId = mSQLiteDatabase.insert(DBInfo.Table.USER_TABLE, null, UserInfo.getContentValues(userInfo));
        mSQLiteDatabase.close();
        return rowId;
    }


    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    public long deleteUser(String userId) {
        mSQLiteDatabase = mDBHelper.getReadableDatabase();
        long rowId = mSQLiteDatabase.delete(DBInfo.Table.USER_TABLE, DBInfo.Table.USER_ID + "=?", new String[]{userId});
        mSQLiteDatabase.close();
        return rowId;
    }


    /**
     * 修改用户
     *
     * @param userInfo
     * @return
     */
    public long updateUser(UserInfo userInfo) {
        long affectedRows = 0;
        affectedRows = mSQLiteDatabase.update(DBInfo.Table.USER_TABLE, UserInfo.getContentValues(userInfo), DBInfo.Table.USER_ID + "=?", new String[]{userInfo.getUserId()});
        return affectedRows;
    }


    /**
     * 查找用户
     *
     * @param userId
     * @return
     */
    public UserInfo findUser(String userId) {
        UserInfo userInfo = null;
        mSQLiteDatabase = mDBHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(DBInfo.Table.USER_TABLE, null, DBInfo.Table.USER_ID + "=?", new String[]{userId}, null, null, null);
        if (cursor.moveToFirst()) {
            userInfo = UserInfo.createUserFromCursor(cursor);
        }
        mSQLiteDatabase.close();
        return userInfo;
    }


    /**
     * 查找所有用户
     *
     * @return
     */
    public List<UserInfo> findAll() {
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        mSQLiteDatabase = mDBHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(DBInfo.Table.USER_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            userInfos.add(UserInfo.createUserFromCursor(cursor));
        }
        mSQLiteDatabase.close();

        return userInfos;
    }

}
