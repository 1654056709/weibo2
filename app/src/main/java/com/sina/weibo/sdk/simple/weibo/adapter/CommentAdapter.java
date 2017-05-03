package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;
import com.sina.weibo.sdk.simple.weibo.util.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by John on 2017/4/7.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private Context mContext;
    private List<CommonComment.CommentsBean> mCommentsBeanList;

    public CommentAdapter(Context context, List<CommonComment.CommentsBean> commentsBeanList) {
        mContext = context;
        mCommentsBeanList = commentsBeanList;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_fragment_comment, null);
        return new CommentHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        holder.bindComment(mCommentsBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCommentsBeanList.size();
    }


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
                    .load(commentsBean.getUser().getProfile_image_url())
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(mItemUserWeiboUserHead);

            mItemUserWeiboUserName.setText(commentsBean.getUser().getName());
            mItemUserWeiboContentDate.setText(Tools.dateFormat(commentsBean.getCreated_at()));
            mItemUserWeiboContent.setText(commentsBean.getText());
        }

    }
}
