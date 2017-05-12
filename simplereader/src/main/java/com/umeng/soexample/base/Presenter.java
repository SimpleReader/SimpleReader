package com.umeng.soexample.base;

/**
 * Description:presenter
 * Created by chenggong on 2017/3/28.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
