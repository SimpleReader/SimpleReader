package com.umeng.soexample.view;

import com.umeng.soexample.model.News;

import java.util.List;

/**
 * Description:获取新闻列表接口
 * Created by chenggong on 2017/3/29.
 */

public interface INewsListView {
    void onGetNewsListSuccess(List<News> response);
}
