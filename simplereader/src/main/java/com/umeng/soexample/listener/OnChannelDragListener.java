package com.umeng.soexample.listener;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Description:频道拖拽监听接口
 * Created by chenggong on 2017/4/17.
 */

public interface OnChannelDragListener {
    void onStarDrag(BaseViewHolder baseViewHolder);
    void onItemMove(int starPos, int endPos);
}
