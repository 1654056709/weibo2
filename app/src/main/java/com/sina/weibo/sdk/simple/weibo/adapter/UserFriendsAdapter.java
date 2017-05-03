package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.event.ShowUserFriendsWebEvent;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;
import com.sina.weibo.sdk.simple.weibo.ui.activity.UserFriendsWbActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by John on 2017/4/2.
 */

public class UserFriendsAdapter extends RecyclerView.Adapter<UserFriendsAdapter.UserFriendsHolder> {
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

    /**
     * Created by John on 2017/4/2.
     */

    public class UserFriendsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_user_friend_screen_name)
        TextView mItemUserFriendScreenName;
        @BindView(R.id.item_user_friend_weibo_content)
        TextView mItemUserFriendWeiboContent;
        @BindView(R.id.item_user_friend_head)
        ImageView mItemUserFriendHead;

        private CommonFriendsInfo.UsersBean mUsersBean;
        private Context mContext;

        public UserFriendsHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, UserFriendsWbActivity.class));
                    EventBus.getDefault().postSticky(new ShowUserFriendsWebEvent(mUsersBean));
                }
            });
        }

        public void bindFriends(CommonFriendsInfo.UsersBean usersBean) {
            mUsersBean = usersBean;
            Glide.with(mContext)
                    .load(usersBean.getProfile_image_url())
                    .centerCrop()
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(mItemUserFriendHead);
            mItemUserFriendScreenName.setText(usersBean.getScreen_name());
            mItemUserFriendWeiboContent.setText(usersBean.getDescription());

        }
    }
}
