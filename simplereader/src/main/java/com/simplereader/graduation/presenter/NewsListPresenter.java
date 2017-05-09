package com.simplereader.graduation.presenter;

import com.orhanobut.logger.Logger;
import com.simplereader.graduation.api.ApiService;
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

    public void getNewsList(String titleCode) {
        addSubscription(AppClient.getApiService(ApiService.API_SERVICE_URL).getNews(titleCode), new SubscriberCallBack<List<News>>() {

            @Override
            protected void onSuccess(List<News> response) {
                Logger.e("response" + response.toString());
                Logger.e("mvpView:"+mvpView==null?"null":"不为空"+" response:"+response==null?"是":"不为空");
                mvpView.onGetNewsListSuccess(response);
            }
        });
    }
}
