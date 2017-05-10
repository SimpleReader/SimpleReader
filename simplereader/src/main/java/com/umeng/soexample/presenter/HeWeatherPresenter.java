package com.umeng.soexample.presenter;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BaseCallBack;
import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.model.HeWeather;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.soexample.view.IHeWeatherView;

/**
 * Description:
 * Created by chenggong on 2017/5/6.
 */

public class HeWeatherPresenter extends BasePresenter<IHeWeatherView> {

    public HeWeatherPresenter(IHeWeatherView mvpView) {
        super(mvpView);
    }

    public void loadHeWeather(String city) {

        addSubscription(AppClient.getApiService(ApiService.Host_WEATHER).loadHeWeather(city, ConstanceValue.KEY_WEATHER), new BaseCallBack<HeWeather>() {
            @Override
            public void onNext(HeWeather response) {
                Logger.e("HeWeather:"+response.toString());
                mvpView.onGetWeatherSuccess(response);
            }
        });
    }

}
