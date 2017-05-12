package com.umeng.soexample.view;

import com.umeng.soexample.model.CommentList;

/**
 * 详情view基础接口
 * Created by chenggong on 2017/4/24.
 */

public interface IBaseDetailView {
    void onGetCommentSuccess(CommentList response);
}
