package com.yeejoin.deloymentsystem.data.local;

import android.arch.lifecycle.LiveData;

import com.yeejoin.deloymentsystem.AppExecutors;
import com.yeejoin.deloymentsystem.MyApplication;
import com.yeejoin.deloymentsystem.data.DataSource;
import com.yeejoin.deloymentsystem.data.local.db.AppDatabase;
import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;

import java.util.List;

/**
 * Created by maodou on 2017/12/5.
 * 本地数据源
 */

public class LocalDataSource extends DataSource {
    private static LocalDataSource instance = null;
    private AppDatabase database = null;
    private AppExecutors executors = null;

    private LocalDataSource() {
        database = MyApplication.getInstance().getDatabase();
        executors = MyApplication.getInstance().getAppExecutors();
    }

    public static LocalDataSource getInstance() {
        if (instance == null) {
            synchronized (LocalDataSource.class) {
                if (instance == null)
                    instance = new LocalDataSource();
            }
        }
        return instance;
    }

    @Override
    public LiveData<List<NetConfig>> getNetConfigs() {
        return database.netConfigDao().loadNetConfig();
    }

    @Override
    public LiveData<Login> getLastLogin() {
        return database.loginDao().loadLogin();
    }
}
