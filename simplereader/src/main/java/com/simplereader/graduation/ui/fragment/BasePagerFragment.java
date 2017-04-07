package com.simplereader.graduation.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplereader.graduation.base.BaseFragment;
import com.simplereader.graduation.ui.adapter.TitlePagerAdapter;
import com.simplereader.graduation.ui.view.ColorTrackTabViewIndicator;
import com.simplereader.graduation.ui.view.colortrackview.ColorTrackView;
import com.simplereader.simplereader.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gxj on 2017/4/7.
 */
public abstract class BasePagerFragment extends BaseFragment {
    @BindView(R.id.tab)
    ColorTrackTabViewIndicator tab;
    @BindView(R.id.vp)
    ViewPager vp;

    protected abstract String[] getTitles();

    protected abstract Class<? extends BaseFragment> getSubFragmentClass();

    protected abstract Bundle getSubFragmentArguments(int i);

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void processLogic() {
        try {
            List<BaseFragment> fragments = new ArrayList<>();
            for (int i = 0; i < getTitles().length; i++) {
                BaseFragment fragment = getSubFragmentClass().newInstance();
                fragment.setArguments(getSubFragmentArguments(i));
                fragments.add(fragment);
            }
            vp.setAdapter(new TitlePagerAdapter(getChildFragmentManager(), fragments, getTitles()));
            tab.setTitles(getTitles(), new ColorTrackTabViewIndicator.CorlorTrackTabBack() {
                @Override
                public void onClickButton(Integer position, ColorTrackView colorTrackView) {
                    vp.setCurrentItem(position, false);
                }
            });
            vp.setOffscreenPageLimit(getTitles().length);
            tab.setupViewPager(vp);
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setListener() {

    }
}