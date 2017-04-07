package com.simplereader.graduation.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.simplereader.graduation.ui.adapter.VideoAdapter;

/**
 * Created by gxj on 2017/4/7.
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
                /*if (JCVideoPlayerManager.getCurrentJcvd() != null) {
                    JCVideoPlayer videoPlayer = JCVideoPlayerManager.getCurrentJcvd();
                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                        //当滑动的时，正在播放的视频移除屏幕，取消播放这个视频
                        JCVideoPlayer.releaseAllVideos();
                    }
                }*/
            }
        });
    }
}
