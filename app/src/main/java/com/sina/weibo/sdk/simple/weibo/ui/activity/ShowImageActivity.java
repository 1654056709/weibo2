package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sina.weibo.sdk.simple.weibo.R;

import java.io.File;
import java.util.zip.DeflaterOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 显示图片
 */

public class ShowImageActivity extends AppCompatActivity {
    private static final String IMG_URL = "img_url";

    @BindView(R.id.activity_show_img)
    ImageView mActivityShowImg;
    @BindView(R.id.activity_show_img_close)
    ImageView mActivityShowImgClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String imgUrl = getIntent().getStringExtra(IMG_URL);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);

        Glide.with(this)
                .load(imgUrl)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                        BitmapFactory.Options options = getOptions(resource);
                        DisplayMetrics metrics = getDisplayMetrics(ShowImageActivity.this);
                        float width = metrics.widthPixels;
                        float height = Math.round(options.outHeight * (width / options.outWidth));
                        Glide.with(ShowImageActivity.this)
                                .load(Uri.fromFile(resource))
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .override((int) width, (int) height)
                                .fitCenter()
                                .into(mActivityShowImg);
                    }
                });
    }

    public static Intent newIntent(Context context, String imgUrl) {
        Intent intent = new Intent(context, ShowImageActivity.class);
        intent.putExtra(IMG_URL, imgUrl);
        return intent;
    }

    /**
     * 得到图片的宽和高
     *
     * @param resource
     * @return
     */
    @NonNull
    private BitmapFactory.Options getOptions(File resource) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(resource.getAbsolutePath(), options);
        return options;
    }


    /**
     * 得到屏幕宽和高
     *
     * @return
     */
    @NonNull
    private DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    @OnClick(R.id.activity_show_img_close)
    public void onClick() {
        finish();
    }
}
