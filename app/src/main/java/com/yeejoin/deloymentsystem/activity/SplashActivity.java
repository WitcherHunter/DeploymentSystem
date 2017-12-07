package com.yeejoin.deloymentsystem.activity;

import android.content.Intent;

import com.yeejoin.deloymentsystem.R;
import com.yeejoin.deloymentsystem.base.BaseActivity;

/**
 * Created by maodou on 2017/12/7.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected int getResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        System.out.println("SplashActivity: " + isTaskRoot());
        try {
            Thread.sleep(2000);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
