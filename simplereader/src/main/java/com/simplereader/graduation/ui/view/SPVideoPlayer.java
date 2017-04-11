package com.simplereader.graduation.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simplereader.simplereader.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by gxj on 2017/4/11.
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

    @Override
    public void setUiWitStateAndScreen(int state) {
        super.setUiWitStateAndScreen(state);
        switch (currentState) {
            case CURRENT_STATE_PREPARING:
                //隐藏时长
                llDuration.setVisibility(View.GONE);
                break;
            case CURRENT_STATE_AUTO_COMPLETE:
            case CURRENT_STATE_ERROR:
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

    public void setDurationText(String text) {
        tvDuration.setText(text);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_sp_video_player;
    }
}
