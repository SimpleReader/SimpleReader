package com.simplereader.graduation.base;

/**
 * Created by gxj on 2017/3/28.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
