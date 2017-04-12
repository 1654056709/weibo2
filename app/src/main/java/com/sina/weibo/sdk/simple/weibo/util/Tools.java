package com.sina.weibo.sdk.simple.weibo.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.system.StructStat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.ui.dialog.WriteInfoDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by John on 2017/3/21.
 */

public class Tools {
    /**
     * 判断是否由网络连接
     *
     * @param context
     * @return true：有网络连接   false:没有网络连接
     */
    public static boolean isNetWorkConnected(Context context) {
        //判断师是否联网
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] netWorkInfos = connectivityManager.getAllNetworkInfo();
            for (NetworkInfo info : netWorkInfos) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void checkNetWork(final Context context) {
        if (!isNetWorkConnected(context)) {
            //没有网络连接
            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.ic_network_hint)
                    .setTitle(R.string.network_hint)
                    .setMessage(R.string.have_not_network)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Activity currentActivity = (Activity) context;
                            currentActivity.finish();
                        }
                    })
                    .create().show();
        }
    }


    /**
     * 进度回调
     *
     * @param context
     * @param callBack
     */
    public static void progressCallback(Context context, ProgressFinishedCallBack callBack) {
        try {
            ProgressDialog progressDialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("数据加载中...");
            progressDialog.show();
            Thread.sleep(1000);
            callBack.onSuccess(progressDialog);
        } catch (Exception e) {
            ToastUtil.showToasts(context, "错误");
            callBack.onFailure(e.getMessage());
        }
    }

    public interface ProgressFinishedCallBack {
        void onSuccess(ProgressDialog progressDialog);

        void onFailure(String errorMsg);
    }


    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String dateFormat(String date) {
        String dateFormat = null;
        String pattern = null;
        Date contentDate = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (DateUtils.isToday(contentDate.getTime())) {
            pattern = "今天/HH:mm:ss";
        } else {
            pattern = "yyyy-MM-dd/HH:mm";
        }
        sdf.applyPattern(pattern);
        dateFormat = sdf.format(contentDate);
        return dateFormat;
    }


    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        int top = mRecyclerView.getChildAt(lastItem - firstItem).getTop();
        mRecyclerView.smoothScrollBy(0, top);
    }


    public static void showEmptyView(android.view.View emptyView, SwipeToLoadLayout swipeToLoadLayout, RecyclerView recyclerView) {
        boolean flag = recyclerView.getAdapter().getItemCount() == 0 ? true : false;
        emptyView.setVisibility(flag ? android.view.View.VISIBLE : android.view.View.GONE);
        swipeToLoadLayout.setVisibility(flag ? android.view.View.GONE : android.view.View.VISIBLE);
    }

    /**
     * 初始化标题
     *
     * @param nextCursor
     * @param totalNum
     */
    public static void initTitle(int nextCursor, int totalNum, TextView view) {
        int totalPage = 0;
        if (totalNum % 50 == 0) {
            totalPage = totalNum / 50;
        } else {
            totalPage = totalNum / 50 + 1;
        }

        int page = nextCursor / 50;
        if (nextCursor == 0) {
            page = totalPage;
        }
        view.setText(page + "/" + totalPage);
    }


    /**
     * 写微博dialog
     *
     * @param fragmentManager
     * @param tag
     */
    public static final String WRITE_WEIBO = "write_weibo";

    public static WriteInfoDialog openWriteWeibo(FragmentManager fragmentManager, String tag, long weiboId) {
        WriteInfoDialog writeInfoDialog = WriteInfoDialog.newWriteInfoDialog(WRITE_WEIBO, weiboId);
        writeInfoDialog.show(fragmentManager, tag);
        return writeInfoDialog;
    }


    /**
     * 评论
     */
    public static final String WRITE_COMMENT = "write_comment";

    public static WriteInfoDialog openWriteComment(FragmentManager fragmentManager, String tag, long weiboId) {
        WriteInfoDialog writeInfoDialog = WriteInfoDialog.newWriteInfoDialog(WRITE_COMMENT, weiboId);
        writeInfoDialog.show(fragmentManager, tag);
        return writeInfoDialog;
    }

    /**
     * 转换字符串
     *
     * @param num
     * @return
     */
    public static String number2Str(long num) {
        String numStr = null;
        if (num < 1000) {
            numStr = String.valueOf(num);
        } else if (num >= 1000) {
            numStr = num / 1000 + "K";
        } else if (num >= 10000) {
            numStr = num / 10000 + "W";
        }
        return numStr;
    }


    /**
     * 抖动动画
     *
     * @param view
     */
    public static void shakeAnimation(View view) {
        TranslateAnimation animation = new TranslateAnimation(0, -5, 0, -5);
        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(100);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }


    /**
     * RecyclerView上滑 下滑监听
     */
    public interface ScrollCallback {

        void up();

        void down();
    }

    public static void scrollLinstener(final Context context, RecyclerView recyclerView, final ScrollCallback callback) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int slopDistance = ViewConfiguration.get(context).getScaledDoubleTapSlop() / 4;
                if (Math.abs(dy) > slopDistance) {
                    if (dy > 0) {
                        callback.up();
                    } else if (dy < 0) {
                        callback.down();
                    }
                }

            }
        });
    }
}
