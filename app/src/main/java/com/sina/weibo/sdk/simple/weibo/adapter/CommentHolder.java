package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.util.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by John on 2017/4/7.
 */

public class CommentHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_user_weibo_user_head)
    ImageView mItemUserWeiboUserHead;
    @BindView(R.id.item_user_weibo_user_name)
    TextView mItemUserWeiboUserName;
    @BindView(R.id.item_user_weibo_content)
    TextView mItemUserWeiboContent;
    @BindView(R.id.item_user_weibo_content_date)
    TextView mItemUserWeiboContentDate;
    private CommonComment.CommentsBean mCommentsBean;
    private Context mContext;

    public CommentHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    public void bindComment(CommonComment.CommentsBean commentsBean) {
        mCommentsBean = commentsBean;
        Glide.with(mContext)
                .load(commentsBean.getUser().getProfile_url())
                .bitmapTransform(new CropCircleTransformation(mContext))
                .centerCrop()
                .into(mItemUserWeiboUserHead);

        mItemUserWeiboContent.setText(commentsBean.getText());

        mItemUserWeiboContentDate.setText(Tools.dateFormat(commentsBean.getCreated_at()));
    }

}
