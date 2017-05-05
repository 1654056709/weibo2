package com.sina.weibo.sdk.simple.weibo.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.ui.dialog.WriteInfoDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by John on 2017/3/21.
 * 业务处理工具集
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


    /**
     * 检查是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean checkNetWork(final Context context) {
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
            return false;
        }
        return true;
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


    /**
     * RecyclerView移动到指定位置
     *
     * @param manager
     * @param mRecyclerView
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        int top = mRecyclerView.getChildAt(lastItem - firstItem).getTop();
        mRecyclerView.smoothScrollBy(0, top);
    }


    /**
     * RecycView为空时，视图
     *
     * @param emptyView
     * @param swipeToLoadLayout
     * @param recyclerView
     */
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


    /**
     * RecyclerView滑动监听
     *
     * @param context
     * @param recyclerView
     * @param callback
     */
    public static void scrollLinstener(final Context context, RecyclerView recyclerView, final ScrollCallback callback) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int slopDistance = ViewConfiguration.get(context).getScaledTouchSlop();
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

    /**
     * 将图片转换为字节数组
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmap2OutputStream(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 得到屏幕宽和高
     *
     * @return
     */
    @NonNull
    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 得到图片的宽和高
     *
     * @param resource
     * @return
     */
    @NonNull
    public static BitmapFactory.Options getOptions(File resource) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(resource.getAbsolutePath(), options);
        return options;
    }


    /**
     * 关闭当前进程
     */
    public static void quitProcess() {
        int pid = Process.myPid();
        Process.killProcess(pid);
    }


    /**
     * 动态设定GridView宽和高
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        int totalWidth = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
            if (totalWidth == 0) {
                totalWidth = 3 * listItem.getMeasuredWidth();
            }
        }
        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        params.width = totalWidth;
        // 设置参数
        listView.setLayoutParams(params);
    }

    /**
     * 处理微博内容
     *
     * @param context
     * @param content
     * @param textView
     */
    public static void setWeiboTextContent(final Context context, final String content, TextView textView) {
        String regex = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(content);

        String[] urls = content.split(regex);
        if (urls.length > 0) {
            for (int i = 0; i < urls.length; i++) {
                builder.append(urls[i]);
                if (matcher.find()) {
                    final String url = matcher.group(0);
                    final SpannableString spannableString = new SpannableString(url);
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            Uri uri = Uri.parse(url.replaceAll("[\\s]+", ""));
                            intent.setData(uri);
                            context.startActivity(intent);
                        }
                    }, 0, url.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, url.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.append(spannableString);
                }
            }
            textView.setText(builder);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }


    /**
     * 包装加载更多
     */

    public interface LoadMoreCallback {
        void loadMoreData();
    }

    public static void decideData(Context context, List datas, LoadMoreCallback callback) {
        if (datas.size() > 0) {
            callback.loadMoreData();
        } else {
            ToastUtil.showToasts(context, context.getResources().getString(R.string.no_more_data));
        }
    }
}

