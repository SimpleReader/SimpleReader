package com.umeng.soexample.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.umeng.soexample.R;
import com.umeng.soexample.model.HeWeather;
import com.umeng.soexample.theme.util.SharedPreferencesMgr;
import com.umeng.soexample.util.DateUtils;
import com.umeng.soexample.util.ImageLoadUtil;

import java.util.List;

/**
 * Description:未来天气预测适配器
 * Created by chenggong on 2017/5/6.
 */

public class ForcastAdapter extends BaseQuickAdapter<HeWeather.HeWeather5Bean.DailyForecastBean> {

    public ForcastAdapter(List<HeWeather.HeWeather5Bean.DailyForecastBean> data) {
        super(R.layout.item_forecast, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HeWeather.HeWeather5Bean.DailyForecastBean dailyForecastBean) {
        //显示图片
        ImageLoadUtil.loadImage(mContext, SharedPreferencesMgr.getInt(dailyForecastBean.getCond().getTxt_d(),R.mipmap.none), (ImageView) baseViewHolder.getView(R.id.forecast_icon));
        //判断日期
        int dateStatus=DateUtils.calculateDayStatus(DateUtils.getOnlyDate(dailyForecastBean.getDate()));
        String dateShow="";
        switch (dateStatus){
            case 0:
                dateShow="今天";
                break;
            case 1:
                dateShow="明天";
                break;
            case 2:
                dateShow="后天";
                break;
            default:
                break;
        }
        //设置显示文字
        baseViewHolder.setText(R.id.forecast_date,dateShow)
                .setText(R.id.forecast_temp,String.format("%s℃ - %s℃",
                        dailyForecastBean.getTmp().getMax(),
                        dailyForecastBean.getTmp().getMin()))
                .setText(R.id.forecast_txt,String.format("%s。 %s %s %s km/h。 降水几率 %s%%。",
                        dailyForecastBean.getCond().getTxt_d(),
                        dailyForecastBean.getWind().getSc(),
                        dailyForecastBean.getWind().getDir(),
                        dailyForecastBean.getWind().getSpd(),
                        dailyForecastBean.getPop()));
    }
}
