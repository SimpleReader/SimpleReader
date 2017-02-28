package com.simplereader.graduation;

import android.app.Application;

/**
 * Created by gxj on 2017/2/28.
 */

public class MyApplication extends Application {
    private static MyApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static MyApplication getContext() {
        return mContext;
    }
}
