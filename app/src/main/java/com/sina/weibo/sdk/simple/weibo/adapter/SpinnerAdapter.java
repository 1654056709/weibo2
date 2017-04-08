package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.UserInfo;

import java.util.List;

/**
 * 用户列表适配器
 */
public class SpinnerAdapter extends BaseAdapter {
    private List<UserInfo> mUserInfos;
    private Context mContext;

    public SpinnerAdapter(Context context, List<UserInfo> userInfos) {
        mUserInfos = userInfos;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mUserInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return mUserInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_select_user_login, null);
        }
        UserInfo userInfo = mUserInfos.get(i);
        SpinnerViewHolder viewHolder = getViewHolder(view);
        viewHolder.mUserHeadImageView.setImageBitmap(userInfo.getUserHead());
        viewHolder.mUserNameTextView.setText(userInfo.getUserName());
        return view;
    }

    private SpinnerViewHolder getViewHolder(View view) {
        SpinnerViewHolder viewHolder = (SpinnerViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SpinnerViewHolder(view);
            view.setTag(viewHolder);
        }
        return viewHolder;
    }


    class SpinnerViewHolder {
        public ImageView mUserHeadImageView;
        public TextView mUserNameTextView;

        public SpinnerViewHolder(View view) {
            mUserHeadImageView = (ImageView) view.findViewById(R.id.item_select_user_login_user_head_image_view);
            mUserNameTextView = (TextView) view.findViewById(R.id.item_select_user_login_user_name_text_view);
        }
    }
}

