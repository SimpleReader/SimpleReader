package com.simplereader.graduation.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplereader.graduation.base.BaseFragment;
import com.simplereader.graduation.base.BaseMvpFragment;
import com.simplereader.graduation.presenter.HomePresenter;
import com.simplereader.graduation.ui.adapter.TitlePagerAdapter;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.graduation.view.IHomeView;
import com.simplereader.simplereader.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gxj on 2017/3/28.
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements IHomeView {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private String[] titles = new String[]{};
    private String[] titlesCode = new String[]{};

    //需要的参数通过setArguments解决
    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

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
            bundle.putString(ConstanceValue.Data, titlesCode[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        viewPager.setAdapter(new TitlePagerAdapter(getChildFragmentManager(), fragments, titles));
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        viewPager.setOffscreenPageLimit(titles.length);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//tablayout的滑动模式
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void setListener() {

    }
}
/*public class HomeFragment extends BaseMvpFragment<HomePresenter> implements IHomeView {
    @BindView(R.id.feed_top_search_hint)
    TextView feedTopSearchHint;
    @BindView(R.id.tab)
    ColorTrackTabViewIndicator tab;
    @BindView(R.id.icon_category)
    ImageView iconCategory;

    @Override
    protected void processLogic() {
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            NewsListFragment fragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ConstanceValue.DATA, titlesCode[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        vp.setAdapter(new TitlePagerAdapter(getChildFragmentManager(), fragments, titles));
        tab.setTitles(titles, new ColorTrackTabViewIndicator.CorlorTrackTabBack() {
            @Override
            public void onClickButton(Integer position, ColorTrackView colorTrackView) {
                vp.setCurrentItem(position, false);
            }
        });
        final View tabChild = tab.getChildAt(0);
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        //重新测量
        tabChild.measure(w, h);
        //设置最小宽度，使其可以在滑动一部分距离
        tabChild.setMinimumWidth(tabChild.getMeasuredWidth() + tab.getTabWidth());

        vp.setOffscreenPageLimit(titles.length);
        tab.setupViewPager(vp);
    }

    static final int REQUEST_CHANNEL = 111;

    @OnClick({R.id.feed_top_search_hint, R.id.icon_category})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_category:
//                intent2Activity(ChannelActivity.class);
                Intent intent = new Intent(mContext, ChannelActivity.class);
                startActivityForResult(intent,REQUEST_CHANNEL);
                break;
        }
    }
}*/
