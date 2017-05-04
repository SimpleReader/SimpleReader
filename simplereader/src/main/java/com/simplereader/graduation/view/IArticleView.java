package com.simplereader.graduation.view;

import com.simplereader.graduation.model.Article;

import java.util.List;

/**
 * Description:
 * Created by chenggong on 2017/5/4.
 */

public interface IArticleView {
    void onGetArticleListSuccess(List<Article> response);
}
