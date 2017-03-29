package com.simplereader.graduation.base;

import android.os.Handler;
import android.os.Looper;

import com.simplereader.graduation.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by gxj on 2017/3/29.
 */

public abstract class BaseCallBack<T> extends Subscriber<T> {
    private Handler mDelivery;

    public BaseCallBack() {
        mDelivery = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onCompleted() {
        mDelivery = null;
    }

    @Override
    public void onError(final Throwable e) {
        e.printStackTrace();
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (e instanceof SocketTimeoutException) {
                    ToastUtils.showToast("网络连接超时");
                } else if (e instanceof SocketException) {
                    if (e instanceof ConnectException) {
                        ToastUtils.showToast("网络未连接");
                    } else {
                        ToastUtils.showToast("网络错误");
                    }
                }
                onError();
            }
        });
    }

    protected void onError() {
    }

    protected void onFailure(ResultResponse response) {
    }
}
