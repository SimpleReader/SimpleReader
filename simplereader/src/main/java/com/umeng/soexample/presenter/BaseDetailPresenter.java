package com.umeng.soexample.presenter;

import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.base.SubscriberCallBack;
import com.umeng.soexample.model.CommentList;
import com.umeng.soexample.view.IBaseDetailView;

/**
 * Created by chenggong on 2017/4/24.
 */

public class BaseDetailPresenter extends BasePresenter<IBaseDetailView> {
    public BaseDetailPresenter(IBaseDetailView mvpView) {
        super(mvpView);
    }

    public void getComment(String group_id, String item_id, int pageNow) {
        int offset = (pageNow - 1) * 10;
        addSubscription(AppClient.getApiService(ApiService.API_SERVICE_URL).getComment(group_id, item_id, offset + "", "10"), new SubscriberCallBack<CommentList>() {
                    @Override
                    protected void onSuccess(CommentList response) {
                        mvpView.onGetCommentSuccess(response);
                    }
                }
        );
    }
}
