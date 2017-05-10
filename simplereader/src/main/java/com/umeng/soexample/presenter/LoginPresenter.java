package com.umeng.soexample.presenter;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BaseCallBack;
import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.model.ArticleResponse;
import com.umeng.soexample.view.ILoginView;

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
