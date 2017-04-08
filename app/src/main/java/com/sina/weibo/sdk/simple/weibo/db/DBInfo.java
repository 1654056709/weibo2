package com.sina.weibo.sdk.simple.weibo.db;

/**
 * Created by John on 2017/3/22.
 */

public class DBInfo {
    /**
     * 数据库常量
     */
    public interface DB {
        /**
         * 数据库名称
         */
        String DB_NAME = "weibo.db";

        /**
         * 数据库版本
         */
        int VERSION = 1;
    }

    /**
     * 数据库中的表常量
     */
    public interface Table {
        /**
         * 表名称
         */
        String USER_TABLE = "user";

        /**
         * 主键
         */
        String _ID = "_id";

        /**
         * 用户编号
         */
        String USER_ID = "user_id";

        /**
         * 用户姓名
         */
        String USER_NAME = "user_name";

        /**
         * access token
         */
        String ACCESS_TOKEN = "access_token";

        /**
         *手机号码
         */
        String PHONE_NUMBER = "phone_number";


        /**
         * 用户描述
         */
        String DESCRIPTION = "description";

        /**
         * 用户头像
         */
        String USER_HEAD = "user_head";


        /**
         * 创建表语句
         */
        String CREATE_USER_TABLE = "create table if not exists " + USER_TABLE
                + "(" +
                _ID + " integer primary key autoincrement," +
                USER_ID + " text," +
                USER_NAME + " text," +
                ACCESS_TOKEN + " text," +
                DESCRIPTION + " text," +
                PHONE_NUMBER + " text," +
                USER_HEAD + " blob" +
                ");";


        /**
         * 删除用户表
         */
        String DROP_TABLE = "drop table " + USER_TABLE;
    }
}
