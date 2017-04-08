package com.sina.weibo.sdk.simple.weibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.CommentViewPagerAdapter;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.MentionUserCommentFragment;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.MentionUserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MentionUserActivity extends AppCompatActivity {


    @BindView(R.id.title_bar_title)
    TextView mTitleBarTitle;
    @BindView(R.id.title_bar_write_image_view)
    ImageView mTitleBarWriteImageView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_mention_user_tab_layout)
    TabLayout mActivityMentionUserTabLayout;
    @BindView(R.id.activity_mention_user_viewpage)
    ViewPager mActivityMentionUserViewpage;
    private List<String> mTitles;
    private List<Fragment> mFragments;
    private CommentViewPagerAdapter mCommentViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mention_user);
        ButterKnife.bind(this);
        initToolbar();
        initTabLayout();
        initViewPager();
        mCommentViewPagerAdapter = new CommentViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mActivityMentionUserViewpage.setAdapter(mCommentViewPagerAdapter);
        mActivityMentionUserTabLayout.setupWithViewPager(mActivityMentionUserViewpage);
        mTitleBarTitle.setText("@我");
    }

    private void initToolbar() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(new MentionUserFragment());
        mFragments.add(new MentionUserCommentFragment());
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout() {
        mTitles = new ArrayList<>();
        mTitles.add("微博");
        mTitles.add("评论");
        for (String title : mTitles) {
            mActivityMentionUserTabLayout.addTab(
                    mActivityMentionUserTabLayout.newTab()
                            .setText(title)
            );
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MentionUserActivity.class);
    }

    @OnClick(R.id.title_bar_write_image_view)
    public void onClick() {
    }
}
