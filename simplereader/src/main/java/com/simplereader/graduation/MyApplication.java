package com.simplereader.graduation;

import android.app.Application;

import com.simplereader.graduation.theme.util.util.SharedPreferencesMgr;
import com.simplereader.graduation.util.ConstanceValue;

/**
 * Created by gxj on 2017/2/28.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharedPreferencesMgr.init(this, "simpleread");
        SharedPreferencesMgr.setString(ConstanceValue.SP_CITY,ConstanceValue.CITY_DEFAULT);
    }

    public static MyApplication getContext() {
        return instance;
    }
}