package com.umeng.soexample.base;

import android.os.Bundle;

/**
 * Description:MVP活动基类
 * Created by chenggong on 2017/4/24.
 */

public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }*/
    }

}
