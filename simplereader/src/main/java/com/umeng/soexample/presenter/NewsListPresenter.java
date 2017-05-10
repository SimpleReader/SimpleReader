package com.umeng.soexample.presenter;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.base.SubscriberCallBack;
import com.umeng.soexample.model.News;
import com.umeng.soexample.view.INewsListView;

import java.util.List;

/**
 * Created by gxj on 2017/3/29.
 */

public class NewsListPresenter extends BasePresenter<INewsListView> {
    public NewsListPresenter(INewsListView mvpView) {
        super(mvpView);
    }

    public void getNewsList(String titleCode) {
        addSubscription(AppClient.getApiService(ApiService.API_SERVICE_URL).getNews(titleCode), new SubscriberCallBack<List<News>>() {

            @Override
            protected void onSuccess(List<News> response) {
                Logger.e("response" + response.toString());
                mvpView.onGetNewsListSuccess(response);
            }
        });
    }
}
