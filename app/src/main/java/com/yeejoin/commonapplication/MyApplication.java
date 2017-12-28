package com.yeejoin.commonapplication;

import android.app.Application;

import com.yeejoin.commonapplication.data.Injection;
import com.yeejoin.commonapplication.data.local.db.AppDatabase;
import com.yeejoin.commonapplication.data.model.entity.Login;
import com.yeejoin.commonapplication.data.model.entity.NetConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maodou on 2017/12/28.
 */

public class MyApplication extends Application {
    private static MyApplication instance = null;
    private AppExecutors appExecutors;
    private Login loginInfo = null;

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appExecutors = new AppExecutors();
        loginInfo = Injection.getDataRepository(this)
                .getLastLogin().getValue();
    }

    public Login getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(Login loginInfo) {
        this.loginInfo = loginInfo;
    }

    public AppExecutors getAppExecutors() {
        return appExecutors;
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this);
    }
}
