package com.simplereader.graduation.mvp.contract;

import com.simplereader.graduation.base.BaseModel;
import com.simplereader.graduation.base.BasePresenter;
import com.simplereader.graduation.base.BaseView;
import com.simplereader.graduation.bean.Gank;

import rx.Observable;

/**
 * Created by gxj on 2017/2/28.
 */

public interface MainContract {
    interface View extends BaseView {

        void showDialog();

        void onSucceed(Gank data);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<Gank> getGank();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getGank();
    }
}
