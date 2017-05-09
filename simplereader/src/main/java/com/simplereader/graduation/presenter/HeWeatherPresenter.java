package com.simplereader.graduation.presenter;

import com.orhanobut.logger.Logger;
import com.simplereader.graduation.api.ApiService;
import com.simplereader.graduation.api.AppClient;
import com.simplereader.graduation.base.BaseCallBack;
import com.simplereader.graduation.base.BasePresenter;
import com.simplereader.graduation.model.HeWeather;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.graduation.view.IHeWeatherView;

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
