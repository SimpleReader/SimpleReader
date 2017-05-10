package com.umeng.soexample.view;

import com.umeng.soexample.model.News;

import java.util.List;

/**
 * Created by gxj on 2017/3/29.
 */

public interface INewsListView {
    void onGetNewsListSuccess(List<News> response);
}
