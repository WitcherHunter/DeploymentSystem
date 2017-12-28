package com.yeejoin.deloymentsystem.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;

import com.yeejoin.deloymentsystem.R;
import com.yeejoin.deloymentsystem.base.BaseActivity;
import com.yeejoin.deloymentsystem.utils.Util;
import com.yeejoin.deloymentsystem.viewmodel.BaseViewModel;


/**
 * Created by maodou on 2017/12/28.
 */

public class SplashActivity extends BaseActivity {
    private BaseViewModel baseViewModel = null;

    @Override
    protected int getResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        new Handler().postDelayed(() -> {
            if (!Util.isNetworkConnected(this)){
                startActivity(new Intent(this,LoginActivity.class));
                finish();
            } else {
                getLogin();
            }
        }, 3000);
    }

    private void getLogin(){
        baseViewModel.getLastLogin()
                .observe(this, login -> {
                    if (login != null && login.token != null) {
                        validToken(login.token);
                    } else {
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    }
                });
    }

    private void validToken(String token){
        baseViewModel.tokenValid(token)
                .observe(this, valid -> {
                    if (!valid){
                        showToast("用户登录已过期");
                        startActivity(new Intent(this, LoginActivity.class));
                    } else {
                        startActivity(new Intent(this, MainActivity.class));
                    }
                    finish();
                });
    }
}
