package com.simplereader.graduation.presenter;

import com.simplereader.graduation.api.AppClient;
import com.simplereader.graduation.base.BasePresenter;
import com.simplereader.graduation.base.SubscriberCallBack;
import com.simplereader.graduation.model.CommentList;
import com.simplereader.graduation.view.IBaseDetailView;

/**
 * Created by gxj on 2017/4/24.
 */

public class BaseDetailPresenter extends BasePresenter<IBaseDetailView> {
    public BaseDetailPresenter(IBaseDetailView mvpView) {
        super(mvpView);
    }

    public void getComment(String group_id, String item_id, int pageNow) {
        int offset = (pageNow - 1) * 10;
        addSubscription(AppClient.getApiService().getComment(group_id, item_id, offset + "", "10"), new SubscriberCallBack<CommentList>() {
                    @Override
                    protected void onSuccess(CommentList response) {
                        mvpView.onGetCommentSuccess(response);
                    }
                }
        );
    }
}
