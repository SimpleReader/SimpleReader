package com.umeng.soexample.presenter;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BaseCallBack;
import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.model.ArticleResponse;
import com.umeng.soexample.view.IArticleView;

/**
 * Description:
 * Created by chenggong on 2017/5/4.
 */

public class ArticleListPresenter extends BasePresenter<IArticleView> {

    public ArticleListPresenter(IArticleView mvpView) {
        super(mvpView);
    }

    public void getArticleList(){
        addSubscription(AppClient.getApiService(ApiService.HOST_MINE).getArticleData(ApiService.URL_ARTICLE), new BaseCallBack<ArticleResponse>() {

            @Override
            public void onNext(ArticleResponse response) {
                Logger.e("response:"+response.getData().toString());
                mvpView.onGetArticleListSuccess(response);
            }
        });
    }
}
