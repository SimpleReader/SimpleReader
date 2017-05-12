package com.umeng.soexample.view;

import com.umeng.soexample.model.HeWeather;

/**
 * Description:获取天气情况接口
 * Created by chenggong on 2017/5/6.
 */

public interface IHeWeatherView {
    void onGetWeatherSuccess(HeWeather response);
}
