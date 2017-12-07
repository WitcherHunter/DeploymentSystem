package com.yeejoin.deloymentsystem.activity;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yeejoin.deloymentsystem.MyApplication;
import com.yeejoin.deloymentsystem.R;
import com.yeejoin.deloymentsystem.base.BaseActivity;
import com.yeejoin.deloymentsystem.fragment.TestFragment;

import java.util.ArrayList;

import butterknife.BindView;
import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabBar)
    NavigationTabBar mTabBar;

    private String[] titles = new String[]{"首页", "指挥", "统计", "我的"};

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new TestFragment();
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(adapter);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.dh_01),
                        Color.parseColor("#df5a55"))
                .selectedIcon(getResources().getDrawable(R.drawable.dh_01_01))
                .title(titles[0])
                .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.dh_02),
                        Color.parseColor("#df5a55"))
                        .selectedIcon(getResources().getDrawable(R.drawable.dh_02_02))
                        .title(titles[1])
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.dh_03),
                        Color.parseColor("#df5a55"))
                        .selectedIcon(getResources().getDrawable(R.drawable.dh_03_03))
                        .title(titles[2])
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.dh_04),
                        Color.parseColor("#df5a55"))
                        .selectedIcon(getResources().getDrawable(R.drawable.dh_04_04))
                        .title(titles[3])
                        .build()
        );
        mTabBar.setModels(models);
        mTabBar.setViewPager(mViewPager);
    }
}
