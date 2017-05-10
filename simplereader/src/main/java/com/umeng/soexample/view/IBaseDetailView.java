package com.umeng.soexample.view;

import com.umeng.soexample.model.CommentList;

/**
 * Created by gxj on 2017/4/24.
 */

public interface IBaseDetailView {
    void onGetCommentSuccess(CommentList response);
}
