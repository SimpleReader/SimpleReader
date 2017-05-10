package com.umeng.soexample.presenter;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.view.IHomeView;

/**
 * Created by gxj on 2017/3/29.
 */

public class HomePresenter extends BasePresenter<IHomeView> {
    public HomePresenter(IHomeView mvpView) {
        super(mvpView);
    }
}
