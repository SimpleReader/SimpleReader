package com.simplereader.graduation.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.simplereader.graduation.base.BaseFragment;

import java.util.List;

/**
 * Created by gxj on 2017/3/29.
 */

public class TitlePagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private String[] titles;

    public TitlePagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? "" : titles[position];
    }
}