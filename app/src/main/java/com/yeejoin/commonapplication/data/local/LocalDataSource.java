package com.yeejoin.commonapplication.data.local;

import android.arch.lifecycle.LiveData;

import com.yeejoin.commonapplication.AppExecutors;
import com.yeejoin.commonapplication.MyApplication;
import com.yeejoin.commonapplication.data.DataSource;
import com.yeejoin.commonapplication.data.local.db.AppDatabase;
import com.yeejoin.commonapplication.data.model.entity.Login;
import com.yeejoin.commonapplication.data.model.entity.NetConfig;

import java.util.List;

/**
 * Created by maodou on 2017/12/28.
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
