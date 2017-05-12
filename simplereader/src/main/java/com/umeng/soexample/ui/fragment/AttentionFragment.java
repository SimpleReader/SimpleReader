package com.umeng.soexample.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseMvpFragment;
import com.umeng.soexample.model.HeWeather;
import com.umeng.soexample.presenter.HeWeatherPresenter;
import com.umeng.soexample.theme.util.util.SharedPreferencesMgr;
import com.umeng.soexample.ui.activity.ChooseCityActivity;
import com.umeng.soexample.ui.adapter.ForcastAdapter;
import com.umeng.soexample.ui.adapter.HoursWeatherAdapter;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.soexample.view.IHeWeatherView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Descrtiption: 天气页面
 * Created by chenggong on 2017/3/28.
 */

public class AttentionFragment extends BaseMvpFragment<HeWeatherPresenter> implements IHeWeatherView {
    public RecyclerView mRecyclerViewForcast; // 未来天气预报列表
    public RecyclerView mRecyclerViewHours; //实时预报
    public TextView mIndexClothesBrf; //穿衣指数
    public TextView mIndexClothesTxt; //穿衣建议
    public TextView mIndexSportsBrf; //运动指数
    public TextView mIndexSportsTxt; //运动建议
    public TextView mIndexTravelBrf; //旅游指数
    public TextView mIndexTravelTxt; //旅游建议
    public TextView mIndexFluBrf; //感冒指数
    public TextView mIndexFluTxt; //感冒建议

    public ImageView mWeatherIcon; //当前天气图标
    public TextView mTemperatureNow; //当前温度
    public TextView mTemperatureMax; //最高温度
    public TextView mTemperatureMin; //最低温度
    public TextView mTempPm25; //PM2.5
    public TextView mTempQuality; //空气质量

    public TextView mCityName;

    private ForcastAdapter mForcastAdapter; //未来天气预报适配器
    private HoursWeatherAdapter mHoursAdapter; //实时天气预报适配器
    private List<HeWeather.HeWeather5Bean.DailyForecastBean> mForcastList = new ArrayList<>();
    private List<HeWeather.HeWeather5Bean.HourlyForecastBean> mHoursList = new ArrayList<>();
    private HeWeather.HeWeather5Bean.SuggestionBean mSuggestion; //推荐指数
    private HeWeather.HeWeather5Bean.NowBean mTemperatureBean; //温度
    private HeWeather.HeWeather5Bean.AqiBean mAqiBean; //AQI

    private RelativeLayout mCity; //选择城市
    private String cityName; //城市名称
    private boolean isLoaded = false;

    @Override
    protected HeWeatherPresenter createPresenter() {
        return new HeWeatherPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_weather, null);
    }

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(view);
        //温度
        mWeatherIcon = get(R.id.weather_icon);
        mTemperatureNow = get(R.id.temp_flu);
        mTemperatureMax = get(R.id.temp_max);
        mTemperatureMin = get(R.id.temp_min);
        mTempPm25 = get(R.id.temp_pm);
        mTempQuality = get(R.id.temp_quality);
        //未来天气预报
        mRecyclerViewForcast = get(R.id.recyclerview_weather);
        //实时预报
        mRecyclerViewHours = get(R.id.recycleView_hours);
        //指数
        mIndexClothesBrf = get(R.id.cloth_brief);
        mIndexClothesTxt = get(R.id.cloth_txt);
        mIndexSportsBrf = get(R.id.sport_brief);
        mIndexSportsTxt = get(R.id.sport_txt);
        mIndexTravelBrf = get(R.id.travel_brief);
        mIndexTravelTxt = get(R.id.travel_txt);
        mIndexFluBrf = get(R.id.flu_brief);
        mIndexFluTxt = get(R.id.flu_txt);

        mCity = get(R.id.city);
        mCityName = get(R.id.cityName);
    }

    @Override
    protected void processLogic() {
    }

    @Override
    protected void setListener() {
        mCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChooseCityActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetWeatherSuccess(HeWeather response) {
        Logger.e("onGetWeatherSuccess");
        //获取城市
        mCityName.setText(response.getHeWeather5().get(0).getBasic().getCity());
        //获取推荐实体
        mSuggestion = response.getHeWeather5().get(0).getSuggestion();
        //获取温度实体
        mTemperatureBean = response.getHeWeather5().get(0).getNow();
        //获取AQI
        mAqiBean = response.getHeWeather5().get(0).getAqi();
        //显示推荐指数
        mIndexClothesBrf.setText(String.format("穿衣指数---%s", mSuggestion.getDrsg().getBrf()));
        mIndexClothesTxt.setText(mSuggestion.getDrsg().getTxt());
        mIndexSportsBrf.setText(String.format("运动指数---%s", mSuggestion.getSport().getBrf()));
        mIndexSportsTxt.setText(mSuggestion.getSport().getTxt());
        mIndexTravelBrf.setText(String.format("旅游指数---%s", mSuggestion.getTrav().getBrf()));
        mIndexTravelTxt.setText(mSuggestion.getTrav().getTxt());
        mIndexFluBrf.setText(String.format("感冒指数---%s", mSuggestion.getFlu().getBrf()));
        mIndexFluTxt.setText(mSuggestion.getFlu().getTxt());
        //未来天气预报数据
        mForcastList.clear();
        mForcastList.addAll(0, response.getHeWeather5().get(0).getDaily_forecast());
        //实时天气预报数据
        mHoursList.clear();
        mHoursList.addAll(0, response.getHeWeather5().get(0).getHourly_forecast());
        //温度
        mTemperatureNow.setText(String.format("%s℃", mTemperatureBean.getTmp()));
        mTemperatureMax.setText(String.format("↑ %s ℃", mForcastList.get(0).getTmp().getMax()));
        mTemperatureMin.setText(String.format("↓ %s ℃", mForcastList.get(0).getTmp().getMin()));
        mTempPm25.setText(String.format("PM2.5: %s μg/m³", mAqiBean.getCity().getPm25()));
        mTempQuality.setText("空气质量： " + mAqiBean.getCity().getQlty());
        mRecyclerViewForcast.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewHours.setLayoutManager(new LinearLayoutManager(getContext()));
        //实例化适配器
        mForcastAdapter = new ForcastAdapter(mForcastList);
        mHoursAdapter = new HoursWeatherAdapter(mHoursList);
        //给RecyclerView设置适配器
        mRecyclerViewForcast.setAdapter(mForcastAdapter);
        mRecyclerViewHours.setAdapter(mHoursAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e("isVisibleToUser---loadHeWeather:" + isVisibleToUser);
        if (isVisibleToUser) {
            cityName = SharedPreferencesMgr.getString(ConstanceValue.SP_CITY, ConstanceValue.CITY_DEFAULT);
            Logger.e("cityName1:" + cityName);
            isLoaded=true;
            mvpPresenter.loadHeWeather(cityName);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e("weather---onResume");
        if(!isLoaded){
            cityName = SharedPreferencesMgr.getString(ConstanceValue.SP_CITY, ConstanceValue.CITY_DEFAULT);
            mvpPresenter.loadHeWeather(cityName);
        }
    }

}
