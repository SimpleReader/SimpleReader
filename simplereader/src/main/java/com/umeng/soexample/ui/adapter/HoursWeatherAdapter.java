package com.umeng.soexample.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.umeng.soexample.R;
import com.umeng.soexample.model.HeWeather;

import java.util.List;

/**
 * Description:
 * Created by chenggong on 2017/5/6.
 */

public class HoursWeatherAdapter extends BaseQuickAdapter<HeWeather.HeWeather5Bean.HourlyForecastBean> {

    public HoursWeatherAdapter(List<HeWeather.HeWeather5Bean.HourlyForecastBean> data) {
        super(R.layout.item_hour_info, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HeWeather.HeWeather5Bean.HourlyForecastBean hourlyForecastBean) {
        baseViewHolder.setText(R.id.one_clock,hourlyForecastBean.getDate())
                .setText(R.id.one_temp,hourlyForecastBean.getTmp())
                .setText(R.id.one_humidity,hourlyForecastBean.getHum())
                .setText(R.id.one_swind,hourlyForecastBean.getWind().getSpd());
    }
}
