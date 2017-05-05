package com.sina.weibo.sdk.simple.weibo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by John on 2017/4/7.
 */

public class CommonComment {
    private boolean hasvisible;
    private long previous_cursor;
    private long next_cursor;
    private long total_number;
    private long longerval;
    private List<CommentsBean> comments;

    public boolean isHasvisible() {
        return hasvisible;
    }

    public void setHasvisible(boolean hasvisible) {
        this.hasvisible = hasvisible;
    }

    public long getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(long previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public long getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(long next_cursor) {
        this.next_cursor = next_cursor;
    }

    public long getTotal_number() {
        return total_number;
    }

    public void setTotal_number(long total_number) {
        this.total_number = total_number;
    }

    public long getlongerval() {
        return longerval;
    }

    public void setlongerval(long longerval) {
        this.longerval = longerval;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        private String created_at;
        private long id;
        private long rootid;
        private long floor_number;
        private String text;
        private long source_allowclick;
        private long source_type;
        private String source;
        private UserBean user;
        private String mid;
        private String idstr;
        private StatusBean status;
        private long like_count;
        private long reply_count;
        private boolean liked;
        private List<?> url_objects;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getRootid() {
            return rootid;
        }

        public void setRootid(long rootid) {
            this.rootid = rootid;
        }

        public long getFloor_number() {
            return floor_number;
        }

        public void setFloor_number(long floor_number) {
            this.floor_number = floor_number;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public long getSource_allowclick() {
            return source_allowclick;
        }

        public void setSource_allowclick(long source_allowclick) {
            this.source_allowclick = source_allowclick;
        }

        public long getSource_type() {
            return source_type;
        }

        public void setSource_type(long source_type) {
            this.source_type = source_type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public StatusBean getStatus() {
            return status;
        }

        public void setStatus(StatusBean status) {
            this.status = status;
        }

        public long getLike_count() {
            return like_count;
        }

        public void setLike_count(long like_count) {
            this.like_count = like_count;
        }

        public long getReply_count() {
            return reply_count;
        }

        public void setReply_count(long reply_count) {
            this.reply_count = reply_count;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public List<?> getUrl_objects() {
            return url_objects;
        }

        public void setUrl_objects(List<?> url_objects) {
            this.url_objects = url_objects;
        }

        public static class UserBean {
            /**
             * id : 3903514164
             * idstr : 3903514164
             * class : 1
             * screen_name : 如果爱-就勇敢爱
             * name : 如果爱-就勇敢爱
             * province : 100
             * city : 1000
             * location : 其他
             * description : 做真的自我
             * url :
             * profile_image_url : http://tva1.sinaimg.cn/crop.0.0.180.180.50/e8aae634jw8eakgk3uvxlj2050050weg.jpg
             * cover_image_phone : http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg
             * profile_url : u/3903514164
             * domain :
             * weihao :
             * gender : m
             * followers_count : 24
             * friends_count : 209
             * pagefriends_count : 1
             * statuses_count : 178
             * favourites_count : 17
             * created_at : Thu Nov 14 12:54:49 +0800 2013
             * following : false
             * allow_all_act_msg : false
             * geo_enabled : true
             * verified : false
             * verified_type : -1
             * remark :
             * insecurity : {"sexual_content":false}
             * ptype : 0
             * allow_all_comment : true
             * avatar_large : http://tva1.sinaimg.cn/crop.0.0.180.180.180/e8aae634jw8eakgk3uvxlj2050050weg.jpg
             * avatar_hd : http://tva1.sinaimg.cn/crop.0.0.180.180.1024/e8aae634jw8eakgk3uvxlj2050050weg.jpg
             * verified_reason :
             * verified_trade :
             * verified_reason_url :
             * verified_source :
             * verified_source_url :
             * follow_me : false
             * online_status : 0
             * bi_followers_count : 8
             * lang : zh-cn
             * star : 0
             * mbtype : 0
             * mbrank : 0
             * block_word : 0
             * block_app : 0
             * level : 1
             * type : 1
             * ulevel : 0
             * badge : {"uc_domain":0,"enterprise":0,"anniversary":0,"taobao":0,"travel2013":0,"gongyi":0,"gongyi_level":0,"bind_taobao":1,"hongbao_2014":1,"suishoupai_2014":0,"dailv":0,"zongyiji":0,"vip_activity1":0,"unread_pool":1,"daiyan":0,"ali_1688":0,"vip_activity2":0,"suishoupai_2016":0,"fools_day_2016":0,"uefa_euro_2016":0,"super_star_2016":0,"unread_pool_ext":0,"self_media":0,"olympic_2016":0,"dzwbqlx_2016":0,"discount_2016":0,"wedding_2016":0,"shuang11_2016":0,"follow_whitelist_video":0,"wbzy_2016":0,"hongbao_2017":0,"hongbao_2017_2":0,"caishen_2017":0,"wedding_2017":1,"league_badge":0}
             * badge_top :
             * has_ability_tag : 0
             * extend : {"privacy":{"mobile":1},"mbprivilege":"0000000000000000000000000000000000000000000000000000000000000000"}
             * credit_score : 80
             * user_ability : 0
             * urank : 9
             */

            private long id;
            private String idstr;
            @SerializedName("class")
            private long classX;
            private String screen_name;
            private String name;
            private String province;
            private String city;
            private String location;
            private String description;
            private String url;
            private String profile_image_url;
            private String cover_image_phone;
            private String profile_url;
            private String domain;
            private String weihao;
            private String gender;
            private long followers_count;
            private long friends_count;
            private long pagefriends_count;
            private long statuses_count;
            private long favourites_count;
            private String created_at;
            private boolean following;
            private boolean allow_all_act_msg;
            private boolean geo_enabled;
            private boolean verified;
            private long verified_type;
            private String remark;
            private InsecurityBean insecurity;
            private long ptype;
            private boolean allow_all_comment;
            private String avatar_large;
            private String avatar_hd;
            private String verified_reason;
            private String verified_trade;
            private String verified_reason_url;
            private String verified_source;
            private String verified_source_url;
            private boolean follow_me;
            private long online_status;
            private long bi_followers_count;
            private String lang;
            private long star;
            private long mbtype;
            private long mbrank;
            private long block_word;
            private long block_app;
            private long level;
            private long type;
            private long ulevel;
            private BadgeBean badge;
            private String badge_top;
            private long has_ability_tag;
            private ExtendBean extend;
            private long credit_score;
            private long user_ability;
            private long urank;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getIdstr() {
                return idstr;
            }

            public void setIdstr(String idstr) {
                this.idstr = idstr;
            }

            public long getClassX() {
                return classX;
            }

            public void setClassX(long classX) {
                this.classX = classX;
            }

            public String getScreen_name() {
                return screen_name;
            }

            public void setScreen_name(String screen_name) {
                this.screen_name = screen_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getProfile_image_url() {
                return profile_image_url;
            }

            public void setProfile_image_url(String profile_image_url) {
                this.profile_image_url = profile_image_url;
            }

            public String getCover_image_phone() {
                return cover_image_phone;
            }

            public void setCover_image_phone(String cover_image_phone) {
                this.cover_image_phone = cover_image_phone;
            }

            public String getProfile_url() {
                return profile_url;
            }

            public void setProfile_url(String profile_url) {
                this.profile_url = profile_url;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getWeihao() {
                return weihao;
            }

            public void setWeihao(String weihao) {
                this.weihao = weihao;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public long getFollowers_count() {
                return followers_count;
            }

            public void setFollowers_count(long followers_count) {
                this.followers_count = followers_count;
            }

            public long getFriends_count() {
                return friends_count;
            }

            public void setFriends_count(long friends_count) {
                this.friends_count = friends_count;
            }

            public long getPagefriends_count() {
                return pagefriends_count;
            }

            public void setPagefriends_count(long pagefriends_count) {
                this.pagefriends_count = pagefriends_count;
            }

            public long getStatuses_count() {
                return statuses_count;
            }

            public void setStatuses_count(long statuses_count) {
                this.statuses_count = statuses_count;
            }

            public long getFavourites_count() {
                return favourites_count;
            }

            public void setFavourites_count(long favourites_count) {
                this.favourites_count = favourites_count;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public boolean isFollowing() {
                return following;
            }

            public void setFollowing(boolean following) {
                this.following = following;
            }

            public boolean isAllow_all_act_msg() {
                return allow_all_act_msg;
            }

            public void setAllow_all_act_msg(boolean allow_all_act_msg) {
                this.allow_all_act_msg = allow_all_act_msg;
            }

            public boolean isGeo_enabled() {
                return geo_enabled;
            }

            public void setGeo_enabled(boolean geo_enabled) {
                this.geo_enabled = geo_enabled;
            }

            public boolean isVerified() {
                return verified;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public long getVerified_type() {
                return verified_type;
            }

            public void setVerified_type(long verified_type) {
                this.verified_type = verified_type;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public InsecurityBean getInsecurity() {
                return insecurity;
            }

            public void setInsecurity(InsecurityBean insecurity) {
                this.insecurity = insecurity;
            }

            public long getPtype() {
                return ptype;
            }

            public void setPtype(long ptype) {
                this.ptype = ptype;
            }

            public boolean isAllow_all_comment() {
                return allow_all_comment;
            }

            public void setAllow_all_comment(boolean allow_all_comment) {
                this.allow_all_comment = allow_all_comment;
            }

            public String getAvatar_large() {
                return avatar_large;
            }

            public void setAvatar_large(String avatar_large) {
                this.avatar_large = avatar_large;
            }

            public String getAvatar_hd() {
                return avatar_hd;
            }

            public void setAvatar_hd(String avatar_hd) {
                this.avatar_hd = avatar_hd;
            }

            public String getVerified_reason() {
                return verified_reason;
            }

            public void setVerified_reason(String verified_reason) {
                this.verified_reason = verified_reason;
            }

            public String getVerified_trade() {
                return verified_trade;
            }

            public void setVerified_trade(String verified_trade) {
                this.verified_trade = verified_trade;
            }

            public String getVerified_reason_url() {
                return verified_reason_url;
            }

            public void setVerified_reason_url(String verified_reason_url) {
                this.verified_reason_url = verified_reason_url;
            }

            public String getVerified_source() {
                return verified_source;
            }

            public void setVerified_source(String verified_source) {
                this.verified_source = verified_source;
            }

            public String getVerified_source_url() {
                return verified_source_url;
            }

            public void setVerified_source_url(String verified_source_url) {
                this.verified_source_url = verified_source_url;
            }

            public boolean isFollow_me() {
                return follow_me;
            }

            public void setFollow_me(boolean follow_me) {
                this.follow_me = follow_me;
            }

            public long getOnline_status() {
                return online_status;
            }

            public void setOnline_status(long online_status) {
                this.online_status = online_status;
            }

            public long getBi_followers_count() {
                return bi_followers_count;
            }

            public void setBi_followers_count(long bi_followers_count) {
                this.bi_followers_count = bi_followers_count;
            }

            public String getLang() {
                return lang;
            }

            public void setLang(String lang) {
                this.lang = lang;
            }

            public long getStar() {
                return star;
            }

            public void setStar(long star) {
                this.star = star;
            }

            public long getMbtype() {
                return mbtype;
            }

            public void setMbtype(long mbtype) {
                this.mbtype = mbtype;
            }

            public long getMbrank() {
                return mbrank;
            }

            public void setMbrank(long mbrank) {
                this.mbrank = mbrank;
            }

            public long getBlock_word() {
                return block_word;
            }

            public void setBlock_word(long block_word) {
                this.block_word = block_word;
            }

            public long getBlock_app() {
                return block_app;
            }

            public void setBlock_app(long block_app) {
                this.block_app = block_app;
            }

            public long getLevel() {
                return level;
            }

            public void setLevel(long level) {
                this.level = level;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public long getUlevel() {
                return ulevel;
            }

            public void setUlevel(long ulevel) {
                this.ulevel = ulevel;
            }

            public BadgeBean getBadge() {
                return badge;
            }

            public void setBadge(BadgeBean badge) {
                this.badge = badge;
            }

            public String getBadge_top() {
                return badge_top;
            }

            public void setBadge_top(String badge_top) {
                this.badge_top = badge_top;
            }

            public long getHas_ability_tag() {
                return has_ability_tag;
            }

            public void setHas_ability_tag(long has_ability_tag) {
                this.has_ability_tag = has_ability_tag;
            }

            public ExtendBean getExtend() {
                return extend;
            }

            public void setExtend(ExtendBean extend) {
                this.extend = extend;
            }

            public long getCredit_score() {
                return credit_score;
            }

            public void setCredit_score(long credit_score) {
                this.credit_score = credit_score;
            }

            public long getUser_ability() {
                return user_ability;
            }

            public void setUser_ability(long user_ability) {
                this.user_ability = user_ability;
            }

            public long getUrank() {
                return urank;
            }

            public void setUrank(long urank) {
                this.urank = urank;
            }

            public static class InsecurityBean {
                /**
                 * sexual_content : false
                 */

                private boolean sexual_content;

                public boolean isSexual_content() {
                    return sexual_content;
                }

                public void setSexual_content(boolean sexual_content) {
                    this.sexual_content = sexual_content;
                }
            }

            public static class BadgeBean {
                /**
                 * uc_domain : 0
                 * enterprise : 0
                 * anniversary : 0
                 * taobao : 0
                 * travel2013 : 0
                 * gongyi : 0
                 * gongyi_level : 0
                 * bind_taobao : 1
                 * hongbao_2014 : 1
                 * suishoupai_2014 : 0
                 * dailv : 0
                 * zongyiji : 0
                 * vip_activity1 : 0
                 * unread_pool : 1
                 * daiyan : 0
                 * ali_1688 : 0
                 * vip_activity2 : 0
                 * suishoupai_2016 : 0
                 * fools_day_2016 : 0
                 * uefa_euro_2016 : 0
                 * super_star_2016 : 0
                 * unread_pool_ext : 0
                 * self_media : 0
                 * olympic_2016 : 0
                 * dzwbqlx_2016 : 0
                 * discount_2016 : 0
                 * wedding_2016 : 0
                 * shuang11_2016 : 0
                 * follow_whitelist_video : 0
                 * wbzy_2016 : 0
                 * hongbao_2017 : 0
                 * hongbao_2017_2 : 0
                 * caishen_2017 : 0
                 * wedding_2017 : 1
                 * league_badge : 0
                 */

                private long uc_domain;
                private long enterprise;
                private long anniversary;
                private long taobao;
                private long travel2013;
                private long gongyi;
                private long gongyi_level;
                private long bind_taobao;
                private long hongbao_2014;
                private long suishoupai_2014;
                private long dailv;
                private long zongyiji;
                private long vip_activity1;
                private long unread_pool;
                private long daiyan;
                private long ali_1688;
                private long vip_activity2;
                private long suishoupai_2016;
                private long fools_day_2016;
                private long uefa_euro_2016;
                private long super_star_2016;
                private long unread_pool_ext;
                private long self_media;
                private long olympic_2016;
                private long dzwbqlx_2016;
                private long discount_2016;
                private long wedding_2016;
                private long shuang11_2016;
                private long follow_whitelist_video;
                private long wbzy_2016;
                private long hongbao_2017;
                private long hongbao_2017_2;
                private long caishen_2017;
                private long wedding_2017;
                private long league_badge;

                public long getUc_domain() {
                    return uc_domain;
                }

                public void setUc_domain(long uc_domain) {
                    this.uc_domain = uc_domain;
                }

                public long getEnterprise() {
                    return enterprise;
                }

                public void setEnterprise(long enterprise) {
                    this.enterprise = enterprise;
                }

                public long getAnniversary() {
                    return anniversary;
                }

                public void setAnniversary(long anniversary) {
                    this.anniversary = anniversary;
                }

                public long getTaobao() {
                    return taobao;
                }

                public void setTaobao(long taobao) {
                    this.taobao = taobao;
                }

                public long getTravel2013() {
                    return travel2013;
                }

                public void setTravel2013(long travel2013) {
                    this.travel2013 = travel2013;
                }

                public long getGongyi() {
                    return gongyi;
                }

                public void setGongyi(long gongyi) {
                    this.gongyi = gongyi;
                }

                public long getGongyi_level() {
                    return gongyi_level;
                }

                public void setGongyi_level(long gongyi_level) {
                    this.gongyi_level = gongyi_level;
                }

                public long getBind_taobao() {
                    return bind_taobao;
                }

                public void setBind_taobao(long bind_taobao) {
                    this.bind_taobao = bind_taobao;
                }

                public long getHongbao_2014() {
                    return hongbao_2014;
                }

                public void setHongbao_2014(long hongbao_2014) {
                    this.hongbao_2014 = hongbao_2014;
                }

                public long getSuishoupai_2014() {
                    return suishoupai_2014;
                }

                public void setSuishoupai_2014(long suishoupai_2014) {
                    this.suishoupai_2014 = suishoupai_2014;
                }

                public long getDailv() {
                    return dailv;
                }

                public void setDailv(long dailv) {
                    this.dailv = dailv;
                }

                public long getZongyiji() {
                    return zongyiji;
                }

                public void setZongyiji(long zongyiji) {
                    this.zongyiji = zongyiji;
                }

                public long getVip_activity1() {
                    return vip_activity1;
                }

                public void setVip_activity1(long vip_activity1) {
                    this.vip_activity1 = vip_activity1;
                }

                public long getUnread_pool() {
                    return unread_pool;
                }

                public void setUnread_pool(long unread_pool) {
                    this.unread_pool = unread_pool;
                }

                public long getDaiyan() {
                    return daiyan;
                }

                public void setDaiyan(long daiyan) {
                    this.daiyan = daiyan;
                }

                public long getAli_1688() {
                    return ali_1688;
                }

                public void setAli_1688(long ali_1688) {
                    this.ali_1688 = ali_1688;
                }

                public long getVip_activity2() {
                    return vip_activity2;
                }

                public void setVip_activity2(long vip_activity2) {
                    this.vip_activity2 = vip_activity2;
                }

                public long getSuishoupai_2016() {
                    return suishoupai_2016;
                }

                public void setSuishoupai_2016(long suishoupai_2016) {
                    this.suishoupai_2016 = suishoupai_2016;
                }

                public long getFools_day_2016() {
                    return fools_day_2016;
                }

                public void setFools_day_2016(long fools_day_2016) {
                    this.fools_day_2016 = fools_day_2016;
                }

                public long getUefa_euro_2016() {
                    return uefa_euro_2016;
                }

                public void setUefa_euro_2016(long uefa_euro_2016) {
                    this.uefa_euro_2016 = uefa_euro_2016;
                }

                public long getSuper_star_2016() {
                    return super_star_2016;
                }

                public void setSuper_star_2016(long super_star_2016) {
                    this.super_star_2016 = super_star_2016;
                }

                public long getUnread_pool_ext() {
                    return unread_pool_ext;
                }

                public void setUnread_pool_ext(long unread_pool_ext) {
                    this.unread_pool_ext = unread_pool_ext;
                }

                public long getSelf_media() {
                    return self_media;
                }

                public void setSelf_media(long self_media) {
                    this.self_media = self_media;
                }

                public long getOlympic_2016() {
                    return olympic_2016;
                }

                public void setOlympic_2016(long olympic_2016) {
                    this.olympic_2016 = olympic_2016;
                }

                public long getDzwbqlx_2016() {
                    return dzwbqlx_2016;
                }

                public void setDzwbqlx_2016(long dzwbqlx_2016) {
                    this.dzwbqlx_2016 = dzwbqlx_2016;
                }

                public long getDiscount_2016() {
                    return discount_2016;
                }

                public void setDiscount_2016(long discount_2016) {
                    this.discount_2016 = discount_2016;
                }

                public long getWedding_2016() {
                    return wedding_2016;
                }

                public void setWedding_2016(long wedding_2016) {
                    this.wedding_2016 = wedding_2016;
                }

                public long getShuang11_2016() {
                    return shuang11_2016;
                }

                public void setShuang11_2016(long shuang11_2016) {
                    this.shuang11_2016 = shuang11_2016;
                }

                public long getFollow_whitelist_video() {
                    return follow_whitelist_video;
                }

                public void setFollow_whitelist_video(long follow_whitelist_video) {
                    this.follow_whitelist_video = follow_whitelist_video;
                }

                public long getWbzy_2016() {
                    return wbzy_2016;
                }

                public void setWbzy_2016(long wbzy_2016) {
                    this.wbzy_2016 = wbzy_2016;
                }

                public long getHongbao_2017() {
                    return hongbao_2017;
                }

                public void setHongbao_2017(long hongbao_2017) {
                    this.hongbao_2017 = hongbao_2017;
                }

                public long getHongbao_2017_2() {
                    return hongbao_2017_2;
                }

                public void setHongbao_2017_2(long hongbao_2017_2) {
                    this.hongbao_2017_2 = hongbao_2017_2;
                }

                public long getCaishen_2017() {
                    return caishen_2017;
                }

                public void setCaishen_2017(long caishen_2017) {
                    this.caishen_2017 = caishen_2017;
                }

                public long getWedding_2017() {
                    return wedding_2017;
                }

                public void setWedding_2017(long wedding_2017) {
                    this.wedding_2017 = wedding_2017;
                }

                public long getLeague_badge() {
                    return league_badge;
                }

                public void setLeague_badge(long league_badge) {
                    this.league_badge = league_badge;
                }
            }

            public static class ExtendBean {
                /**
                 * privacy : {"mobile":1}
                 * mbprivilege : 0000000000000000000000000000000000000000000000000000000000000000
                 */

                private PrivacyBean privacy;
                private String mbprivilege;

                public PrivacyBean getPrivacy() {
                    return privacy;
                }

                public void setPrivacy(PrivacyBean privacy) {
                    this.privacy = privacy;
                }

                public String getMbprivilege() {
                    return mbprivilege;
                }

                public void setMbprivilege(String mbprivilege) {
                    this.mbprivilege = mbprivilege;
                }

                public static class PrivacyBean {
                    /**
                     * mobile : 1
                     */

                    private long mobile;

                    public long getMobile() {
                        return mobile;
                    }

                    public void setMobile(long mobile) {
                        this.mobile = mobile;
                    }
                }
            }
        }

        public static class StatusBean {
            /**
             * created_at : Mon Apr 10 21:26:26 +0800 2017
             * id : 4095097483829171
             * mid : 4095097483829171
             * idstr : 4095097483829171
             * text : 转发微博
             * source_allowclick : 0
             * source_type : 1
             * source : <a href="http://app.weibo.com/t/feed/H6Loy" rel="nofollow">未通过审核应用</a>
             * appid : 2326732
             * favorited : false
             * truncated : false
             * in_reply_to_status_id :
             * in_reply_to_user_id :
             * in_reply_to_screen_name :
             * pic_ids : []
             * geo : null
             * user : {"id":3903514164,"idstr":"3903514164","class":1,"screen_name":"如果爱-就勇敢爱","name":"如果爱-就勇敢爱","province":"100","city":"1000","location":"其他","description":"做真的自我","url":"","profile_image_url":"http://tva1.sinaimg.cn/crop.0.0.180.180.50/e8aae634jw8eakgk3uvxlj2050050weg.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3903514164","domain":"","weihao":"","gender":"m","followers_count":24,"friends_count":209,"pagefriends_count":1,"statuses_count":178,"favourites_count":17,"created_at":"Thu Nov 14 12:54:49 +0800 2013","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.0.0.180.180.180/e8aae634jw8eakgk3uvxlj2050050weg.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.0.0.180.180.1024/e8aae634jw8eakgk3uvxlj2050050weg.jpg",
             * "verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":8,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"level":1,"type":1,"ulevel":0,"badge":{"uc_domain":0,"enterprise":0,"anniversary":0,"taobao":0,"travel2013":0,"gongyi":0,"gongyi_level":0,"bind_taobao":1,"hongbao_2014":1,"suishoupai_2014":0,"dailv":0,"zongyiji":0,"vip_activity1":0,"unread_pool":1,"daiyan":0,"ali_1688":0,"vip_activity2":0,"suishoupai_2016":0,"fools_day_2016":0,"uefa_euro_2016":0,"super_star_2016":0,"unread_pool_ext":0,"self_media":0,"olympic_2016":0,"dzwbqlx_2016":0,"discount_2016":0,"wedding_2016":0,"shuang11_2016":0,"follow_whitelist_video":0,"wbzy_2016":0,"hongbao_2017":0,"hongbao_2017_2":0,"caishen_2017":0,"wedding_2017":1,"league_badge":0},"badge_top":"","has_ability_tag":0,"extend":{"privacy":{"mobile":1},
             * "mbprivilege":"0000000000000000000000000000000000000000000000000000000000000000"},"credit_score":80,"user_ability":0,"urank":9}
             * retweeted_status : {"created_at":"Mon Apr 10 21:26:11 +0800 2017","id":4095097421681412,"mid":"4095097421681412","idstr":"4095097421681412","text":"hello","textLength":5,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/H6Loy\" rel=\"nofollow\">未通过审核应用<\/a>","appid":2326732,"favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_ids":[],"geo":null,"user":{"id":3903514164,"idstr":"3903514164","class":1,"screen_name":"如果爱-就勇敢爱","name":"如果爱-就勇敢爱","province":"100","city":"1000","location":"其他","description":"做真的自我","url":"","profile_image_url":"http://tva1.sinaimg.cn/crop.0.0.180.180.50/e8aae634jw8eakgk3uvxlj2050050weg.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3903514164","domain":"","weihao":"","gender":"m","followers_count":24,"friends_count":209,"pagefriends_count":1,"statuses_count":178,
             * "favourites_count":17,"created_at":"Thu Nov 14 12:54:49 +0800 2013","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.0.0.180.180.180/e8aae634jw8eakgk3uvxlj2050050weg.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.0.0.180.180.1024/e8aae634jw8eakgk3uvxlj2050050weg.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":8,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"level":1,"type":1,"ulevel":0,"badge":{"uc_domain":0,"enterprise":0,"anniversary":0,"taobao":0,"travel2013":0,"gongyi":0,"gongyi_level":0,"bind_taobao":1,"hongbao_2014":1,"suishoupai_2014":0,"dailv":0,"zongyiji":0,"vip_activity1":0,"unread_pool":1,"daiyan":0,"ali_1688":0,"vip_activity2":0,
             * "suishoupai_2016":0,"fools_day_2016":0,"uefa_euro_2016":0,"super_star_2016":0,"unread_pool_ext":0,"self_media":0,"olympic_2016":0,"dzwbqlx_2016":0,"discount_2016":0,"wedding_2016":0,"shuang11_2016":0,"follow_whitelist_video":0,"wbzy_2016":0,"hongbao_2017":0,"hongbao_2017_2":0,"caishen_2017":0,"wedding_2017":1,"league_badge":0},"badge_top":"","has_ability_tag":0,"extend":{"privacy":{"mobile":1},"mbprivilege":"0000000000000000000000000000000000000000000000000000000000000000"},"credit_score":80,"user_ability":0,"urank":9},"reposts_count":0,"comments_count":0,"attitudes_count":0,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"url_objects":[],"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2}
             * reposts_count : 0
             * comments_count : 0
             * attitudes_count : 0
             * isLongText : false
             * mlevel : 0
             * visible : {"type":0,"list_id":0}
             * biz_feature : 0
             * url_objects : []
             * hasActionTypeCard : 0
             * darwin_tags : []
             * hot_weibo_tags : []
             * text_tag_tips : []
             * userType : 0
             * positive_recom_flag : 0
             * gif_ids :
             * is_show_bulletin : 2
             */

            private String created_at;
            private long id;
            private String mid;
            private String idstr;
            private String text;
            private long source_allowclick;
            private long source_type;
            private String source;
            private long appid;
            private boolean favorited;
            private boolean truncated;
            private String in_reply_to_status_id;
            private String in_reply_to_user_id;
            private String in_reply_to_screen_name;
            private Object geo;
            private UserBeanX user;
            private RetweetedStatusBean retweeted_status;
            private long reposts_count;
            private long comments_count;
            private long attitudes_count;
            private boolean isLongText;
            private long mlevel;
            private VisibleBeanX visible;
            private long biz_feature;
            private long hasActionTypeCard;
            private long userType;
            private long positive_recom_flag;
            private String gif_ids;
            private long is_show_bulletin;
            private List<?> pic_ids;
            private List<?> url_objects;
            private List<?> darwin_tags;
            private List<?> hot_weibo_tags;
            private List<?> text_tag_tips;

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getIdstr() {
                return idstr;
            }

            public void setIdstr(String idstr) {
                this.idstr = idstr;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public long getSource_allowclick() {
                return source_allowclick;
            }

            public void setSource_allowclick(long source_allowclick) {
                this.source_allowclick = source_allowclick;
            }

            public long getSource_type() {
                return source_type;
            }

            public void setSource_type(long source_type) {
                this.source_type = source_type;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public long getAppid() {
                return appid;
            }

            public void setAppid(long appid) {
                this.appid = appid;
            }

            public boolean isFavorited() {
                return favorited;
            }

            public void setFavorited(boolean favorited) {
                this.favorited = favorited;
            }

            public boolean isTruncated() {
                return truncated;
            }

            public void setTruncated(boolean truncated) {
                this.truncated = truncated;
            }

            public String getIn_reply_to_status_id() {
                return in_reply_to_status_id;
            }

            public void setIn_reply_to_status_id(String in_reply_to_status_id) {
                this.in_reply_to_status_id = in_reply_to_status_id;
            }

            public String getIn_reply_to_user_id() {
                return in_reply_to_user_id;
            }

            public void setIn_reply_to_user_id(String in_reply_to_user_id) {
                this.in_reply_to_user_id = in_reply_to_user_id;
            }

            public String getIn_reply_to_screen_name() {
                return in_reply_to_screen_name;
            }

            public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
                this.in_reply_to_screen_name = in_reply_to_screen_name;
            }

            public Object getGeo() {
                return geo;
            }

            public void setGeo(Object geo) {
                this.geo = geo;
            }

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public RetweetedStatusBean getRetweeted_status() {
                return retweeted_status;
            }

            public void setRetweeted_status(RetweetedStatusBean retweeted_status) {
                this.retweeted_status = retweeted_status;
            }

            public long getReposts_count() {
                return reposts_count;
            }

            public void setReposts_count(long reposts_count) {
                this.reposts_count = reposts_count;
            }

            public long getComments_count() {
                return comments_count;
            }

            public void setComments_count(long comments_count) {
                this.comments_count = comments_count;
            }

            public long getAttitudes_count() {
                return attitudes_count;
            }

            public void setAttitudes_count(long attitudes_count) {
                this.attitudes_count = attitudes_count;
            }

            public boolean isIsLongText() {
                return isLongText;
            }

            public void setIsLongText(boolean isLongText) {
                this.isLongText = isLongText;
            }

            public long getMlevel() {
                return mlevel;
            }

            public void setMlevel(long mlevel) {
                this.mlevel = mlevel;
            }

            public VisibleBeanX getVisible() {
                return visible;
            }

            public void setVisible(VisibleBeanX visible) {
                this.visible = visible;
            }

            public long getBiz_feature() {
                return biz_feature;
            }

            public void setBiz_feature(long biz_feature) {
                this.biz_feature = biz_feature;
            }

            public long getHasActionTypeCard() {
                return hasActionTypeCard;
            }

            public void setHasActionTypeCard(long hasActionTypeCard) {
                this.hasActionTypeCard = hasActionTypeCard;
            }

            public long getUserType() {
                return userType;
            }

            public void setUserType(long userType) {
                this.userType = userType;
            }

            public long getPositive_recom_flag() {
                return positive_recom_flag;
            }

            public void setPositive_recom_flag(long positive_recom_flag) {
                this.positive_recom_flag = positive_recom_flag;
            }

            public String getGif_ids() {
                return gif_ids;
            }

            public void setGif_ids(String gif_ids) {
                this.gif_ids = gif_ids;
            }

            public long getIs_show_bulletin() {
                return is_show_bulletin;
            }

            public void setIs_show_bulletin(long is_show_bulletin) {
                this.is_show_bulletin = is_show_bulletin;
            }

            public List<?> getPic_ids() {
                return pic_ids;
            }

            public void setPic_ids(List<?> pic_ids) {
                this.pic_ids = pic_ids;
            }

            public List<?> getUrl_objects() {
                return url_objects;
            }

            public void setUrl_objects(List<?> url_objects) {
                this.url_objects = url_objects;
            }

            public List<?> getDarwin_tags() {
                return darwin_tags;
            }

            public void setDarwin_tags(List<?> darwin_tags) {
                this.darwin_tags = darwin_tags;
            }

            public List<?> getHot_weibo_tags() {
                return hot_weibo_tags;
            }

            public void setHot_weibo_tags(List<?> hot_weibo_tags) {
                this.hot_weibo_tags = hot_weibo_tags;
            }

            public List<?> getText_tag_tips() {
                return text_tag_tips;
            }

            public void setText_tag_tips(List<?> text_tag_tips) {
                this.text_tag_tips = text_tag_tips;
            }

            public static class UserBeanX {
                /**
                 * id : 3903514164
                 * idstr : 3903514164
                 * class : 1
                 * screen_name : 如果爱-就勇敢爱
                 * name : 如果爱-就勇敢爱
                 * province : 100
                 * city : 1000
                 * location : 其他
                 * description : 做真的自我
                 * url :
                 * profile_image_url : http://tva1.sinaimg.cn/crop.0.0.180.180.50/e8aae634jw8eakgk3uvxlj2050050weg.jpg
                 * cover_image_phone : http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg
                 * profile_url : u/3903514164
                 * domain :
                 * weihao :
                 * gender : m
                 * followers_count : 24
                 * friends_count : 209
                 * pagefriends_count : 1
                 * statuses_count : 178
                 * favourites_count : 17
                 * created_at : Thu Nov 14 12:54:49 +0800 2013
                 * following : false
                 * allow_all_act_msg : false
                 * geo_enabled : true
                 * verified : false
                 * verified_type : -1
                 * remark :
                 * insecurity : {"sexual_content":false}
                 * ptype : 0
                 * allow_all_comment : true
                 * avatar_large : http://tva1.sinaimg.cn/crop.0.0.180.180.180/e8aae634jw8eakgk3uvxlj2050050weg.jpg
                 * avatar_hd : http://tva1.sinaimg.cn/crop.0.0.180.180.1024/e8aae634jw8eakgk3uvxlj2050050weg.jpg
                 * verified_reason :
                 * verified_trade :
                 * verified_reason_url :
                 * verified_source :
                 * verified_source_url :
                 * follow_me : false
                 * online_status : 0
                 * bi_followers_count : 8
                 * lang : zh-cn
                 * star : 0
                 * mbtype : 0
                 * mbrank : 0
                 * block_word : 0
                 * block_app : 0
                 * level : 1
                 * type : 1
                 * ulevel : 0
                 * badge : {"uc_domain":0,"enterprise":0,"anniversary":0,"taobao":0,"travel2013":0,"gongyi":0,"gongyi_level":0,"bind_taobao":1,"hongbao_2014":1,"suishoupai_2014":0,"dailv":0,"zongyiji":0,"vip_activity1":0,"unread_pool":1,"daiyan":0,"ali_1688":0,"vip_activity2":0,"suishoupai_2016":0,"fools_day_2016":0,"uefa_euro_2016":0,"super_star_2016":0,"unread_pool_ext":0,"self_media":0,"olympic_2016":0,"dzwbqlx_2016":0,"discount_2016":0,"wedding_2016":0,"shuang11_2016":0,"follow_whitelist_video":0,"wbzy_2016":0,"hongbao_2017":0,"hongbao_2017_2":0,"caishen_2017":0,"wedding_2017":1,"league_badge":0}
                 * badge_top :
                 * has_ability_tag : 0
                 * extend : {"privacy":{"mobile":1},"mbprivilege":"0000000000000000000000000000000000000000000000000000000000000000"}
                 * credit_score : 80
                 * user_ability : 0
                 * urank : 9
                 */

                private long id;
                private String idstr;
                @SerializedName("class")
                private long classX;
                private String screen_name;
                private String name;
                private String province;
                private String city;
                private String location;
                private String description;
                private String url;
                private String profile_image_url;
                private String cover_image_phone;
                private String profile_url;
                private String domain;
                private String weihao;
                private String gender;
                private long followers_count;
                private long friends_count;
                private long pagefriends_count;
                private long statuses_count;
                private long favourites_count;
                private String created_at;
                private boolean following;
                private boolean allow_all_act_msg;
                private boolean geo_enabled;
                private boolean verified;
                private long verified_type;
                private String remark;
                private InsecurityBeanX insecurity;
                private long ptype;
                private boolean allow_all_comment;
                private String avatar_large;
                private String avatar_hd;
                private String verified_reason;
                private String verified_trade;
                private String verified_reason_url;
                private String verified_source;
                private String verified_source_url;
                private boolean follow_me;
                private long online_status;
                private long bi_followers_count;
                private String lang;
                private long star;
                private long mbtype;
                private long mbrank;
                private long block_word;
                private long block_app;
                private long level;
                private long type;
                private long ulevel;
                private BadgeBeanX badge;
                private String badge_top;
                private long has_ability_tag;
                private ExtendBeanX extend;
                private long credit_score;
                private long user_ability;
                private long urank;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public String getIdstr() {
                    return idstr;
                }

                public void setIdstr(String idstr) {
                    this.idstr = idstr;
                }

                public long getClassX() {
                    return classX;
                }

                public void setClassX(long classX) {
                    this.classX = classX;
                }

                public String getScreen_name() {
                    return screen_name;
                }

                public void setScreen_name(String screen_name) {
                    this.screen_name = screen_name;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getProfile_image_url() {
                    return profile_image_url;
                }

                public void setProfile_image_url(String profile_image_url) {
                    this.profile_image_url = profile_image_url;
                }

                public String getCover_image_phone() {
                    return cover_image_phone;
                }

                public void setCover_image_phone(String cover_image_phone) {
                    this.cover_image_phone = cover_image_phone;
                }

                public String getProfile_url() {
                    return profile_url;
                }

                public void setProfile_url(String profile_url) {
                    this.profile_url = profile_url;
                }

                public String getDomain() {
                    return domain;
                }

                public void setDomain(String domain) {
                    this.domain = domain;
                }

                public String getWeihao() {
                    return weihao;
                }

                public void setWeihao(String weihao) {
                    this.weihao = weihao;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public long getFollowers_count() {
                    return followers_count;
                }

                public void setFollowers_count(long followers_count) {
                    this.followers_count = followers_count;
                }

                public long getFriends_count() {
                    return friends_count;
                }

                public void setFriends_count(long friends_count) {
                    this.friends_count = friends_count;
                }

                public long getPagefriends_count() {
                    return pagefriends_count;
                }

                public void setPagefriends_count(long pagefriends_count) {
                    this.pagefriends_count = pagefriends_count;
                }

                public long getStatuses_count() {
                    return statuses_count;
                }

                public void setStatuses_count(long statuses_count) {
                    this.statuses_count = statuses_count;
                }

                public long getFavourites_count() {
                    return favourites_count;
                }

                public void setFavourites_count(long favourites_count) {
                    this.favourites_count = favourites_count;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public boolean isFollowing() {
                    return following;
                }

                public void setFollowing(boolean following) {
                    this.following = following;
                }

                public boolean isAllow_all_act_msg() {
                    return allow_all_act_msg;
                }

                public void setAllow_all_act_msg(boolean allow_all_act_msg) {
                    this.allow_all_act_msg = allow_all_act_msg;
                }

                public boolean isGeo_enabled() {
                    return geo_enabled;
                }

                public void setGeo_enabled(boolean geo_enabled) {
                    this.geo_enabled = geo_enabled;
                }

                public boolean isVerified() {
                    return verified;
                }

                public void setVerified(boolean verified) {
                    this.verified = verified;
                }

                public long getVerified_type() {
                    return verified_type;
                }

                public void setVerified_type(long verified_type) {
                    this.verified_type = verified_type;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public InsecurityBeanX getInsecurity() {
                    return insecurity;
                }

                public void setInsecurity(InsecurityBeanX insecurity) {
                    this.insecurity = insecurity;
                }

                public long getPtype() {
                    return ptype;
                }

                public void setPtype(long ptype) {
                    this.ptype = ptype;
                }

                public boolean isAllow_all_comment() {
                    return allow_all_comment;
                }

                public void setAllow_all_comment(boolean allow_all_comment) {
                    this.allow_all_comment = allow_all_comment;
                }

                public String getAvatar_large() {
                    return avatar_large;
                }

                public void setAvatar_large(String avatar_large) {
                    this.avatar_large = avatar_large;
                }

                public String getAvatar_hd() {
                    return avatar_hd;
                }

                public void setAvatar_hd(String avatar_hd) {
                    this.avatar_hd = avatar_hd;
                }

                public String getVerified_reason() {
                    return verified_reason;
                }

                public void setVerified_reason(String verified_reason) {
                    this.verified_reason = verified_reason;
                }

                public String getVerified_trade() {
                    return verified_trade;
                }

                public void setVerified_trade(String verified_trade) {
                    this.verified_trade = verified_trade;
                }

                public String getVerified_reason_url() {
                    return verified_reason_url;
                }

                public void setVerified_reason_url(String verified_reason_url) {
                    this.verified_reason_url = verified_reason_url;
                }

                public String getVerified_source() {
                    return verified_source;
                }

                public void setVerified_source(String verified_source) {
                    this.verified_source = verified_source;
                }

                public String getVerified_source_url() {
                    return verified_source_url;
                }

                public void setVerified_source_url(String verified_source_url) {
                    this.verified_source_url = verified_source_url;
                }

                public boolean isFollow_me() {
                    return follow_me;
                }

                public void setFollow_me(boolean follow_me) {
                    this.follow_me = follow_me;
                }

                public long getOnline_status() {
                    return online_status;
                }

                public void setOnline_status(long online_status) {
                    this.online_status = online_status;
                }

                public long getBi_followers_count() {
                    return bi_followers_count;
                }

                public void setBi_followers_count(long bi_followers_count) {
                    this.bi_followers_count = bi_followers_count;
                }

                public String getLang() {
                    return lang;
                }

                public void setLang(String lang) {
                    this.lang = lang;
                }

                public long getStar() {
                    return star;
                }

                public void setStar(long star) {
                    this.star = star;
                }

                public long getMbtype() {
                    return mbtype;
                }

                public void setMbtype(long mbtype) {
                    this.mbtype = mbtype;
                }

                public long getMbrank() {
                    return mbrank;
                }

                public void setMbrank(long mbrank) {
                    this.mbrank = mbrank;
                }

                public long getBlock_word() {
                    return block_word;
                }

                public void setBlock_word(long block_word) {
                    this.block_word = block_word;
                }

                public long getBlock_app() {
                    return block_app;
                }

                public void setBlock_app(long block_app) {
                    this.block_app = block_app;
                }

                public long getLevel() {
                    return level;
                }

                public void setLevel(long level) {
                    this.level = level;
                }

                public long getType() {
                    return type;
                }

                public void setType(long type) {
                    this.type = type;
                }

                public long getUlevel() {
                    return ulevel;
                }

                public void setUlevel(long ulevel) {
                    this.ulevel = ulevel;
                }

                public BadgeBeanX getBadge() {
                    return badge;
                }

                public void setBadge(BadgeBeanX badge) {
                    this.badge = badge;
                }

                public String getBadge_top() {
                    return badge_top;
                }

                public void setBadge_top(String badge_top) {
                    this.badge_top = badge_top;
                }

                public long getHas_ability_tag() {
                    return has_ability_tag;
                }

                public void setHas_ability_tag(long has_ability_tag) {
                    this.has_ability_tag = has_ability_tag;
                }

                public ExtendBeanX getExtend() {
                    return extend;
                }

                public void setExtend(ExtendBeanX extend) {
                    this.extend = extend;
                }

                public long getCredit_score() {
                    return credit_score;
                }

                public void setCredit_score(long credit_score) {
                    this.credit_score = credit_score;
                }

                public long getUser_ability() {
                    return user_ability;
                }

                public void setUser_ability(long user_ability) {
                    this.user_ability = user_ability;
                }

                public long getUrank() {
                    return urank;
                }

                public void setUrank(long urank) {
                    this.urank = urank;
                }

                public static class InsecurityBeanX {
                    /**
                     * sexual_content : false
                     */

                    private boolean sexual_content;

                    public boolean isSexual_content() {
                        return sexual_content;
                    }

                    public void setSexual_content(boolean sexual_content) {
                        this.sexual_content = sexual_content;
                    }
                }

                public static class BadgeBeanX {
                    /**
                     * uc_domain : 0
                     * enterprise : 0
                     * anniversary : 0
                     * taobao : 0
                     * travel2013 : 0
                     * gongyi : 0
                     * gongyi_level : 0
                     * bind_taobao : 1
                     * hongbao_2014 : 1
                     * suishoupai_2014 : 0
                     * dailv : 0
                     * zongyiji : 0
                     * vip_activity1 : 0
                     * unread_pool : 1
                     * daiyan : 0
                     * ali_1688 : 0
                     * vip_activity2 : 0
                     * suishoupai_2016 : 0
                     * fools_day_2016 : 0
                     * uefa_euro_2016 : 0
                     * super_star_2016 : 0
                     * unread_pool_ext : 0
                     * self_media : 0
                     * olympic_2016 : 0
                     * dzwbqlx_2016 : 0
                     * discount_2016 : 0
                     * wedding_2016 : 0
                     * shuang11_2016 : 0
                     * follow_whitelist_video : 0
                     * wbzy_2016 : 0
                     * hongbao_2017 : 0
                     * hongbao_2017_2 : 0
                     * caishen_2017 : 0
                     * wedding_2017 : 1
                     * league_badge : 0
                     */

                    private long uc_domain;
                    private long enterprise;
                    private long anniversary;
                    private long taobao;
                    private long travel2013;
                    private long gongyi;
                    private long gongyi_level;
                    private long bind_taobao;
                    private long hongbao_2014;
                    private long suishoupai_2014;
                    private long dailv;
                    private long zongyiji;
                    private long vip_activity1;
                    private long unread_pool;
                    private long daiyan;
                    private long ali_1688;
                    private long vip_activity2;
                    private long suishoupai_2016;
                    private long fools_day_2016;
                    private long uefa_euro_2016;
                    private long super_star_2016;
                    private long unread_pool_ext;
                    private long self_media;
                    private long olympic_2016;
                    private long dzwbqlx_2016;
                    private long discount_2016;
                    private long wedding_2016;
                    private long shuang11_2016;
                    private long follow_whitelist_video;
                    private long wbzy_2016;
                    private long hongbao_2017;
                    private long hongbao_2017_2;
                    private long caishen_2017;
                    private long wedding_2017;
                    private long league_badge;

                    public long getUc_domain() {
                        return uc_domain;
                    }

                    public void setUc_domain(long uc_domain) {
                        this.uc_domain = uc_domain;
                    }

                    public long getEnterprise() {
                        return enterprise;
                    }

                    public void setEnterprise(long enterprise) {
                        this.enterprise = enterprise;
                    }

                    public long getAnniversary() {
                        return anniversary;
                    }

                    public void setAnniversary(long anniversary) {
                        this.anniversary = anniversary;
                    }

                    public long getTaobao() {
                        return taobao;
                    }

                    public void setTaobao(long taobao) {
                        this.taobao = taobao;
                    }

                    public long getTravel2013() {
                        return travel2013;
                    }

                    public void setTravel2013(long travel2013) {
                        this.travel2013 = travel2013;
                    }

                    public long getGongyi() {
                        return gongyi;
                    }

                    public void setGongyi(long gongyi) {
                        this.gongyi = gongyi;
                    }

                    public long getGongyi_level() {
                        return gongyi_level;
                    }

                    public void setGongyi_level(long gongyi_level) {
                        this.gongyi_level = gongyi_level;
                    }

                    public long getBind_taobao() {
                        return bind_taobao;
                    }

                    public void setBind_taobao(long bind_taobao) {
                        this.bind_taobao = bind_taobao;
                    }

                    public long getHongbao_2014() {
                        return hongbao_2014;
                    }

                    public void setHongbao_2014(long hongbao_2014) {
                        this.hongbao_2014 = hongbao_2014;
                    }

                    public long getSuishoupai_2014() {
                        return suishoupai_2014;
                    }

                    public void setSuishoupai_2014(long suishoupai_2014) {
                        this.suishoupai_2014 = suishoupai_2014;
                    }

                    public long getDailv() {
                        return dailv;
                    }

                    public void setDailv(long dailv) {
                        this.dailv = dailv;
                    }

                    public long getZongyiji() {
                        return zongyiji;
                    }

                    public void setZongyiji(long zongyiji) {
                        this.zongyiji = zongyiji;
                    }

                    public long getVip_activity1() {
                        return vip_activity1;
                    }

                    public void setVip_activity1(long vip_activity1) {
                        this.vip_activity1 = vip_activity1;
                    }

                    public long getUnread_pool() {
                        return unread_pool;
                    }

                    public void setUnread_pool(long unread_pool) {
                        this.unread_pool = unread_pool;
                    }

                    public long getDaiyan() {
                        return daiyan;
                    }

                    public void setDaiyan(long daiyan) {
                        this.daiyan = daiyan;
                    }

                    public long getAli_1688() {
                        return ali_1688;
                    }

                    public void setAli_1688(long ali_1688) {
                        this.ali_1688 = ali_1688;
                    }

                    public long getVip_activity2() {
                        return vip_activity2;
                    }

                    public void setVip_activity2(long vip_activity2) {
                        this.vip_activity2 = vip_activity2;
                    }

                    public long getSuishoupai_2016() {
                        return suishoupai_2016;
                    }

                    public void setSuishoupai_2016(long suishoupai_2016) {
                        this.suishoupai_2016 = suishoupai_2016;
                    }

                    public long getFools_day_2016() {
                        return fools_day_2016;
                    }

                    public void setFools_day_2016(long fools_day_2016) {
                        this.fools_day_2016 = fools_day_2016;
                    }

                    public long getUefa_euro_2016() {
                        return uefa_euro_2016;
                    }

                    public void setUefa_euro_2016(long uefa_euro_2016) {
                        this.uefa_euro_2016 = uefa_euro_2016;
                    }

                    public long getSuper_star_2016() {
                        return super_star_2016;
                    }

                    public void setSuper_star_2016(long super_star_2016) {
                        this.super_star_2016 = super_star_2016;
                    }

                    public long getUnread_pool_ext() {
                        return unread_pool_ext;
                    }

                    public void setUnread_pool_ext(long unread_pool_ext) {
                        this.unread_pool_ext = unread_pool_ext;
                    }

                    public long getSelf_media() {
                        return self_media;
                    }

                    public void setSelf_media(long self_media) {
                        this.self_media = self_media;
                    }

                    public long getOlympic_2016() {
                        return olympic_2016;
                    }

                    public void setOlympic_2016(long olympic_2016) {
                        this.olympic_2016 = olympic_2016;
                    }

                    public long getDzwbqlx_2016() {
                        return dzwbqlx_2016;
                    }

                    public void setDzwbqlx_2016(long dzwbqlx_2016) {
                        this.dzwbqlx_2016 = dzwbqlx_2016;
                    }

                    public long getDiscount_2016() {
                        return discount_2016;
                    }

                    public void setDiscount_2016(long discount_2016) {
                        this.discount_2016 = discount_2016;
                    }

                    public long getWedding_2016() {
                        return wedding_2016;
                    }

                    public void setWedding_2016(long wedding_2016) {
                        this.wedding_2016 = wedding_2016;
                    }

                    public long getShuang11_2016() {
                        return shuang11_2016;
                    }

                    public void setShuang11_2016(long shuang11_2016) {
                        this.shuang11_2016 = shuang11_2016;
                    }

                    public long getFollow_whitelist_video() {
                        return follow_whitelist_video;
                    }

                    public void setFollow_whitelist_video(long follow_whitelist_video) {
                        this.follow_whitelist_video = follow_whitelist_video;
                    }

                    public long getWbzy_2016() {
                        return wbzy_2016;
                    }

                    public void setWbzy_2016(long wbzy_2016) {
                        this.wbzy_2016 = wbzy_2016;
                    }

                    public long getHongbao_2017() {
                        return hongbao_2017;
                    }

                    public void setHongbao_2017(long hongbao_2017) {
                        this.hongbao_2017 = hongbao_2017;
                    }

                    public long getHongbao_2017_2() {
                        return hongbao_2017_2;
                    }

                    public void setHongbao_2017_2(long hongbao_2017_2) {
                        this.hongbao_2017_2 = hongbao_2017_2;
                    }

                    public long getCaishen_2017() {
                        return caishen_2017;
                    }

                    public void setCaishen_2017(long caishen_2017) {
                        this.caishen_2017 = caishen_2017;
                    }

                    public long getWedding_2017() {
                        return wedding_2017;
                    }

                    public void setWedding_2017(long wedding_2017) {
                        this.wedding_2017 = wedding_2017;
                    }

                    public long getLeague_badge() {
                        return league_badge;
                    }

                    public void setLeague_badge(long league_badge) {
                        this.league_badge = league_badge;
                    }
                }

                public static class ExtendBeanX {
                    /**
                     * privacy : {"mobile":1}
                     * mbprivilege : 0000000000000000000000000000000000000000000000000000000000000000
                     */

                    private PrivacyBeanX privacy;
                    private String mbprivilege;

                    public PrivacyBeanX getPrivacy() {
                        return privacy;
                    }

                    public void setPrivacy(PrivacyBeanX privacy) {
                        this.privacy = privacy;
                    }

                    public String getMbprivilege() {
                        return mbprivilege;
                    }

                    public void setMbprivilege(String mbprivilege) {
                        this.mbprivilege = mbprivilege;
                    }

                    public static class PrivacyBeanX {
                        /**
                         * mobile : 1
                         */

                        private long mobile;

                        public long getMobile() {
                            return mobile;
                        }

                        public void setMobile(long mobile) {
                            this.mobile = mobile;
                        }
                    }
                }
            }

            public static class RetweetedStatusBean {
                /**
                 * created_at : Mon Apr 10 21:26:11 +0800 2017
                 * id : 4095097421681412
                 * mid : 4095097421681412
                 * idstr : 4095097421681412
                 * text : hello
                 * textLength : 5
                 * source_allowclick : 0
                 * source_type : 1
                 * source : <a href="http://app.weibo.com/t/feed/H6Loy" rel="nofollow">未通过审核应用</a>
                 * appid : 2326732
                 * favorited : false
                 * truncated : false
                 * in_reply_to_status_id :
                 * in_reply_to_user_id :
                 * in_reply_to_screen_name :
                 * pic_ids : []
                 * geo : null
                 * user : {"id":3903514164,"idstr":"3903514164","class":1,"screen_name":"如果爱-就勇敢爱","name":"如果爱-就勇敢爱","province":"100","city":"1000","location":"其他","description":"做真的自我","url":"","profile_image_url":"http://tva1.sinaimg.cn/crop.0.0.180.180.50/e8aae634jw8eakgk3uvxlj2050050weg.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3903514164","domain":"","weihao":"","gender":"m","followers_count":24,"friends_count":209,"pagefriends_count":1,"statuses_count":178,"favourites_count":17,"created_at":"Thu Nov 14 12:54:49 +0800 2013","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.0.0.180.180.180/e8aae634jw8eakgk3uvxlj2050050weg.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.0.0.180.180.1024/e8aae634jw8eakgk3uvxlj2050050weg.jpg",
                 * "verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":8,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"level":1,"type":1,"ulevel":0,"badge":{"uc_domain":0,"enterprise":0,"anniversary":0,"taobao":0,"travel2013":0,"gongyi":0,"gongyi_level":0,"bind_taobao":1,"hongbao_2014":1,"suishoupai_2014":0,"dailv":0,"zongyiji":0,"vip_activity1":0,"unread_pool":1,"daiyan":0,"ali_1688":0,"vip_activity2":0,"suishoupai_2016":0,"fools_day_2016":0,"uefa_euro_2016":0,"super_star_2016":0,"unread_pool_ext":0,"self_media":0,"olympic_2016":0,"dzwbqlx_2016":0,"discount_2016":0,"wedding_2016":0,"shuang11_2016":0,"follow_whitelist_video":0,"wbzy_2016":0,"hongbao_2017":0,"hongbao_2017_2":0,"caishen_2017":0,"wedding_2017":1,"league_badge":0},"badge_top":"","has_ability_tag":0,"extend":{"privacy":{"mobile":1},
                 * "mbprivilege":"0000000000000000000000000000000000000000000000000000000000000000"},"credit_score":80,"user_ability":0,"urank":9}
                 * reposts_count : 0
                 * comments_count : 0
                 * attitudes_count : 0
                 * isLongText : false
                 * mlevel : 0
                 * visible : {"type":0,"list_id":0}
                 * biz_feature : 0
                 * url_objects : []
                 * hasActionTypeCard : 0
                 * darwin_tags : []
                 * hot_weibo_tags : []
                 * text_tag_tips : []
                 * userType : 0
                 * positive_recom_flag : 0
                 * gif_ids :
                 * is_show_bulletin : 2
                 */

                private String created_at;
                private long id;
                private String mid;
                private String idstr;
                private String text;
                private long textLength;
                private long source_allowclick;
                private long source_type;
                private String source;
                private long appid;
                private boolean favorited;
                private boolean truncated;
                private String in_reply_to_status_id;
                private String in_reply_to_user_id;
                private String in_reply_to_screen_name;
                private Object geo;
                private UserBeanXX user;
                private long reposts_count;
                private long comments_count;
                private long attitudes_count;
                private boolean isLongText;
                private long mlevel;
                private VisibleBean visible;
                private long biz_feature;
                private long hasActionTypeCard;
                private long userType;
                private long positive_recom_flag;
                private String gif_ids;
                private long is_show_bulletin;
                private List<?> pic_ids;
                private List<?> url_objects;
                private List<?> darwin_tags;
                private List<?> hot_weibo_tags;
                private List<?> text_tag_tips;

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getIdstr() {
                    return idstr;
                }

                public void setIdstr(String idstr) {
                    this.idstr = idstr;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public long getTextLength() {
                    return textLength;
                }

                public void setTextLength(long textLength) {
                    this.textLength = textLength;
                }

                public long getSource_allowclick() {
                    return source_allowclick;
                }

                public void setSource_allowclick(long source_allowclick) {
                    this.source_allowclick = source_allowclick;
                }

                public long getSource_type() {
                    return source_type;
                }

                public void setSource_type(long source_type) {
                    this.source_type = source_type;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public long getAppid() {
                    return appid;
                }

                public void setAppid(long appid) {
                    this.appid = appid;
                }

                public boolean isFavorited() {
                    return favorited;
                }

                public void setFavorited(boolean favorited) {
                    this.favorited = favorited;
                }

                public boolean isTruncated() {
                    return truncated;
                }

                public void setTruncated(boolean truncated) {
                    this.truncated = truncated;
                }

                public String getIn_reply_to_status_id() {
                    return in_reply_to_status_id;
                }

                public void setIn_reply_to_status_id(String in_reply_to_status_id) {
                    this.in_reply_to_status_id = in_reply_to_status_id;
                }

                public String getIn_reply_to_user_id() {
                    return in_reply_to_user_id;
                }

                public void setIn_reply_to_user_id(String in_reply_to_user_id) {
                    this.in_reply_to_user_id = in_reply_to_user_id;
                }

                public String getIn_reply_to_screen_name() {
                    return in_reply_to_screen_name;
                }

                public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
                    this.in_reply_to_screen_name = in_reply_to_screen_name;
                }

                public Object getGeo() {
                    return geo;
                }

                public void setGeo(Object geo) {
                    this.geo = geo;
                }

                public UserBeanXX getUser() {
                    return user;
                }

                public void setUser(UserBeanXX user) {
                    this.user = user;
                }

                public long getReposts_count() {
                    return reposts_count;
                }

                public void setReposts_count(long reposts_count) {
                    this.reposts_count = reposts_count;
                }

                public long getComments_count() {
                    return comments_count;
                }

                public void setComments_count(long comments_count) {
                    this.comments_count = comments_count;
                }

                public long getAttitudes_count() {
                    return attitudes_count;
                }

                public void setAttitudes_count(long attitudes_count) {
                    this.attitudes_count = attitudes_count;
                }

                public boolean isIsLongText() {
                    return isLongText;
                }

                public void setIsLongText(boolean isLongText) {
                    this.isLongText = isLongText;
                }

                public long getMlevel() {
                    return mlevel;
                }

                public void setMlevel(long mlevel) {
                    this.mlevel = mlevel;
                }

                public VisibleBean getVisible() {
                    return visible;
                }

                public void setVisible(VisibleBean visible) {
                    this.visible = visible;
                }

                public long getBiz_feature() {
                    return biz_feature;
                }

                public void setBiz_feature(long biz_feature) {
                    this.biz_feature = biz_feature;
                }

                public long getHasActionTypeCard() {
                    return hasActionTypeCard;
                }

                public void setHasActionTypeCard(long hasActionTypeCard) {
                    this.hasActionTypeCard = hasActionTypeCard;
                }

                public long getUserType() {
                    return userType;
                }

                public void setUserType(long userType) {
                    this.userType = userType;
                }

                public long getPositive_recom_flag() {
                    return positive_recom_flag;
                }

                public void setPositive_recom_flag(long positive_recom_flag) {
                    this.positive_recom_flag = positive_recom_flag;
                }

                public String getGif_ids() {
                    return gif_ids;
                }

                public void setGif_ids(String gif_ids) {
                    this.gif_ids = gif_ids;
                }

                public long getIs_show_bulletin() {
                    return is_show_bulletin;
                }

                public void setIs_show_bulletin(long is_show_bulletin) {
                    this.is_show_bulletin = is_show_bulletin;
                }

                public List<?> getPic_ids() {
                    return pic_ids;
                }

                public void setPic_ids(List<?> pic_ids) {
                    this.pic_ids = pic_ids;
                }

                public List<?> getUrl_objects() {
                    return url_objects;
                }

                public void setUrl_objects(List<?> url_objects) {
                    this.url_objects = url_objects;
                }

                public List<?> getDarwin_tags() {
                    return darwin_tags;
                }

                public void setDarwin_tags(List<?> darwin_tags) {
                    this.darwin_tags = darwin_tags;
                }

                public List<?> getHot_weibo_tags() {
                    return hot_weibo_tags;
                }

                public void setHot_weibo_tags(List<?> hot_weibo_tags) {
                    this.hot_weibo_tags = hot_weibo_tags;
                }

                public List<?> getText_tag_tips() {
                    return text_tag_tips;
                }

                public void setText_tag_tips(List<?> text_tag_tips) {
                    this.text_tag_tips = text_tag_tips;
                }

                public static class UserBeanXX {
                    /**
                     * id : 3903514164
                     * idstr : 3903514164
                     * class : 1
                     * screen_name : 如果爱-就勇敢爱
                     * name : 如果爱-就勇敢爱
                     * province : 100
                     * city : 1000
                     * location : 其他
                     * description : 做真的自我
                     * url :
                     * profile_image_url : http://tva1.sinaimg.cn/crop.0.0.180.180.50/e8aae634jw8eakgk3uvxlj2050050weg.jpg
                     * cover_image_phone : http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg
                     * profile_url : u/3903514164
                     * domain :
                     * weihao :
                     * gender : m
                     * followers_count : 24
                     * friends_count : 209
                     * pagefriends_count : 1
                     * statuses_count : 178
                     * favourites_count : 17
                     * created_at : Thu Nov 14 12:54:49 +0800 2013
                     * following : false
                     * allow_all_act_msg : false
                     * geo_enabled : true
                     * verified : false
                     * verified_type : -1
                     * remark :
                     * insecurity : {"sexual_content":false}
                     * ptype : 0
                     * allow_all_comment : true
                     * avatar_large : http://tva1.sinaimg.cn/crop.0.0.180.180.180/e8aae634jw8eakgk3uvxlj2050050weg.jpg
                     * avatar_hd : http://tva1.sinaimg.cn/crop.0.0.180.180.1024/e8aae634jw8eakgk3uvxlj2050050weg.jpg
                     * verified_reason :
                     * verified_trade :
                     * verified_reason_url :
                     * verified_source :
                     * verified_source_url :
                     * follow_me : false
                     * online_status : 0
                     * bi_followers_count : 8
                     * lang : zh-cn
                     * star : 0
                     * mbtype : 0
                     * mbrank : 0
                     * block_word : 0
                     * block_app : 0
                     * level : 1
                     * type : 1
                     * ulevel : 0
                     * badge : {"uc_domain":0,"enterprise":0,"anniversary":0,"taobao":0,"travel2013":0,"gongyi":0,"gongyi_level":0,"bind_taobao":1,"hongbao_2014":1,"suishoupai_2014":0,"dailv":0,"zongyiji":0,"vip_activity1":0,"unread_pool":1,"daiyan":0,"ali_1688":0,"vip_activity2":0,"suishoupai_2016":0,"fools_day_2016":0,"uefa_euro_2016":0,"super_star_2016":0,"unread_pool_ext":0,"self_media":0,"olympic_2016":0,"dzwbqlx_2016":0,"discount_2016":0,"wedding_2016":0,"shuang11_2016":0,"follow_whitelist_video":0,"wbzy_2016":0,"hongbao_2017":0,"hongbao_2017_2":0,"caishen_2017":0,"wedding_2017":1,"league_badge":0}
                     * badge_top :
                     * has_ability_tag : 0
                     * extend : {"privacy":{"mobile":1},"mbprivilege":"0000000000000000000000000000000000000000000000000000000000000000"}
                     * credit_score : 80
                     * user_ability : 0
                     * urank : 9
                     */

                    private long id;
                    private String idstr;
                    @SerializedName("class")
                    private long classX;
                    private String screen_name;
                    private String name;
                    private String province;
                    private String city;
                    private String location;
                    private String description;
                    private String url;
                    private String profile_image_url;
                    private String cover_image_phone;
                    private String profile_url;
                    private String domain;
                    private String weihao;
                    private String gender;
                    private long followers_count;
                    private long friends_count;
                    private long pagefriends_count;
                    private long statuses_count;
                    private long favourites_count;
                    private String created_at;
                    private boolean following;
                    private boolean allow_all_act_msg;
                    private boolean geo_enabled;
                    private boolean verified;
                    private long verified_type;
                    private String remark;
                    private InsecurityBeanXX insecurity;
                    private long ptype;
                    private boolean allow_all_comment;
                    private String avatar_large;
                    private String avatar_hd;
                    private String verified_reason;
                    private String verified_trade;
                    private String verified_reason_url;
                    private String verified_source;
                    private String verified_source_url;
                    private boolean follow_me;
                    private long online_status;
                    private long bi_followers_count;
                    private String lang;
                    private long star;
                    private long mbtype;
                    private long mbrank;
                    private long block_word;
                    private long block_app;
                    private long level;
                    private long type;
                    private long ulevel;
                    private BadgeBeanXX badge;
                    private String badge_top;
                    private long has_ability_tag;
                    private ExtendBeanXX extend;
                    private long credit_score;
                    private long user_ability;
                    private long urank;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public String getIdstr() {
                        return idstr;
                    }

                    public void setIdstr(String idstr) {
                        this.idstr = idstr;
                    }

                    public long getClassX() {
                        return classX;
                    }

                    public void setClassX(long classX) {
                        this.classX = classX;
                    }

                    public String getScreen_name() {
                        return screen_name;
                    }

                    public void setScreen_name(String screen_name) {
                        this.screen_name = screen_name;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
                    }

                    public String getCity() {
                        return city;
                    }

                    public void setCity(String city) {
                        this.city = city;
                    }

                    public String getLocation() {
                        return location;
                    }

                    public void setLocation(String location) {
                        this.location = location;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public String getProfile_image_url() {
                        return profile_image_url;
                    }

                    public void setProfile_image_url(String profile_image_url) {
                        this.profile_image_url = profile_image_url;
                    }

                    public String getCover_image_phone() {
                        return cover_image_phone;
                    }

                    public void setCover_image_phone(String cover_image_phone) {
                        this.cover_image_phone = cover_image_phone;
                    }

                    public String getProfile_url() {
                        return profile_url;
                    }

                    public void setProfile_url(String profile_url) {
                        this.profile_url = profile_url;
                    }

                    public String getDomain() {
                        return domain;
                    }

                    public void setDomain(String domain) {
                        this.domain = domain;
                    }

                    public String getWeihao() {
                        return weihao;
                    }

                    public void setWeihao(String weihao) {
                        this.weihao = weihao;
                    }

                    public String getGender() {
                        return gender;
                    }

                    public void setGender(String gender) {
                        this.gender = gender;
                    }

                    public long getFollowers_count() {
                        return followers_count;
                    }

                    public void setFollowers_count(long followers_count) {
                        this.followers_count = followers_count;
                    }

                    public long getFriends_count() {
                        return friends_count;
                    }

                    public void setFriends_count(long friends_count) {
                        this.friends_count = friends_count;
                    }

                    public long getPagefriends_count() {
                        return pagefriends_count;
                    }

                    public void setPagefriends_count(long pagefriends_count) {
                        this.pagefriends_count = pagefriends_count;
                    }

                    public long getStatuses_count() {
                        return statuses_count;
                    }

                    public void setStatuses_count(long statuses_count) {
                        this.statuses_count = statuses_count;
                    }

                    public long getFavourites_count() {
                        return favourites_count;
                    }

                    public void setFavourites_count(long favourites_count) {
                        this.favourites_count = favourites_count;
                    }

                    public String getCreated_at() {
                        return created_at;
                    }

                    public void setCreated_at(String created_at) {
                        this.created_at = created_at;
                    }

                    public boolean isFollowing() {
                        return following;
                    }

                    public void setFollowing(boolean following) {
                        this.following = following;
                    }

                    public boolean isAllow_all_act_msg() {
                        return allow_all_act_msg;
                    }

                    public void setAllow_all_act_msg(boolean allow_all_act_msg) {
                        this.allow_all_act_msg = allow_all_act_msg;
                    }

                    public boolean isGeo_enabled() {
                        return geo_enabled;
                    }

                    public void setGeo_enabled(boolean geo_enabled) {
                        this.geo_enabled = geo_enabled;
                    }

                    public boolean isVerified() {
                        return verified;
                    }

                    public void setVerified(boolean verified) {
                        this.verified = verified;
                    }

                    public long getVerified_type() {
                        return verified_type;
                    }

                    public void setVerified_type(long verified_type) {
                        this.verified_type = verified_type;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public InsecurityBeanXX getInsecurity() {
                        return insecurity;
                    }

                    public void setInsecurity(InsecurityBeanXX insecurity) {
                        this.insecurity = insecurity;
                    }

                    public long getPtype() {
                        return ptype;
                    }

                    public void setPtype(long ptype) {
                        this.ptype = ptype;
                    }

                    public boolean isAllow_all_comment() {
                        return allow_all_comment;
                    }

                    public void setAllow_all_comment(boolean allow_all_comment) {
                        this.allow_all_comment = allow_all_comment;
                    }

                    public String getAvatar_large() {
                        return avatar_large;
                    }

                    public void setAvatar_large(String avatar_large) {
                        this.avatar_large = avatar_large;
                    }

                    public String getAvatar_hd() {
                        return avatar_hd;
                    }

                    public void setAvatar_hd(String avatar_hd) {
                        this.avatar_hd = avatar_hd;
                    }

                    public String getVerified_reason() {
                        return verified_reason;
                    }

                    public void setVerified_reason(String verified_reason) {
                        this.verified_reason = verified_reason;
                    }

                    public String getVerified_trade() {
                        return verified_trade;
                    }

                    public void setVerified_trade(String verified_trade) {
                        this.verified_trade = verified_trade;
                    }

                    public String getVerified_reason_url() {
                        return verified_reason_url;
                    }

                    public void setVerified_reason_url(String verified_reason_url) {
                        this.verified_reason_url = verified_reason_url;
                    }

                    public String getVerified_source() {
                        return verified_source;
                    }

                    public void setVerified_source(String verified_source) {
                        this.verified_source = verified_source;
                    }

                    public String getVerified_source_url() {
                        return verified_source_url;
                    }

                    public void setVerified_source_url(String verified_source_url) {
                        this.verified_source_url = verified_source_url;
                    }

                    public boolean isFollow_me() {
                        return follow_me;
                    }

                    public void setFollow_me(boolean follow_me) {
                        this.follow_me = follow_me;
                    }

                    public long getOnline_status() {
                        return online_status;
                    }

                    public void setOnline_status(long online_status) {
                        this.online_status = online_status;
                    }

                    public long getBi_followers_count() {
                        return bi_followers_count;
                    }

                    public void setBi_followers_count(long bi_followers_count) {
                        this.bi_followers_count = bi_followers_count;
                    }

                    public String getLang() {
                        return lang;
                    }

                    public void setLang(String lang) {
                        this.lang = lang;
                    }

                    public long getStar() {
                        return star;
                    }

                    public void setStar(long star) {
                        this.star = star;
                    }

                    public long getMbtype() {
                        return mbtype;
                    }

                    public void setMbtype(long mbtype) {
                        this.mbtype = mbtype;
                    }

                    public long getMbrank() {
                        return mbrank;
                    }

                    public void setMbrank(long mbrank) {
                        this.mbrank = mbrank;
                    }

                    public long getBlock_word() {
                        return block_word;
                    }

                    public void setBlock_word(long block_word) {
                        this.block_word = block_word;
                    }

                    public long getBlock_app() {
                        return block_app;
                    }

                    public void setBlock_app(long block_app) {
                        this.block_app = block_app;
                    }

                    public long getLevel() {
                        return level;
                    }

                    public void setLevel(long level) {
                        this.level = level;
                    }

                    public long getType() {
                        return type;
                    }

                    public void setType(long type) {
                        this.type = type;
                    }

                    public long getUlevel() {
                        return ulevel;
                    }

                    public void setUlevel(long ulevel) {
                        this.ulevel = ulevel;
                    }

                    public BadgeBeanXX getBadge() {
                        return badge;
                    }

                    public void setBadge(BadgeBeanXX badge) {
                        this.badge = badge;
                    }

                    public String getBadge_top() {
                        return badge_top;
                    }

                    public void setBadge_top(String badge_top) {
                        this.badge_top = badge_top;
                    }

                    public long getHas_ability_tag() {
                        return has_ability_tag;
                    }

                    public void setHas_ability_tag(long has_ability_tag) {
                        this.has_ability_tag = has_ability_tag;
                    }

                    public ExtendBeanXX getExtend() {
                        return extend;
                    }

                    public void setExtend(ExtendBeanXX extend) {
                        this.extend = extend;
                    }

                    public long getCredit_score() {
                        return credit_score;
                    }

                    public void setCredit_score(long credit_score) {
                        this.credit_score = credit_score;
                    }

                    public long getUser_ability() {
                        return user_ability;
                    }

                    public void setUser_ability(long user_ability) {
                        this.user_ability = user_ability;
                    }

                    public long getUrank() {
                        return urank;
                    }

                    public void setUrank(long urank) {
                        this.urank = urank;
                    }

                    public static class InsecurityBeanXX {
                        /**
                         * sexual_content : false
                         */

                        private boolean sexual_content;

                        public boolean isSexual_content() {
                            return sexual_content;
                        }

                        public void setSexual_content(boolean sexual_content) {
                            this.sexual_content = sexual_content;
                        }
                    }

                    public static class BadgeBeanXX {
                        /**
                         * uc_domain : 0
                         * enterprise : 0
                         * anniversary : 0
                         * taobao : 0
                         * travel2013 : 0
                         * gongyi : 0
                         * gongyi_level : 0
                         * bind_taobao : 1
                         * hongbao_2014 : 1
                         * suishoupai_2014 : 0
                         * dailv : 0
                         * zongyiji : 0
                         * vip_activity1 : 0
                         * unread_pool : 1
                         * daiyan : 0
                         * ali_1688 : 0
                         * vip_activity2 : 0
                         * suishoupai_2016 : 0
                         * fools_day_2016 : 0
                         * uefa_euro_2016 : 0
                         * super_star_2016 : 0
                         * unread_pool_ext : 0
                         * self_media : 0
                         * olympic_2016 : 0
                         * dzwbqlx_2016 : 0
                         * discount_2016 : 0
                         * wedding_2016 : 0
                         * shuang11_2016 : 0
                         * follow_whitelist_video : 0
                         * wbzy_2016 : 0
                         * hongbao_2017 : 0
                         * hongbao_2017_2 : 0
                         * caishen_2017 : 0
                         * wedding_2017 : 1
                         * league_badge : 0
                         */

                        private long uc_domain;
                        private long enterprise;
                        private long anniversary;
                        private long taobao;
                        private long travel2013;
                        private long gongyi;
                        private long gongyi_level;
                        private long bind_taobao;
                        private long hongbao_2014;
                        private long suishoupai_2014;
                        private long dailv;
                        private long zongyiji;
                        private long vip_activity1;
                        private long unread_pool;
                        private long daiyan;
                        private long ali_1688;
                        private long vip_activity2;
                        private long suishoupai_2016;
                        private long fools_day_2016;
                        private long uefa_euro_2016;
                        private long super_star_2016;
                        private long unread_pool_ext;
                        private long self_media;
                        private long olympic_2016;
                        private long dzwbqlx_2016;
                        private long discount_2016;
                        private long wedding_2016;
                        private long shuang11_2016;
                        private long follow_whitelist_video;
                        private long wbzy_2016;
                        private long hongbao_2017;
                        private long hongbao_2017_2;
                        private long caishen_2017;
                        private long wedding_2017;
                        private long league_badge;

                        public long getUc_domain() {
                            return uc_domain;
                        }

                        public void setUc_domain(long uc_domain) {
                            this.uc_domain = uc_domain;
                        }

                        public long getEnterprise() {
                            return enterprise;
                        }

                        public void setEnterprise(long enterprise) {
                            this.enterprise = enterprise;
                        }

                        public long getAnniversary() {
                            return anniversary;
                        }

                        public void setAnniversary(long anniversary) {
                            this.anniversary = anniversary;
                        }

                        public long getTaobao() {
                            return taobao;
                        }

                        public void setTaobao(long taobao) {
                            this.taobao = taobao;
                        }

                        public long getTravel2013() {
                            return travel2013;
                        }

                        public void setTravel2013(long travel2013) {
                            this.travel2013 = travel2013;
                        }

                        public long getGongyi() {
                            return gongyi;
                        }

                        public void setGongyi(long gongyi) {
                            this.gongyi = gongyi;
                        }

                        public long getGongyi_level() {
                            return gongyi_level;
                        }

                        public void setGongyi_level(long gongyi_level) {
                            this.gongyi_level = gongyi_level;
                        }

                        public long getBind_taobao() {
                            return bind_taobao;
                        }

                        public void setBind_taobao(long bind_taobao) {
                            this.bind_taobao = bind_taobao;
                        }

                        public long getHongbao_2014() {
                            return hongbao_2014;
                        }

                        public void setHongbao_2014(long hongbao_2014) {
                            this.hongbao_2014 = hongbao_2014;
                        }

                        public long getSuishoupai_2014() {
                            return suishoupai_2014;
                        }

                        public void setSuishoupai_2014(long suishoupai_2014) {
                            this.suishoupai_2014 = suishoupai_2014;
                        }

                        public long getDailv() {
                            return dailv;
                        }

                        public void setDailv(long dailv) {
                            this.dailv = dailv;
                        }

                        public long getZongyiji() {
                            return zongyiji;
                        }

                        public void setZongyiji(long zongyiji) {
                            this.zongyiji = zongyiji;
                        }

                        public long getVip_activity1() {
                            return vip_activity1;
                        }

                        public void setVip_activity1(long vip_activity1) {
                            this.vip_activity1 = vip_activity1;
                        }

                        public long getUnread_pool() {
                            return unread_pool;
                        }

                        public void setUnread_pool(long unread_pool) {
                            this.unread_pool = unread_pool;
                        }

                        public long getDaiyan() {
                            return daiyan;
                        }

                        public void setDaiyan(long daiyan) {
                            this.daiyan = daiyan;
                        }

                        public long getAli_1688() {
                            return ali_1688;
                        }

                        public void setAli_1688(long ali_1688) {
                            this.ali_1688 = ali_1688;
                        }

                        public long getVip_activity2() {
                            return vip_activity2;
                        }

                        public void setVip_activity2(long vip_activity2) {
                            this.vip_activity2 = vip_activity2;
                        }

                        public long getSuishoupai_2016() {
                            return suishoupai_2016;
                        }

                        public void setSuishoupai_2016(long suishoupai_2016) {
                            this.suishoupai_2016 = suishoupai_2016;
                        }

                        public long getFools_day_2016() {
                            return fools_day_2016;
                        }

                        public void setFools_day_2016(long fools_day_2016) {
                            this.fools_day_2016 = fools_day_2016;
                        }

                        public long getUefa_euro_2016() {
                            return uefa_euro_2016;
                        }

                        public void setUefa_euro_2016(long uefa_euro_2016) {
                            this.uefa_euro_2016 = uefa_euro_2016;
                        }

                        public long getSuper_star_2016() {
                            return super_star_2016;
                        }

                        public void setSuper_star_2016(long super_star_2016) {
                            this.super_star_2016 = super_star_2016;
                        }

                        public long getUnread_pool_ext() {
                            return unread_pool_ext;
                        }

                        public void setUnread_pool_ext(long unread_pool_ext) {
                            this.unread_pool_ext = unread_pool_ext;
                        }

                        public long getSelf_media() {
                            return self_media;
                        }

                        public void setSelf_media(long self_media) {
                            this.self_media = self_media;
                        }

                        public long getOlympic_2016() {
                            return olympic_2016;
                        }

                        public void setOlympic_2016(long olympic_2016) {
                            this.olympic_2016 = olympic_2016;
                        }

                        public long getDzwbqlx_2016() {
                            return dzwbqlx_2016;
                        }

                        public void setDzwbqlx_2016(long dzwbqlx_2016) {
                            this.dzwbqlx_2016 = dzwbqlx_2016;
                        }

                        public long getDiscount_2016() {
                            return discount_2016;
                        }

                        public void setDiscount_2016(long discount_2016) {
                            this.discount_2016 = discount_2016;
                        }

                        public long getWedding_2016() {
                            return wedding_2016;
                        }

                        public void setWedding_2016(long wedding_2016) {
                            this.wedding_2016 = wedding_2016;
                        }

                        public long getShuang11_2016() {
                            return shuang11_2016;
                        }

                        public void setShuang11_2016(long shuang11_2016) {
                            this.shuang11_2016 = shuang11_2016;
                        }

                        public long getFollow_whitelist_video() {
                            return follow_whitelist_video;
                        }

                        public void setFollow_whitelist_video(long follow_whitelist_video) {
                            this.follow_whitelist_video = follow_whitelist_video;
                        }

                        public long getWbzy_2016() {
                            return wbzy_2016;
                        }

                        public void setWbzy_2016(long wbzy_2016) {
                            this.wbzy_2016 = wbzy_2016;
                        }

                        public long getHongbao_2017() {
                            return hongbao_2017;
                        }

                        public void setHongbao_2017(long hongbao_2017) {
                            this.hongbao_2017 = hongbao_2017;
                        }

                        public long getHongbao_2017_2() {
                            return hongbao_2017_2;
                        }

                        public void setHongbao_2017_2(long hongbao_2017_2) {
                            this.hongbao_2017_2 = hongbao_2017_2;
                        }

                        public long getCaishen_2017() {
                            return caishen_2017;
                        }

                        public void setCaishen_2017(long caishen_2017) {
                            this.caishen_2017 = caishen_2017;
                        }

                        public long getWedding_2017() {
                            return wedding_2017;
                        }

                        public void setWedding_2017(long wedding_2017) {
                            this.wedding_2017 = wedding_2017;
                        }

                        public long getLeague_badge() {
                            return league_badge;
                        }

                        public void setLeague_badge(long league_badge) {
                            this.league_badge = league_badge;
                        }
                    }

                    public static class ExtendBeanXX {
                        /**
                         * privacy : {"mobile":1}
                         * mbprivilege : 0000000000000000000000000000000000000000000000000000000000000000
                         */

                        private PrivacyBeanXX privacy;
                        private String mbprivilege;

                        public PrivacyBeanXX getPrivacy() {
                            return privacy;
                        }

                        public void setPrivacy(PrivacyBeanXX privacy) {
                            this.privacy = privacy;
                        }

                        public String getMbprivilege() {
                            return mbprivilege;
                        }

                        public void setMbprivilege(String mbprivilege) {
                            this.mbprivilege = mbprivilege;
                        }

                        public static class PrivacyBeanXX {
                            /**
                             * mobile : 1
                             */

                            private long mobile;

                            public long getMobile() {
                                return mobile;
                            }

                            public void setMobile(long mobile) {
                                this.mobile = mobile;
                            }
                        }
                    }
                }

                public static class VisibleBean {
                    /**
                     * type : 0
                     * list_id : 0
                     */

                    private long type;
                    private long list_id;

                    public long getType() {
                        return type;
                    }

                    public void setType(long type) {
                        this.type = type;
                    }

                    public long getList_id() {
                        return list_id;
                    }

                    public void setList_id(long list_id) {
                        this.list_id = list_id;
                    }
                }
            }

            public static class VisibleBeanX {
                /**
                 * type : 0
                 * list_id : 0
                 */

                private long type;
                private long list_id;

                public long getType() {
                    return type;
                }

                public void setType(long type) {
                    this.type = type;
                }

                public long getList_id() {
                    return list_id;
                }

                public void setList_id(long list_id) {
                    this.list_id = list_id;
                }
            }
        }
    }
}
