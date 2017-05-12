package com.umeng.soexample.listener;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by chenggong on 2017/4/17.
 */

public interface OnChannelDragListener {
    void onStarDrag(BaseViewHolder baseViewHolder);
    void onItemMove(int starPos, int endPos);
}
