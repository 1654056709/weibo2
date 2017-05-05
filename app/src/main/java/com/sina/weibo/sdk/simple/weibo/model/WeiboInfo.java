package com.sina.weibo.sdk.simple.weibo.model;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by John on 2017/3/29.
 * 根据CommonWeiboInfo抽出部分数据构成WeiboInfo实体
 */

public class WeiboInfo {
    // 文章id
    private String mWeiboId;
    // 文章内容
    private String mContent;
    // 发布人id
    private String mUserId;
    // 发布人名字
    private String mUserName;
    // 发布人头像
    private String mUserHead;
    // 发布时间
    private String mDate;
    // 是否有图片
    private Boolean mIsHaveImg;
    //显示图片内容
    private String mImageUrl;
    //所有图片
    private List<String> mImgs;
    //图片源地址
    private String mOriginPicUrl;
    //喜欢
    private long mFavorite;
    //评论
    private long mComment;
    //转发
    private long mTranspond;
    //微博地址
    private String mProfileUrl;

    public String getProfileUrl() {
        return mProfileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        mProfileUrl = profileUrl;
    }

    public void setImgs(List<String> imgs) {
        mImgs = imgs;
    }

    public List<String> getImgs() {
        return mImgs;
    }

    public long getFavorite() {
        return mFavorite;
    }

    public void setFavorite(long favorite) {
        mFavorite = favorite;
    }

    public long getComment() {
        return mComment;
    }

    public void setComment(long comment) {
        mComment = comment;
    }

    public long getTranspond() {
        return mTranspond;
    }

    public void setTranspond(long transpond) {
        mTranspond = transpond;
    }

    public String getOriginPicUrl() {
        return mOriginPicUrl;
    }

    public void setOriginPicUrl(String originPicUrl) {
        mOriginPicUrl = originPicUrl;
    }

    public WeiboInfo() {
    }

    public WeiboInfo(String weiboId, String content, String userId, String userName, String userHead, String date, Boolean isHaveImg, String imageUrl) {
        mWeiboId = weiboId;
        mContent = content;
        mUserId = userId;
        mUserName = userName;
        mUserHead = userHead;
        mDate = date;
        mIsHaveImg = isHaveImg;
        mImageUrl = imageUrl;
    }

    public String getWeiboId() {
        return mWeiboId;
    }

    public void setWeiboId(String weiboId) {
        mWeiboId = weiboId;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserHead() {
        return mUserHead;
    }

    public void setUserHead(String userHead) {
        mUserHead = userHead;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Boolean getHaveImg() {
        return mIsHaveImg;
    }

    public void setHaveImg(Boolean haveImg) {
        mIsHaveImg = haveImg;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    /**
     * 将CommonWeiboInfo->Weibo集合
     */
    public static List<WeiboInfo> transformToWiebo(CommonWeiboInfo commonWeiboInfo) {
        List<WeiboInfo> weibos = new ArrayList<>();
        List<CommonWeiboInfo.StatusesBean> statusesBeen = commonWeiboInfo.getStatuses();
        CommonWeiboInfo.StatusesBean.RetweetedStatusBean retweetedStatus = null;
        //是否有图片
        boolean isHaveimage = false;
        //图片地址
        String imgUrl = null;
        //内容
        String content = null;
        String originPic = null;
        List<LinkedTreeMap<String, String>> imgs = null;

        for (CommonWeiboInfo.StatusesBean statusesBean : statusesBeen) {

            WeiboInfo weibo = new WeiboInfo();
            //微博Id
            weibo.setWeiboId(statusesBean.getId() + "");
            //创建时间
            weibo.setDate(statusesBean.getCreated_at());
            //评论
            weibo.setComment(statusesBean.getComments_count());
            //转发
            weibo.setTranspond(statusesBean.getReposts_count());
            //表态
            weibo.setFavorite(statusesBean.getAttitudes_count());
            //微博地址
            weibo.setProfileUrl(statusesBean.getUser().getProfile_url());

            if (statusesBean.getRetweeted_status() != null) {
                retweetedStatus = statusesBean.getRetweeted_status();
                content = statusesBean.getText() + "\n" + retweetedStatus.getText();
                isHaveimage = retweetedStatus.getThumbnail_pic() != null ? true : false;
                imgUrl = retweetedStatus.getBmiddle_pic();
                originPic = retweetedStatus.getBmiddle_pic();
                imgs = (List<LinkedTreeMap<String, String>>) retweetedStatus.getPic_urls();

            } else {
                content = statusesBean.getText();
                isHaveimage = statusesBean.getThumbnail_pic() != null ? true : false;
                imgUrl = statusesBean.getBmiddle_pic();
                originPic = statusesBean.getBmiddle_pic();
                imgs = (List<LinkedTreeMap<String, String>>) statusesBean.getPic_urls();
            }

            weibo.setContent(content);
            weibo.setHaveImg(isHaveimage);
            weibo.setImageUrl(imgUrl);
            weibo.setOriginPicUrl(originPic);

            //用户头像
            weibo.setUserHead(statusesBean.getUser().getAvatar_hd());
            //用户姓名
            weibo.setUserName(statusesBean.getUser().getName());
            //多图
            weibo.setImgs(parseImgs(imgs));

            weibos.add(weibo);
        }
        return weibos;
    }


    /**
     * 解析多图片地址
     *
     * @param linkedTreeMaps
     * @return
     */
    private static List<String> parseImgs(List<LinkedTreeMap<String, String>> linkedTreeMaps) {
        List<String> imgs = new ArrayList<>();
        if (linkedTreeMaps != null) {
            for (int i = 0; i < linkedTreeMaps.size(); i++) {
                LinkedTreeMap<String, String> linkedTreeMap = linkedTreeMaps.get(i);
                Set<Map.Entry<String, String>> entrySet = linkedTreeMap.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    String url = iterator.next().getValue();
                    imgs.add(url);
                }
            }
            return imgs;
        }
        return null;
    }
}
