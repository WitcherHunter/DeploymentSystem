package com.yeejoin.commonapplication.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.yeejoin.commonapplication.R;
import com.yeejoin.commonapplication.base.BaseActivity;
import com.yeejoin.commonapplication.fragment.PersonalFragment;
import com.yeejoin.commonapplication.fragment.TestFragment;

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
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                        return new TestFragment();
                    case 3:
                        return new PersonalFragment();
                    default:
                        throw new IllegalStateException("Illegal position: " + position);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
