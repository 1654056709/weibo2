package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.ui.activity.ShowImageActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.WeiboContentActivity;
import com.sina.weibo.sdk.simple.weibo.util.Tools;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by John on 2017/4/1.
 * <p>
 * RecyclerView的ViewHolder
 * ViewHolder管理一个ItemView
 * 1. 让ViewHolder为ItemView监听用户触摸事件
 */

public class WeiboHolder extends RecyclerView.ViewHolder {

    private final TextView mWeiboContentDateTextView;
    private WeiboInfo mWeibo;
    private Context mContext;
    private final ImageView mWeiboContentImgImageView;
    private final ImageView mUserHeadImageView;
    private final TextView mUserNameTextView;
    private final TextView mWeiboContentTextView;


    public WeiboHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long weiboId = Long.valueOf(mWeibo.getWeiboId());
                mContext.startActivity(WeiboContentActivity.newIntent(mContext, weiboId));
            }
        });
        mUserHeadImageView = (ImageView) itemView.findViewById(R.id.item_user_weibo_user_head);
        mUserNameTextView = (TextView) itemView.findViewById(R.id.item_user_weibo_user_name);
        mWeiboContentTextView = (TextView) itemView.findViewById(R.id.item_user_weibo_content);
        mWeiboContentDateTextView = (TextView) itemView.findViewById(R.id.item_user_weibo_content_date);
        mWeiboContentImgImageView = (ImageView) itemView.findViewById(R.id.item_user_weibo_user_content_image);
        mWeiboContentImgImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(ShowImageActivity.newIntent(mContext, mWeibo.getOriginPicUrl()));
            }
        });
    }


    public void bindWeibo(WeiboInfo weibo) {
        //当前weibo对象
        mWeibo = weibo;
        Glide.with(mContext)
                .load(weibo.getUserHead())
                .centerCrop()
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(mUserHeadImageView);
        mUserNameTextView.setText(weibo.getUserName());
        mWeiboContentDateTextView.setText(Tools.dateFormat(weibo.getDate()));
        mWeiboContentTextView.setText(weibo.getContent());
        if (weibo.getHaveImg()) {
            mWeiboContentImgImageView.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(weibo.getImageUrl())
                    .fitCenter()
                    .crossFade()
                    .into(mWeiboContentImgImageView);
        } else {
            mWeiboContentImgImageView.setVisibility(View.GONE);
        }
    }
}