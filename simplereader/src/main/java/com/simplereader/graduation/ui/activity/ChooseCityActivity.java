package com.simplereader.graduation.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.simplereader.graduation.base.BaseActivity;
import com.simplereader.graduation.db.DBManager;
import com.simplereader.graduation.db.WeatherDB;
import com.simplereader.graduation.model.City;
import com.simplereader.graduation.model.Province;
import com.simplereader.graduation.theme.util.util.SharedPreferencesMgr;
import com.simplereader.graduation.ui.adapter.CityAdapter;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.simplereader.R;

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
