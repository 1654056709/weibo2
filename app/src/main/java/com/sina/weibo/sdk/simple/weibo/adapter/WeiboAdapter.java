package com.sina.weibo.sdk.simple.weibo.adapter;

/**
 * Created by John on 2017/4/1.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.holder.WeiboHolder;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;

import java.util.List;

/**
 * RecyclerView的Adapter
 * 将数据和对应视图数据绑定到一起
 */
public class WeiboAdapter extends RecyclerView.Adapter<WeiboHolder> {
    private List<WeiboInfo> mWeibos;
    private Context mContext;
    private String mType;


    public WeiboAdapter(Context context, List<WeiboInfo> weibos) {
        mContext = context;
        mWeibos = weibos;
    }


    public List<WeiboInfo> getWeibos() {
        return mWeibos;
    }

    public void setWeibos(List<WeiboInfo> weibos) {
        mWeibos = weibos;
    }

    /**
     * 设置编辑模式
     *
     * @param type
     */
    public void setEditType(String type) {
        mType = type;
        notifyDataSetChanged();
    }


    /**
     * 得到编辑模式
     *
     * @return
     */
    public String getEditType() {
        return mType;
    }

    @Override
    public WeiboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_user_weibo, null);
        return new WeiboHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(WeiboHolder holder, int position) {
        holder.bindWeibo(mWeibos.get(position), mType);
    }


    @Override
    public int getItemCount() {
        return mWeibos.size();
    }



}