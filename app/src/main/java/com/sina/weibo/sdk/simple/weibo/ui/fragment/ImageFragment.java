package com.sina.weibo.sdk.simple.weibo.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import butterknife.Unbinder;

/**
 * Created by John on 2017/5/3.
 * 显示图片细节
 */

public class ImageFragment extends Fragment {
    private static final String IMG_URL = "img_url";

    private File mFile;
    Unbinder unbinder;

    @BindView(R.id.fragment_image_view_show)
    ImageView mFragmentImageViewShow;

    private String mUrl;
    private AlertDialog mDialog;
    private TextView mSaveImgTextView;

    public static ImageFragment newInstance(String url) {
        Bundle args = new Bundle();
        ImageFragment fragment = new ImageFragment();
        args.putString(IMG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString(IMG_URL);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_show_img, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDialog();
        showImage(mUrl);
        return view;
    }

    private void initDialog() {
        View view = View.inflate(getActivity(), R.layout.dialog_save_img, null);
        mDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        mSaveImgTextView = ButterKnife.findById(view, R.id.save_img);

        mSaveImgTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存图片
                try {
                    String suffix = getUrlSuffix(mUrl);
                    saveFile(getActivity(), mFile, suffix);
                    mDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 得到url后缀
     *
     * @return
     */
    @NonNull
    private String getUrlSuffix(String url) {
        return url.substring(url.lastIndexOf("."), url.length());
    }

    /**
     * 保存图片
     *
     * @param context
     * @param file
     * @param suffix
     * @throws Exception
     */
    private void saveFile(final Context context, final File file, final String suffix) throws Exception {
        File dir = Environment.getExternalStorageDirectory();
        File imgDir = new File(dir, "simple/weibo/imgs");
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }
        File imgFile = new File(imgDir, file.getName() + suffix);

        if (imgFile.exists()) {
            ToastUtil.showToasts(context, "图片已经保存过了");
        } else {
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(file);
                os = new FileOutputStream(imgFile);
                byte[] buffer = new byte[1024];
                int len = -1;

                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                    //可以添加进度回调
                }
                ToastUtil.showToasts(context, "保存成功");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtil.showToasts(context, "未知错误");
            } finally {
                try {
                    is.close();
                    os.close();
                } catch (Exception e) {

                }
            }
        }
    }


    /**
     * 显示图片
     *
     * @param url 图片地址
     */
    private void showImage(String url) {
        final String suffix = getUrlSuffix(url);


        Glide.with(this)
                .load(url.replace("thumbnail", "bmiddle"))
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                        mFile = resource;
                        BitmapFactory.Options options = Tools.getOptions(resource);
                        DisplayMetrics metrics = Tools.getDisplayMetrics(getContext());
                        float width = metrics.widthPixels;
                        float height = Math.round(options.outHeight * (width / options.outWidth));

                        Glide.with(getContext())
                                .load(Uri.fromFile(resource))
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .override((int) width, (int) height)
                                .into(mFragmentImageViewShow);

                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnLongClick(R.id.fragment_image_view_show)
    public boolean onLongClick(View view) {
        mDialog.show();
        return true;
    }
}
