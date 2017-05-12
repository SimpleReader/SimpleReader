package com.umeng.soexample.view;

import com.umeng.soexample.model.CommentList;

/**
 * Description:详情基础接口
 * Created by chenggong on 2017/4/24.
 */

public interface IBaseDetailView {
    void onGetCommentSuccess(CommentList response);
}
