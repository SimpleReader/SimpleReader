package com.simplereader.graduation.view;

import com.simplereader.graduation.model.News;

import java.util.List;

/**
 * Created by gxj on 2017/3/29.
 */

public interface INewsListView {
    void onGetNewsListSuccess(List<News> response);
}
