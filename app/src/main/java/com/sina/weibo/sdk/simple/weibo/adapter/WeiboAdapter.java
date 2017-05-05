package com.sina.weibo.sdk.simple.weibo.adapter;

/**
 * Created by John on 2017/4/1.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.event.CommentEvent;
import com.sina.weibo.sdk.simple.weibo.event.ImageEvent;
import com.sina.weibo.sdk.simple.weibo.event.WeiboPublisherEvent;
import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.UpdateWeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.activity.CommentActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.ShowImageActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.UserFriendsWbActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.UserTimeLineActivity;
import com.sina.weibo.sdk.simple.weibo.util.TimeUtils;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.UpdateWeiboInfoView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 显示微博信息RecyclerView适配器
 */
public class WeiboAdapter extends RecyclerView.Adapter<WeiboAdapter.WeiboHolder> {
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
     * 编辑模式
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
        holder.bindData(mWeibos.get(position), mType);
    }


    @Override
    public int getItemCount() {
        return mWeibos.size();
    }


    /**
     * ViewHolder
     */
    public class WeiboHolder extends RecyclerView.ViewHolder {
        private WeiboInfo mWeibo;
        private Context mContext;

        @BindView(R.id.item_user_weibo_user_head)
        ImageView mItemUserWeiboUserHead;
        @BindView(R.id.item_user_weibo_delete)
        ImageView mItemUserWeiboDelete;
        @BindView(R.id.item_user_weibo_user_name)
        TextView mItemUserWeiboUserName;
        @BindView(R.id.item_user_weibo_content_date)
        TextView mItemUserWeiboContentDate;
        @BindView(R.id.item_user_weibo_user_content_images)
        GridView mItemUserWeiboUserContentImage;
        @BindView(R.id.item_user_weibo_content)
        TextView mItemUserWeiboContent;
        @BindView(R.id.weibo_transpond_count)
        TextView mWeiboTranspondCount;
        @BindView(R.id.weibo_comment_count)
        TextView mWeiboCommentCount;
        @BindView(R.id.weibo_favorite_count)
        TextView mWeiboFavoriteCount;
        @BindView(R.id.item_weibo)
        LinearLayout mItemWeibo;


        public WeiboHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;
            initListener(itemView);
        }


        /**
         * 初始化监听
         *
         * @param itemView
         */
        private void initListener(View itemView) {
            //查看微博内容
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserFriendsWbActivity.sendShowUserFriendsWebEvent(mContext, new WeiboPublisherEvent(mWeibo.getProfileUrl(), mWeibo.getUserName()));
                }
            });


            //到ShowImageActivity中显示图片
            mItemUserWeiboUserContentImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Logger.d(mWeibo.getOriginPicUrl());
                    ImageEvent event = new ImageEvent(position, mWeibo.getImgs());
                    ShowImageActivity.sendImageEvent(mContext, event);
                }
            });


            //转发微博
            mWeiboTranspondCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(mContext);
                    if (accessToken.isSessionValid()) {
                        long count = mWeibo.getTranspond();
                        count++;
                        mWeibo.setTranspond(count);
                        mWeiboTranspondCount.setText("转发(" + Tools.number2Str(count) + ")");
                        transPondWeibo(mContext, accessToken, mWeibo.getWeiboId());
                    }
                }
            });


            //微博删除
            mItemUserWeiboDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message message = Message.obtain();
                    message.obj = mWeibo;
                    UserTimeLineActivity.mHandler.sendMessage(message);
                    //删除微博
                    Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(mContext);
                    if (accessToken.isSessionValid()) {
                        deleteWeibo(mContext, accessToken, mWeibo.getWeiboId());
                    }
                }
            });


            //获取某条微博评论
            mWeiboCommentCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //发送粘性事件
                    EventBus.getDefault().postSticky(new CommentEvent(mWeibo));
                    Intent intent = CommentActivity.newIntent(mContext);
                    mContext.startActivity(intent);
                }
            });
        }


        /**
         * 转发微博
         *
         * @param accessToken
         * @param weiboId
         */
        private void transPondWeibo(final Context context, Oauth2AccessToken accessToken, String weiboId) {
            UpdateWeiboInfoPresenter updateWeiboInfoPresenter = new UpdateWeiboInfoPresenter(context);
            updateWeiboInfoPresenter.onCreate();
            updateWeiboInfoPresenter.transpondWeibo(accessToken, Long.valueOf(weiboId));
            updateWeiboInfoPresenter.onAttachView(new UpdateWeiboInfoView() {
                @Override
                public void onSuccess(UpdateWeiboInfo updateWeiboInfo) {

                }

                @Override
                public void onSuccess(String weiboInfo) {
                    ToastUtil.showToasts(context, "转发成功");
                }

                @Override
                public void onFailure(String errorMsg) {
                    ToastUtil.showToasts(context, "转发失败");
                }
            });
            updateWeiboInfoPresenter.onStop();
        }

        /**
         * 删除微博
         *
         * @param context
         * @param accessToken
         * @param weiboId
         */
        private void deleteWeibo(final Context context, Oauth2AccessToken accessToken, String weiboId) {
            UpdateWeiboInfoPresenter updateWeiboInfoPresenter = new UpdateWeiboInfoPresenter(context);
            updateWeiboInfoPresenter.onCreate();
            updateWeiboInfoPresenter.deleteWeiboinfo(accessToken, Long.valueOf(weiboId));
            updateWeiboInfoPresenter.onAttachView(new UpdateWeiboInfoView() {
                @Override
                public void onSuccess(UpdateWeiboInfo updateWeiboInfo) {

                }

                @Override
                public void onSuccess(String weiboInfo) {
                    ToastUtil.showToasts(context, "删除成功");
                }

                @Override
                public void onFailure(String errorMsg) {
                    ToastUtil.showToasts(context, "删除失败");
                }
            });
            updateWeiboInfoPresenter.onStop();

        }

        /**
         * 绑定数据
         *
         * @param weibo
         * @param type
         */
        public void bindData(WeiboInfo weibo, String type) {
            //当前weibo对象
            mWeibo = weibo;
            //判断是否删除微博（在个人微博管理中使用）
            decideWhetherDelWeibo(type);
            // 设置微博发布者信息
            setContentPublisherInfo(weibo);
            //设置日期
            setWeiboDate(weibo);
            //设置微博内容
            setWeiboContent(weibo);
            //设置微博内容图片
            setWeiboContentImages(weibo);
            //设置微博底部内容
            setWeiboFooter(weibo);
        }


        /**
         * 判断当前微博页面是否个人微博页面，如果是就可以进行管理
         *
         * @param type
         */
        private void decideWhetherDelWeibo(String type) {
            if (type != null) {
                if (type.equals(UserTimeLineActivity.CANCEL)) {
                    mItemUserWeiboDelete.setVisibility(View.GONE);
                    mItemWeibo.setEnabled(true);
                } else {
                    mItemUserWeiboDelete.setVisibility(View.VISIBLE);
                    mItemWeibo.setEnabled(true);
                }
            } else {
                mItemUserWeiboDelete.setVisibility(View.GONE);
                mItemWeibo.setEnabled(true);
            }
        }

        /**
         * 设置微博发布者信息
         *
         * @param weibo
         */
        private void setContentPublisherInfo(WeiboInfo weibo) {
            Glide.with(mContext)
                    .load(weibo.getUserHead())
                    .crossFade()
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(mItemUserWeiboUserHead);
            mItemUserWeiboUserName.setText(weibo.getUserName());
        }

        /**
         * 设置微博发布日期
         *
         * @param weibo
         */
        private void setWeiboDate(WeiboInfo weibo) {
            mItemUserWeiboContentDate.setText(TimeUtils.instance(mContext).buildTimeString(weibo.getDate()));
        }


        /**
         * 设置微博底部内容
         *
         * @param weibo
         */
        private void setWeiboFooter(WeiboInfo weibo) {

            mWeiboCommentCount.setText(mContext.getResources().getString(R.string.weibo_comment_count,
                    Tools.number2Str(weibo.getComment())));

            mWeiboFavoriteCount.setText(mContext.getResources().getString(R.string.weibo_favorite_count,
                    Tools.number2Str(weibo.getFavorite())));

            mWeiboTranspondCount.setText(mContext.getResources().getString(R.string.weibo_transpond_count,
                    Tools.number2Str(weibo.getTranspond())));
        }


        /**
         * 设置微博内容图片
         *
         * @param weibo
         */
        private void setWeiboContentImages(WeiboInfo weibo) {
            if (weibo.getHaveImg()) {
                imageShowOrHide(View.VISIBLE);
                List<String> datas = weibo.getImgs();
                //显示内容图片
                if (datas != null) {
                    GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, datas);
                    mItemUserWeiboUserContentImage.setAdapter(gridViewAdapter);
                    Tools.setListViewHeightBasedOnChildren(mItemUserWeiboUserContentImage);
                    gridViewAdapter.notifyDataSetChanged();
                }
            } else {
                imageShowOrHide(View.GONE);
            }
        }


        /**
         * 设置微博内容
         *
         * @param weibo
         */
        private void setWeiboContent(WeiboInfo weibo) {
            Tools.setWeiboTextContent(mContext, weibo.getContent(), mItemUserWeiboContent);
        }


        /**
         * 显示或者隐藏图片
         *
         * @param flag
         */
        private void imageShowOrHide(int flag) {
            mItemUserWeiboUserContentImage.setVisibility(flag);
        }
    }
}