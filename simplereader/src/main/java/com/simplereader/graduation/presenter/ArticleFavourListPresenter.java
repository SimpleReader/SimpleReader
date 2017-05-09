package com.simplereader.graduation.presenter;

import com.orhanobut.logger.Logger;
import com.simplereader.graduation.api.ApiService;
import com.simplereader.graduation.api.AppClient;
import com.simplereader.graduation.base.BaseCallBack;
import com.simplereader.graduation.base.BasePresenter;
import com.simplereader.graduation.model.ArticleFavour;
import com.simplereader.graduation.view.IArticleFavourView;

/**
 * Description:
 * Created by chenggong on 2017/5/5.
 */

public class ArticleFavourListPresenter extends BasePresenter<IArticleFavourView> {
    public ArticleFavourListPresenter(IArticleFavourView mvpView) {
        super(mvpView);
    }

    public void getArticleFavourList(String username){
        addSubscription(AppClient.getApiService(ApiService.HOST_MINE).getArticleFavourList(username), new BaseCallBack<ArticleFavour>() {

            @Override
            public void onNext(ArticleFavour response) {
                Logger.e("response:"+response.getData().toString());
                mvpView.onGetArticleFavoursSuccess(response);
            }
        });
    }
}
