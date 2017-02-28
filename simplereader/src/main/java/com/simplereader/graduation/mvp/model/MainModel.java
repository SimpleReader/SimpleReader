package com.simplereader.graduation.mvp.model;

import com.simplereader.graduation.api.ApiEngine;
import com.simplereader.graduation.bean.Gank;
import com.simplereader.graduation.mvp.contract.MainContract;
import com.simplereader.graduation.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by gxj on 2017/2/28.
 */

public class MainModel implements MainContract.Model{

    @Override
    public Observable<Gank> getGank() {
        return ApiEngine.getInstance().getApiService()
                .getGank("1")
                .compose(RxSchedulers.<Gank>switchThread());
    }
}
