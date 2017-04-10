package com.sina.weibo.sdk.simple.weibo.net;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by John on 2017/4/9.
 */

public class HttpUtil {
    //发微博
    private static final String SEND_WEIBO = "https://api.weibo.com/2/statuses/update.json";
    //删除微博
    private static final String DELETE_WEIBO = "https://api.weibo.com/2/statuses/destroy.json";
    //转发微博
    private static final String TRANSPOND_WEIBO = "https://api.weibo.com/2/statuses/repost.json";

    public interface HttpCallback {
        void onSuccess(String msg);

        void onFailure(String msg);
    }

    /**
     * 通用方法
     *
     * @param urlString
     * @param data
     * @param callback
     */
    private static void updateWeib(final String urlString, final Map<String, String> data, final HttpCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    urlConnection.setRequestProperty("Connection", "keep-alive");
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Map.Entry<String, String> element : data.entrySet()) {
                        stringBuffer.append(element.getKey() + "=" + URLEncoder.encode(element.getValue()));
                        stringBuffer.append("&");
                    }
                    String dataStr = stringBuffer.substring(0, stringBuffer.length() - 1);
                    urlConnection.setRequestProperty("Content-Length", String.valueOf(dataStr.getBytes().length));
                    urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);

                    OutputStream os = urlConnection.getOutputStream();
                    os.write(dataStr.getBytes());
                    os.flush();
                    if (urlConnection.getResponseCode() == 200) {
                        InputStream is = urlConnection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int len = 0;
                        byte buffer[] = new byte[1024];
                        while ((len = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        is.close();
                        baos.close();
                        String result = new String(baos.toByteArray());
                        if (callback != null) {
                            callback.onSuccess(result);
                        }
                    } else {
                        callback.onFailure("失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFailure("失败");
                }
            }
        }).start();

    }


    /**
     * 发微博
     *
     * @param accessToken
     * @param status
     * @param callback
     */
    public static void sendWeibo(final Oauth2AccessToken accessToken, final String status, final HttpCallback callback) {
        Map<String, String> data = new HashMap<>();
        data.put("access_token", accessToken.getToken());
        data.put("status", status);
        updateWeib(SEND_WEIBO, data, callback);
    }


    /**
     * 删除自己所发微博
     *
     * @param accessToken
     * @param id
     * @param callback
     */
    public static void deleteWeibo(Oauth2AccessToken accessToken, long id, HttpCallback callback) {
        Map<String, String> data = new HashMap<>();
        data.put("access_token", accessToken.getToken());
        data.put("id", String.valueOf(id));
        updateWeib(DELETE_WEIBO, data, callback);
    }


    /**
     * 转发微博
     *
     * @param accessToken
     * @param id
     * @param callback
     */
    public static void transPondWeibo(Oauth2AccessToken accessToken, long id, HttpCallback callback) {
        Map<String, String> data = new HashMap<>();
        data.put("access_token", accessToken.getToken());
        data.put("id", String.valueOf(id));
        updateWeib(TRANSPOND_WEIBO, data, callback);
    }


}
