package com.simplereader.graduation.presenter;

import com.orhanobut.logger.Logger;
import com.simplereader.graduation.api.AppClient;
import com.simplereader.graduation.base.BasePresenter;
import com.simplereader.graduation.base.SubscriberCallBack;
import com.simplereader.graduation.model.Article;
import com.simplereader.graduation.view.IArticleView;

import java.util.List;

/**
 * Description:
 * Created by chenggong on 2017/5/4.
 */

public class ArticleListPresenter extends BasePresenter<IArticleView> {

    public ArticleListPresenter(IArticleView mvpView) {
        super(mvpView);
    }

    public void getArticleList(){
        addSubscription(AppClient.getApiService().getArticleData(), new SubscriberCallBack<List<Article>>() {
            @Override
            protected void onSuccess(List<Article> response) {
                Logger.e("文章id"+response.get(0).articleId);
                mvpView.onGetArticleListSuccess(response);
            }
        });
    }
}
