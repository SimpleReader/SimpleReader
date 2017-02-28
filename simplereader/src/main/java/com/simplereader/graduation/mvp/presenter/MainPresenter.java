package com.simplereader.graduation.mvp.presenter;

import com.simplereader.graduation.bean.Gank;
import com.simplereader.graduation.mvp.contract.MainContract;
import com.simplereader.graduation.mvp.model.MainModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by gxj on 2017/2/28.
 */

public class MainPresenter extends MainContract.Presenter {
    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void getGank() {

        Subscription subscribe = mModel.getGank()
                .subscribe(new Subscriber<Gank>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onFail(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(Gank gank) {
                        mView.onSucceed(gank);
                    }
                });

        addSubscribe(subscribe);
    }
}
