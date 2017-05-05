package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.event.WeiboPublisherEvent;
import com.sina.weibo.sdk.simple.weibo.model.CommonFriendsInfo;
import com.sina.weibo.sdk.simple.weibo.ui.activity.UserFriendsWbActivity;
import com.sina.weibo.sdk.simple.weibo.util.Tools;

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
        holder.bindData(mUsersBeanList.get(position));
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

        public UserFriendsHolder(final Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;
            //加载监听
            initListener(context, itemView);
        }


        /**
         * 初始化监听
         *
         * @param context
         * @param itemView
         */
        private void initListener(final Context context, View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserFriendsWbActivity.sendShowUserFriendsWebEvent(
                            context,
                            new WeiboPublisherEvent(mUsersBean.getProfile_url(), mUsersBean.getScreen_name())
                    );
                }
            });
        }


        /**
         * 绑定数据
         *
         * @param usersBean
         */
        public void bindData(CommonFriendsInfo.UsersBean usersBean) {
            mUsersBean = usersBean;
            //设置发布者信息
            setPublisherInfo(usersBean);
            //设置发布内容
            setPublisherContent(usersBean);
        }


        /**
         * 设置发布内容
         *
         * @param usersBean
         */
        private void setPublisherContent(CommonFriendsInfo.UsersBean usersBean) {
            Tools.setWeiboTextContent(mContext,usersBean.getDescription(),mItemUserFriendWeiboContent);
        }

        /**
         * 设置发布者信息
         *
         * @param usersBean
         */
        private void setPublisherInfo(CommonFriendsInfo.UsersBean usersBean) {
            Glide.with(mContext)
                    .load(usersBean.getProfile_image_url())
                    .centerCrop()
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(mItemUserFriendHead);
            mItemUserFriendScreenName.setText(usersBean.getScreen_name());
        }
    }
}
