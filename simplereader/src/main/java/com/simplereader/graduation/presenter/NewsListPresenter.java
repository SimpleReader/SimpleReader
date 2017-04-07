package com.simplereader.graduation.presenter;

import com.orhanobut.logger.Logger;
import com.simplereader.graduation.api.AppClient;
import com.simplereader.graduation.base.BasePresenter;
import com.simplereader.graduation.base.SubscriberCallBack;
import com.simplereader.graduation.model.News;
import com.simplereader.graduation.view.INewsListView;

import java.util.List;

/**
 * Created by gxj on 2017/3/29.
 */

public class NewsListPresenter extends BasePresenter<INewsListView> {
    public NewsListPresenter(INewsListView mvpView) {
        super(mvpView);
    }

    public void getNesList(String titleCode) {
        addSubscription(AppClient.getApiService().getNews(titleCode), new SubscriberCallBack<List<News>>() {

            @Override
            protected void onSuccess(List<News> response) {
                Logger.e("response" + response.toString());
                mvpView.onGetNewsListSuccess(response);
            }
        });
    }
}
