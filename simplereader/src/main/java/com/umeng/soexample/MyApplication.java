package com.umeng.soexample;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.soexample.theme.util.util.SharedPreferencesMgr;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by gxj on 2017/2/28.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Config.DEBUG=false;
        UMShareAPI.get(this);
        SharedPreferencesMgr.init(this, "simpleread");
        SharedPreferencesMgr.setString(ConstanceValue.SP_CITY,ConstanceValue.CITY_DEFAULT);
    }

    public static MyApplication getContext() {
        return instance;
    }

    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106155084", "q2ZqrRXCFuaqWogN");
        PlatformConfig.setSinaWeibo("3192576176", "a2411843e4126ace8f5b7efa23e7ef22","http://sns.whalecloud.com");
    }
}