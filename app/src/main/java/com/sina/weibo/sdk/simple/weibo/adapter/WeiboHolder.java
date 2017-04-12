package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.event.CommentEvent;
import com.sina.weibo.sdk.simple.weibo.model.UpdateWeiboInfo;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.net.HttpUtil;
import com.sina.weibo.sdk.simple.weibo.presenter.UpdateWeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.activity.CommentActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.ShowImageActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.UserTimeLineActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.WeiboContentActivity;
import com.sina.weibo.sdk.simple.weibo.ui.dialog.WriteInfoDialog;
import com.sina.weibo.sdk.simple.weibo.ui.fragment.CommentFragment;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.UpdateWeiboInfoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by John on 2017/4/1.
 * <p>
 * RecyclerView的ViewHolder
 * ViewHolder管理一个ItemView
 * 1. 让ViewHolder为ItemView监听用户触摸事件
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
    @BindView(R.id.item_user_weibo_user_content_image)
    ImageView mItemUserWeiboUserContentImage;
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
                Long weiboId = Long.valueOf(mWeibo.getWeiboId());
                mContext.startActivity(WeiboContentActivity.newIntent(mContext, weiboId));
            }
        });


        //查看微博中图片
        mItemUserWeiboUserContentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(ShowImageActivity.newIntent(mContext, mWeibo.getOriginPicUrl()));
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
                Intent intent = CommentActivity.newIntent(mContext, Long.valueOf(mWeibo.getWeiboId()));
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

    public void bindWeibo(WeiboInfo weibo, String type) {
//        Logger.d("type====" + type);
        //当前weibo对象
        mWeibo = weibo;
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


        Glide.with(mContext)
                .load(weibo.getUserHead())
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(mItemUserWeiboUserHead);
        mItemUserWeiboUserName.setText(weibo.getUserName());
        mItemUserWeiboContentDate.setText(Tools.dateFormat(weibo.getDate()));
        mItemUserWeiboContent.setText(weibo.getContent());
        if (weibo.getHaveImg()) {
            mItemUserWeiboUserContentImage.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(weibo.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .fitCenter()
                    .crossFade()
                    .into(mItemUserWeiboUserContentImage);
        } else {
            mItemUserWeiboUserContentImage.setVisibility(View.GONE);
        }

        mWeiboCommentCount.setText("评论(" + Tools.number2Str(weibo.getComment()) + ")");
        mWeiboFavoriteCount.setText("赞(" + Tools.number2Str(weibo.getFavorite()) + ")");
        mWeiboTranspondCount.setText("转发(" + Tools.number2Str(weibo.getTranspond()) + ")");
    }
}