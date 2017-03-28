package com.simplereader.graduation.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.simplereader.graduation.ui.adapter.ViewPagerAdapter;
import com.simplereader.graduation.ui.fragment.AttentionFragment;
import com.simplereader.graduation.ui.fragment.HomeFragment;
import com.simplereader.graduation.ui.fragment.MeFragment;
import com.simplereader.graduation.ui.fragment.VideoFragment;
import com.simplereader.simplereader.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_call:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_mail:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_attention:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_person:
                                viewPager.setCurrentItem(3);
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

        // 如果想禁止滑动，可以把下面的代码取消注释
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new VideoFragment());
        adapter.addFragment(new AttentionFragment());
        adapter.addFragment(new MeFragment());
        viewPager.setAdapter(adapter);
    }
}