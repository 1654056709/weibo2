package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.holder.CommentHolder;
import com.sina.weibo.sdk.simple.weibo.model.CommonComment;

import java.util.List;

/**
 * Created by John on 2017/4/7.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {
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


}
