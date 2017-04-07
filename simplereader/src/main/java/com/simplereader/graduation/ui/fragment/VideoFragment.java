package com.simplereader.graduation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplereader.graduation.base.BaseFragment;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.simplereader.R;

/**
 * Created by gxj on 2017/3/28.
 */

public class VideoFragment extends BasePagerFragment {

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_video, null);
    }

    @Override
    protected String[] getTitles() {
        return getResources().getStringArray(R.array.video_title);
    }

    @Override
    protected Class<? extends BaseFragment> getSubFragmentClass() {
        return VideoListFragment.class;
    }

    @Override
    protected Bundle getSubFragmentArguments(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, getResources().getStringArray(R.array.video_title_code)[i]);
        return bundle;
    }

    @Override
    protected void bindViews(View view) {
        super.bindViews(view);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }

    @Override
    protected void setListener() {
        super.setListener();
    }
}