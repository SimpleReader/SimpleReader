package com.umeng.soexample.base;

import android.text.TextUtils;

import com.umeng.soexample.util.ToastUtils;

/**
 * Created by gxj on 2017/3/29.
 */

public abstract class SubscriberCallBack<T> extends BaseCallBack<ResultResponse<T>> {
    @Override
    public void onNext(ResultResponse response) {
        if (TextUtils.equals(response.message, "success")) {
            onSuccess((T) response.data);
        } else {
            ToastUtils.showToast(response.message);
            onFailure(response);
        }
    }

    protected abstract void onSuccess(T response);
}
