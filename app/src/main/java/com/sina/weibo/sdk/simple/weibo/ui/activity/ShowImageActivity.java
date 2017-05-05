package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.ImagePagerAdapter;
import com.sina.weibo.sdk.simple.weibo.event.ImageEvent;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.ImageFragment;
import com.sina.weibo.sdk.simple.weibo.ui.view.CustomViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 显示图片
 */

public class ShowImageActivity extends AppCompatActivity {
    @BindView(R.id.activity_show_img_close)
    ImageView mActivityShowImgClose;
    @BindView(R.id.image_title)
    TextView mImageTitle;
    @BindView(R.id.view_page_show_imgs)
    CustomViewPager mViewPageShowImgs;

    private ImageEvent mImageEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);

        //初始化ViewPager
        initViewPager();
        //初始化数据
        initData();
        //初始化监听
        initListener();

    }

    /**
     * 初始化数据
     */
    private void initData() {


    }

    /**
     * 初始化监听
     */
    private void initListener() {
        //页面切换监听
        mViewPageShowImgs.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mImageTitle.setText((position + 1) + "/" + mImageEvent.getUrls().size());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        List<String> urls = mImageEvent.getUrls();
        List<Fragment> fragments = new ArrayList<>();
        for (String url : urls) {
            fragments.add(ImageFragment.newInstance(url));
        }
        mViewPageShowImgs.setAdapter(new ImagePagerAdapter(getSupportFragmentManager(), fragments));
        mViewPageShowImgs.setCurrentItem(mImageEvent.getCurrentPos());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onImageEvent(ImageEvent imageEvent) {
        mImageEvent = imageEvent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    public static void sendImageEvent(Context context, ImageEvent imageEvent) {
        context.startActivity(new Intent(context, ShowImageActivity.class));
        EventBus.getDefault().postSticky(imageEvent);
    }

    @OnClick(R.id.activity_show_img_close)
    public void onClick() {
        finish();
    }
}
