package com.umeng.soexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.theme.util.SharedPreferencesMgr;
import com.umeng.soexample.ui.adapter.ViewPagerAdapter;
import com.umeng.soexample.ui.fragment.ArticleFragment;
import com.umeng.soexample.ui.fragment.AttentionFragment;
import com.umeng.soexample.ui.fragment.HomeFragment;
import com.umeng.soexample.ui.fragment.MeFragment;
import com.umeng.soexample.ui.fragment.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    MenuItem prevMenuItem;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initIcon();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_news:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_vedio:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_article:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_weather:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.item_person:
                                viewPager.setCurrentItem(4);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

         //如果想禁止滑动，可以把下面的代码取消注释
       /* viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });*/

        setupViewPager(viewPager);
    }

    @Override
    protected void setListener() {

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment()); //新闻页面
        adapter.addFragment(new VideoFragment()); //视频页面
        adapter.addFragment(new ArticleFragment()); //文章页面
        adapter.addFragment(new AttentionFragment()); //天气页面
        adapter.addFragment(new MeFragment()); //个人页面
        viewPager.setAdapter(adapter);
    }

    /**
     * 初始化图标信息
     */
    private void initIcon(){
        SharedPreferencesMgr.setInt("未知", R.mipmap.none);
        SharedPreferencesMgr.setInt("晴", R.mipmap.type_one_sunny);
        SharedPreferencesMgr.setInt("阴", R.mipmap.type_one_cloudy);
        SharedPreferencesMgr.setInt("多云", R.mipmap.type_one_cloudy);
        SharedPreferencesMgr.setInt("少云", R.mipmap.type_one_cloudy);
        SharedPreferencesMgr.setInt("晴间多云", R.mipmap.type_one_cloudytosunny);
        SharedPreferencesMgr.setInt("小雨", R.mipmap.type_one_light_rain);
        SharedPreferencesMgr.setInt("中雨", R.mipmap.type_one_light_rain);
        SharedPreferencesMgr.setInt("大雨", R.mipmap.type_one_heavy_rain);
        SharedPreferencesMgr.setInt("阵雨", R.mipmap.type_one_thunderstorm);
        SharedPreferencesMgr.setInt("雷阵雨", R.mipmap.type_one_thunder_rain);
        SharedPreferencesMgr.setInt("霾", R.mipmap.type_one_fog);
        SharedPreferencesMgr.setInt("雾", R.mipmap.type_one_fog);
    }

}