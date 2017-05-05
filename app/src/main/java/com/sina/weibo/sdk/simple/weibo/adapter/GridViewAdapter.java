package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sina.weibo.sdk.simple.weibo.R;

import java.util.List;

/**
 * Created by John on 2017/5/3.
 * GridView适配器
 */

public class GridViewAdapter extends BaseAdapter {
    private List<String> mImgs;
    private Context mContext;

    public GridViewAdapter(Context context, List<String> imgs) {
        mImgs = imgs;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImgs.size();
    }

    @Override
    public Object getItem(int position) {
        return mImgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_imgs_grid_view, null);
        }
        GridViewHolder viewHolder = getViewHolder(mContext, convertView);
        viewHolder.bindData(mImgs.get(position));
        return convertView;
    }

    private GridViewHolder getViewHolder(Context context, View view) {
        GridViewHolder viewHolder = (GridViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new GridViewHolder(mContext, view);
            view.setTag(viewHolder);
        }
        return viewHolder;
    }

    class GridViewHolder {
        private ImageView mImageView;
        private Context mContext;

        public GridViewHolder(Context context, View view) {
            mImageView = (ImageView) view.findViewById(R.id.item_grid_view_img);
            mContext = context;
        }

        public void bindData(String url) {
            url = url.replace("thumbnail", "bmiddle");
            Glide.with(mContext)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(mImageView);
        }
    }
}
