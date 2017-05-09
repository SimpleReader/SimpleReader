package com.simplereader.graduation.presenter;

import com.orhanobut.logger.Logger;
import com.simplereader.graduation.api.ApiService;
import com.simplereader.graduation.api.AppClient;
import com.simplereader.graduation.base.BaseCallBack;
import com.simplereader.graduation.base.BasePresenter;
import com.simplereader.graduation.model.ArticleResponse;
import com.simplereader.graduation.view.ILoginView;

/**
 * Description:
 * Created by chenggong on 2017/5/5.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    public LoginPresenter(ILoginView mvpView) {
        super(mvpView);
    }

    public void loginByUser(String username,String password){
        addSubscription(AppClient.getApiService(ApiService.HOST_MINE).loginByUsername(username, password), new BaseCallBack<ArticleResponse>() {

            @Override
            public void onNext(ArticleResponse response) {
                Logger.e("response:"+response.getData().toString());
                mvpView.onLoginSuccess(response);
            }
        });
    }
}
