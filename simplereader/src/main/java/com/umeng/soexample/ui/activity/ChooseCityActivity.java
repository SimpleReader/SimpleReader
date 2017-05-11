package com.umeng.soexample.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.db.DBManager;
import com.umeng.soexample.db.WeatherDB;
import com.umeng.soexample.model.City;
import com.umeng.soexample.model.Province;
import com.umeng.soexample.theme.util.SharedPreferencesMgr;
import com.umeng.soexample.ui.adapter.CityAdapter;
import com.umeng.soexample.util.ConstanceValue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseCityActivity extends BaseActivity {
    @BindView(R.id.recycleView_city)
    public RecyclerView mRecyclerViewCity;
    private List<String> mList=new ArrayList<>(); //通用列表
    private List<Province> mProvinceList=new ArrayList<>(); //省份列表
    private List<City> mCityList=new ArrayList<>(); //城市列表
    private CityAdapter mCityAdapter;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_choose_city);
    }

    @Override
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        DBManager.getInstance(mContext).openDatabase();
        mProvinceList.addAll(0, WeatherDB.loadProvinces(DBManager.getInstance(mContext).getDatabase()));
        for(int i=0;i<mProvinceList.size();i++){
            mList.add(mProvinceList.get(i).proName);
        }
        createAdapter();
        mCityAdapter.notifyItemChanged(0,mList.size());
        mRecyclerViewCity.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCity.setAdapter(mCityAdapter);
    }

    @Override
    protected void setListener() {
        mCityAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                int provinceId=mProvinceList.get(i).proSort;
                mCityList.addAll(0,WeatherDB.loadCities(DBManager.getInstance(mContext).getDatabase(),provinceId));
                mList.clear();
                for(int j=0;j<mCityList.size();j++){
                    mList.add(mCityList.get(j).CityName);
                }
                createAdapter();
                mCityAdapter.notifyItemChanged(0,mList.size());
                mRecyclerViewCity.setLayoutManager(new LinearLayoutManager(ChooseCityActivity.this));
                mRecyclerViewCity.setAdapter(mCityAdapter);
                mCityAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        SharedPreferencesMgr.setString(ConstanceValue.SP_CITY,mList.get(i));
                        Logger.e("cityName2"+mList.get(i));
                        DBManager.getInstance(mContext).closeDatabase();
                        finish();
                    }
                });
            }
        });
    }

    public BaseQuickAdapter createAdapter(){
        mCityAdapter=new CityAdapter(mList);
        return mCityAdapter;
    }


}
