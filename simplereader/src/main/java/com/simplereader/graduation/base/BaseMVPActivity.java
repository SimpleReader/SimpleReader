package com.simplereader.graduation.base;

import android.os.Bundle;

/**
 * Created by gxj on 2017/4/24.
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
