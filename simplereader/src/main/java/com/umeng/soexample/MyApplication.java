package com.umeng.soexample;

import android.app.Application;

import com.myhexin.mylibrary.SpeechSDK;
import com.myhexin.mylibrary.middleware.SessionProxy;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.soexample.theme.util.SharedPreferencesMgr;
import com.umeng.soexample.util.ConstanceValue;

/**
 * Description:项目Application 初始化首先执行
 * Created by chenggong on 2017/2/28.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //配置友盟分享调试
        Config.DEBUG = false;
        UMShareAPI.get(this);
        //初始化sp管理器
        SharedPreferencesMgr.init(this, "simpleread");
        //设置默认显示济南天气
        SharedPreferencesMgr.setString(ConstanceValue.SP_CITY, ConstanceValue.CITY_DEFAULT);
        //打开语音合成的log打印
        SessionProxy.turnOnLog();
        //初始化语音sdk
        SpeechSDK.initSynthesis(getApplicationContext(), "cd83e5ade1bd4b359c20170415131044", "C9EC4C7FE47AAFFB2C3797924213583B");
    }

    public static MyApplication getContext() {
        return instance;
    }

    //友盟第三方分享配置帐号
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106155084", "q2ZqrRXCFuaqWogN");
        PlatformConfig.setSinaWeibo("3192576176", "a2411843e4126ace8f5b7efa23e7ef22", "http://sns.whalecloud.com");
    }
}