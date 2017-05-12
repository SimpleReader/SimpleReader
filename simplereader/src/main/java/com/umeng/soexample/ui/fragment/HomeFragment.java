package com.umeng.soexample.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.base.BaseMvpFragment;
import com.umeng.soexample.presenter.HomePresenter;
import com.umeng.soexample.ui.activity.ChannelActivity;
import com.umeng.soexample.ui.adapter.TitlePagerAdapter;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.soexample.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:主页页面
 * Created by chenggong on 2017/3/28.
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements IHomeView {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.icon_category)
    ImageView iconCategory;
    private String[] titles = new String[]{};
    private String[] titlesCode = new String[]{};
    private static final int REQUEST_CHANNEL = 111;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void processLogic() {
        titles = getResources().getStringArray(R.array.tab_titles);
        titlesCode = getResources().getStringArray(R.array.tab_titles_code);
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            NewsListFragment fragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ConstanceValue.DATA, titlesCode[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        viewPager.setAdapter(new TitlePagerAdapter(getChildFragmentManager(), fragments, titles));
        viewPager.setOffscreenPageLimit(titles.length);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//tablayout的滑动模式
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.icon_category})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_category:
                Intent intent = new Intent(mContext, ChannelActivity.class);
                startActivityForResult(intent, REQUEST_CHANNEL);
                break;
        }
    }
}
