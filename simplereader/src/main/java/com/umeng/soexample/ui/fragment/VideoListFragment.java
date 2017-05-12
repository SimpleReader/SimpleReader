package com.umeng.soexample.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.umeng.soexample.ui.adapter.VideoAdapter;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;

/**
 * Description:视频列表页面
 * Created by chenggong on 2017/4/7.
 */

public class VideoListFragment extends NewsListFragment {
    @Override
    protected BaseQuickAdapter createAdapter() {
        return mAdapter = new VideoAdapter(mDatas);
    }

    @Override
    protected void setListener() {
        super.setListener();
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                JCVideoPlayer jcVideoPlayer = JCVideoPlayerManager.getCurrentJcvd();
                if (((ViewGroup) view).indexOfChild(jcVideoPlayer) != -1 && jcVideoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                    //当滑动的时，正在播放的视频移除屏幕，取消播放这个视频
                    JCVideoPlayer.releaseAllVideos();
                }
            }
        });
    }
}
