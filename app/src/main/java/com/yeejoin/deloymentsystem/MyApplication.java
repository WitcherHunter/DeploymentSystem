package com.yeejoin.deloymentsystem;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.yeejoin.deloymentsystem.data.DataRepository;
import com.yeejoin.deloymentsystem.data.Injection;
import com.yeejoin.deloymentsystem.data.local.db.AppDatabase;
import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;

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

    public static List<NetConfig> initNetConfigs(){
        List<NetConfig> configs = new ArrayList<>();
        configs.add(new NetConfig());

        return configs;
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
