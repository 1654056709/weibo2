package com.sina.weibo.sdk.simple.weibo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.net.HttpUtil;
import com.sina.weibo.sdk.simple.weibo.ui.activity.ShowImageActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.WeiboContentActivity;
import com.sina.weibo.sdk.simple.weibo.util.ToastUtil;
import com.sina.weibo.sdk.simple.weibo.util.Tools;

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
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long weiboId = Long.valueOf(mWeibo.getWeiboId());
                mContext.startActivity(WeiboContentActivity.newIntent(mContext, weiboId));
            }
        });

        mItemUserWeiboUserContentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(ShowImageActivity.newIntent(mContext, mWeibo.getOriginPicUrl()));
            }
        });

        mWeiboTranspondCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(mContext);
                if (accessToken.isSessionValid()) {
                    transPondWeibo(accessToken, mWeibo.getWeiboId());
                }
            }
        });
    }

    /**
     * 转发微博
     *
     * @param accessToken
     * @param weiboId
     */
    private void transPondWeibo(Oauth2AccessToken accessToken, String weiboId) {
        HttpUtil.transPondWeibo(accessToken, Long.valueOf(weiboId), new HttpUtil.HttpCallback() {
            @Override
            public void onSuccess(String msg) {
                Looper.prepare();
                ToastUtil.showToasts(mContext, "转发成功");
                Looper.loop();

            }

            @Override
            public void onFailure(String msg) {
                Looper.prepare();
                ToastUtil.showToasts(mContext, "转发失败");
                Looper.loop();
            }
        });
    }


    public void bindWeibo(WeiboInfo weibo) {
        //当前weibo对象
        mWeibo = weibo;
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