package com.sina.weibo.sdk.simple.weibo.util;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by John on 2017/4/9.
 */

public class HttpUtil {
    public interface HttpCallback {
        void onSuccess(String msg);

        void onFailure(String msg);
    }

    /**
     * 发微博
     *
     * @param accessToken
     * @param status
     * @param callback
     */
    public static void sendWeibo(final Oauth2AccessToken accessToken, final String status, final HttpCallback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String spec = "https://api.weibo.com/2/statuses/update.json";
                    URL url = new URL(spec);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setReadTimeout(5000);
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setRequestProperty("Connection", "keep-alive");
                    urlConnection.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");

                    String data = "access_token=" + URLEncoder.encode(accessToken.getToken(), "UTF-8")
                            + "&status=" + URLEncoder.encode(status, "UTF-8");
                    urlConnection.setRequestProperty("Content-Length",
                            String.valueOf(data.getBytes().length));
                    urlConnection
                            .setRequestProperty("User-Agent",
                                    "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);

                    OutputStream os = urlConnection.getOutputStream();
                    os.write(data.getBytes());
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
}
