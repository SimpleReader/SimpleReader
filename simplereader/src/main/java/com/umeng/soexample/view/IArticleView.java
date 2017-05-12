package com.umeng.soexample.view;

import com.umeng.soexample.model.ArticleResponse;

/**
 * Description:获取文章列表接口
 * Created by chenggong on 2017/5/4.
 */

public interface IArticleView {
    void onGetArticleListSuccess(ArticleResponse response);
}
