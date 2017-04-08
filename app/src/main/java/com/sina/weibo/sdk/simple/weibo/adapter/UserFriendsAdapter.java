package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by John on 2017/4/2.
 */

public class UserFriendsAdapter extends RecyclerView.Adapter<UserFriendsHolder> {
    private List<CommonFriendsInfo.UsersBean> mUsersBeanList;
    private Context mContext;

    public UserFriendsAdapter(Context context, List<CommonFriendsInfo.UsersBean> usersBeen) {
        mContext = context;
        mUsersBeanList = usersBeen;
    }

    @Override
    public UserFriendsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_user_friend, null);
        return new UserFriendsHolder(mContext, itemView);
    }

    @Override
    public void onBindViewHolder(UserFriendsHolder holder, int position) {
        holder.bindFriends(mUsersBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsersBeanList.size();
    }

}
