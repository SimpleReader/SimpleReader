package com.umeng.soexample.presenter;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BaseCallBack;
import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.model.ArticleFavour;
import com.umeng.soexample.view.IArticleFavourView;

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
