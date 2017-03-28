package com.simplereader.graduation.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplereader.graduation.base.BaseFragment;
import com.simplereader.simplereader.R;
import com.simplereader.simplereader.databinding.FragmentHomeBinding;

/**
 * Created by gxj on 2017/3/6.
 */

public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding mBinding;

    public HomeFragment() {
    }

    //需要的参数通过setArguments解决
    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false);
        String[] menu_array = getActivity().getResources().getStringArray(R.array.tab_menu);
        for (int i = 0; i < menu_array.length; i++) {
            mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(menu_array[i]));
        }
        mBinding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//tablayout的滑动模式
        mBinding.tabLayout.setupWithViewPager(mBinding.viewpager);
        return mBinding.getRoot();
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    protected void bindViews(View view) {

    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void setListener() {

    }
}
