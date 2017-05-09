package com.simplereader.graduation.view;

import com.simplereader.graduation.model.ArticleResponse;

/**
 * Description:
 * Created by chenggong on 2017/5/4.
 */

public interface IArticleView {
    void onGetArticleListSuccess(ArticleResponse response);
}
