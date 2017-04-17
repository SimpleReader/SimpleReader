package com.simplereader.graduation.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.simplereader.graduation.base.BaseFragment;
import com.simplereader.graduation.base.BaseMvpFragment;
import com.simplereader.graduation.presenter.HomePresenter;
import com.simplereader.graduation.ui.activity.ChannelActivity;
import com.simplereader.graduation.ui.adapter.TitlePagerAdapter;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.graduation.view.IHomeView;
import com.simplereader.simplereader.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gxj on 2017/3/28.
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements IHomeView {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.icon_category)
    ImageView iconCatrgory;
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
