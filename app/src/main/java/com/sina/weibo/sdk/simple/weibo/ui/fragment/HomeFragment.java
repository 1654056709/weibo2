package com.sina.weibo.sdk.simple.weibo.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.simple.weibo.R;
import com.sina.weibo.sdk.simple.weibo.adapter.WeiboAdapter;
import com.sina.weibo.sdk.simple.weibo.dao.UserDao;
import com.sina.weibo.sdk.simple.weibo.model.UserInfo;
import com.sina.weibo.sdk.simple.weibo.model.WeiboInfo;
import com.sina.weibo.sdk.simple.weibo.presenter.WeiboInfoPresenter;
import com.sina.weibo.sdk.simple.weibo.ui.activity.HomeActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.LoginActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.MentionUserActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.PublicTimeLineActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.UserFansActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.UserFriendsActivity;
import com.sina.weibo.sdk.simple.weibo.ui.activity.UserTimeLineActivity;
import com.sina.weibo.sdk.simple.weibo.ui.dialog.WriteWeiboDialog;
import com.sina.weibo.sdk.simple.weibo.ui.view.LoadMoreFooterView;
import com.sina.weibo.sdk.simple.weibo.ui.view.RefreshHeaderView;
import com.sina.weibo.sdk.simple.weibo.util.SpaceItemDecoration;
import com.sina.weibo.sdk.simple.weibo.util.Tools;
import com.sina.weibo.sdk.simple.weibo.view.WeiboInfoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 显示用户主页的Fragment
 */
public class HomeFragment extends Fragment {
    private static final String USER_INFO = "user_info";

    Unbinder mUnbinder;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_home_user_head)
    ImageView mFragmentHomeUserHead;
    @BindView(R.id.fragment_home_user_name)
    TextView mFragmentHomeUserName;
    @BindView(R.id.fragment_home_user_desc)
    TextView mFragmentHomeUserDesc;
    @BindView(R.id.fragment_home_function)
    RecyclerView mFragmentHomeFunction;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView mSwipeTarget;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;


    private Oauth2AccessToken mAccessToken;
    private WeiboInfoPresenter mWeiboInfoPresenter;
    private static final int WEIBO_COUNT = 10;
    private static int sWeiboPage = 0;
    private LinearLayoutManager mLinearLayoutManager;
    private List<WeiboInfo> mWeibos;
    private WeiboAdapter mWeiboAdapter;
    private PopupWindow mPopupWindow;
    private List<String> mDataList;


    public HomeFragment() {

    }

    public static HomeFragment newFragment(UserInfo userInfo) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER_INFO, userInfo);
        homeFragment.setArguments(args);
        return homeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null, false);
        mUnbinder = ButterKnife.bind(this, view);
        initToolbar();

        //功能列表
        mFragmentHomeFunction.setLayoutManager(new GridLayoutManager(getActivity(), 3) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });


        mWeiboInfoPresenter = new WeiboInfoPresenter(getActivity());
        mWeiboInfoPresenter.onCreate();


        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSwipeTarget.setLayoutManager(mLinearLayoutManager);

        mWeibos = new ArrayList<>();
        mWeiboAdapter = new WeiboAdapter(getActivity(), mWeibos);
        mSwipeTarget.setAdapter(mWeiboAdapter);

        mFragmentHomeFunction.addItemDecoration(new SpaceItemDecoration(2));
        mDataList = Arrays.asList(getActivity().getResources().getStringArray(R.array.function_list));
        mFragmentHomeFunction.setAdapter(new FunctionAdapter(getActivity(), mDataList));

        mAccessToken = AccessTokenKeeper.readAccessToken(getActivity());
        if (mAccessToken.isSessionValid()) {
            //用户信息
            UserDao userDao = UserDao.getInstance(getActivity());
            UserInfo userInfo = userDao.findUser(mAccessToken.getUid());
            Glide.with(getActivity())
                    .load(UserInfo.getByteArrayOutputStream(userInfo.getUserHead()).toByteArray())
                    .centerCrop()
                    .bitmapTransform(new CropCircleTransformation(getActivity()))
                    .into(mFragmentHomeUserHead);
            mFragmentHomeUserName.setText(userInfo.getUserName());
            mFragmentHomeUserDesc.setText(userInfo.getDescription());

            //第一次自动加载数据
            mSwipeToLoadLayout.setRefreshing(true);

            //上拉刷新
            mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshData(mSwipeToLoadLayout);
                }
            });

            //加载更多数据
            mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    lodaMoreData(mSwipeToLoadLayout);
                }
            });
        }
        return view;
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_fragment_home);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.restart_login:
                        goToPage(LoginActivity.class);
                        break;
                }
                return false;
            }
        });
    }


    class FunctionAdapter extends RecyclerView.Adapter<FunctionHolder> {
        private List<String> mData;
        private Context mContext;

        public FunctionAdapter(Context context, List<String> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public FunctionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(mContext, R.layout.item_fragment_home_function, null);
            return new FunctionHolder(view);
        }

        @Override
        public void onBindViewHolder(FunctionHolder holder, int position) {
            holder.bindFunction(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    class FunctionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_fragment_home_function_text_view)
        TextView mItemFragmentHomeFunctionTextView;
        private String mData;

        public FunctionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mData.equals(mDataList.get(0))) {
                goToPage(MentionUserActivity.class);
            } else if (mData.equals(mDataList.get(1))) {
                goToPage(UserTimeLineActivity.class);
            } else if (mData.equals(mDataList.get(2))) {
                goToPage(UserFansActivity.class);
            } else if (mData.equals(mDataList.get(3))) {
                goToPage(UserFriendsActivity.class);
            } else if (mData.equals(mDataList.get(4))) {
                goToPage(PublicTimeLineActivity.class);
            } else if (mData.equals(mDataList.get(5))) {
                Tools.openWriteWeibo(getActivity().getSupportFragmentManager(), HomeActivity.TAG);
            }
        }

        public void bindFunction(String data) {
            mData = data;
            mItemFragmentHomeFunctionTextView.setText(data);
        }
    }


    /**
     * 去其它页面
     *
     * @param clazz
     */
    public void goToPage(Class clazz) {
        getActivity().startActivity(new Intent(getActivity(), clazz));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mWeiboInfoPresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * 加载更多数据
     */
    private void lodaMoreData(final SwipeToLoadLayout swipeToLoadLayout) {
        if (mAccessToken.isSessionValid()) {
            sWeiboPage++;
            mWeiboInfoPresenter.getHomeTimeLineWeiboInfo(mAccessToken, WEIBO_COUNT, sWeiboPage);
            mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
                @Override
                public void onSuccess(List<WeiboInfo> weibos) {
                    int positionStart = mWeibos.size() - 1;
                    mWeibos.addAll(weibos);
                    mWeiboAdapter.notifyItemRangeChanged(positionStart, WEIBO_COUNT);
                    Tools.MoveToPosition(mLinearLayoutManager, mSwipeTarget);
                    swipeToLoadLayout.setLoadingMore(false);
                }

                @Override
                public void onFailure(String errorMsg) {

                }
            });
        }
    }


    /**
     * 上拉刷新数据
     */
    private void refreshData(final SwipeToLoadLayout swipeToLoadLayout) {
        if (mAccessToken.isSessionValid()) {
            sWeiboPage = 1;
            mWeiboInfoPresenter.getHomeTimeLineWeiboInfo(mAccessToken, WEIBO_COUNT, sWeiboPage);
            mWeiboInfoPresenter.onAttachView(new WeiboInfoView() {
                @Override
                public void onSuccess(List<WeiboInfo> weibos) {
                    Log.d(PublicTimeLineActivity.TAG, weibos.get(0).getWeiboId() + "");
                    mWeibos.clear();
                    mWeibos.addAll(weibos);
                    mWeiboAdapter.notifyDataSetChanged();
                    swipeToLoadLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String errorMsg) {
                    Log.d(PublicTimeLineActivity.TAG, errorMsg);
                }
            });
        }
    }
}
