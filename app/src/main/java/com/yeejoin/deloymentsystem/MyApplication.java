package com.yeejoin.deloymentsystem;

import android.app.Application;

import com.yeejoin.deloymentsystem.data.local.db.AppDatabase;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maodou on 2017/12/5.
 */

public class MyApplication extends Application {
    private static MyApplication instance = null;
    private AppExecutors appExecutors;

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appExecutors = new AppExecutors();
    }

    public static List<NetConfig> generateNetConfigs(){
        List<NetConfig> configs = new ArrayList<>();
        configs.add(new NetConfig());

        return configs;
    }

    public AppExecutors getAppExecutors() {
        return appExecutors;
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this);
    }
}
