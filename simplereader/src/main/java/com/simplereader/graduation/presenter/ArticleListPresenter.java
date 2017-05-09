package com.simplereader.graduation.presenter;

import com.orhanobut.logger.Logger;
import com.simplereader.graduation.api.ApiService;
import com.simplereader.graduation.api.AppClient;
import com.simplereader.graduation.base.BaseCallBack;
import com.simplereader.graduation.base.BasePresenter;
import com.simplereader.graduation.model.ArticleResponse;
import com.simplereader.graduation.view.IArticleView;

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
