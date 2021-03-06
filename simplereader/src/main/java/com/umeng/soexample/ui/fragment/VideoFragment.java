package com.umeng.soexample.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.util.ConstanceValue;

/**
 * Description:视频页面
 * Created by chenggong on 2017/3/28.
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