package com.umeng.soexample.presenter;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.view.IHomeView;

/**
 * Description:主页presenter
 * Created by chenggong on 2017/3/29.
 */

public class HomePresenter extends BasePresenter<IHomeView> {
    public HomePresenter(IHomeView mvpView) {
        super(mvpView);
    }
}
