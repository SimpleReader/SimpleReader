package com.simplereader.graduation.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by gxj on 2017/2/28.
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> {

    protected V mView;
    protected M mModel;

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }

}
