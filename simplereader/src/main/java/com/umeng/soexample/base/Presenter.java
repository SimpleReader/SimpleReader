package com.umeng.soexample.base;

/**
 * Created by chenggong on 2017/3/28.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
