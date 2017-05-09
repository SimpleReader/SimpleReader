package com.simplereader.graduation.base;

import com.simplereader.graduation.model.Notice;
import com.simplereader.graduation.util.RxBus;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by gxj on 2017/3/28.
 */

public class BasePresenter<V> implements Presenter<V> {
    public V mvpView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        this.mvpView = view;
    }

    public BasePresenter(V mvpView) {
        attachView(mvpView);
    }

    @Override
    public void detachView() {
        this.mvpView = null;
        //onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 发送消息
     */
    public void post(Notice msg) {
        RxBus.getDefault().post(msg);
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

}