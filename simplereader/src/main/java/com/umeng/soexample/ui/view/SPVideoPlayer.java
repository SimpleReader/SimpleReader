package com.umeng.soexample.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.soexample.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Description:自定义视频播放器
 * Created by chenggong on 2017/4/11.
 */

public class SPVideoPlayer extends JCVideoPlayerStandard {
    private LinearLayout llDuration;
    private TextView tvDuration;

    public SPVideoPlayer(Context context) {
        super(context);
    }

    public SPVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        tvDuration = (TextView) findViewById(R.id.tvDuration);
        llDuration = (LinearLayout) findViewById(R.id.llDuration);
    }

    /**
     * 根据控件状态修改ui
     * @param state
     */
    @Override
    public void setUiWitStateAndScreen(int state) {
        super.setUiWitStateAndScreen(state);
        switch (currentState) {
            case CURRENT_STATE_PREPARING: //准备播放
                //隐藏时长
                llDuration.setVisibility(View.GONE);
                break;
            case CURRENT_STATE_AUTO_COMPLETE: //播放完成
            case CURRENT_STATE_ERROR: //播放错误
                //显示时长
                llDuration.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onCompletion() {
        super.onCompletion();
        //显示时长
        llDuration.setVisibility(View.VISIBLE);
    }

    /**
     * 设置播放时长
     * @param text
     */
    public void setDurationText(String text) {
        tvDuration.setText(text);
    }

    /**
     * 获取控件id
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.layout_sp_video_player;
    }
}
