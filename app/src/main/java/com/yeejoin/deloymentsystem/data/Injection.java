package com.yeejoin.deloymentsystem.data;

import android.app.Application;

import com.yeejoin.deloymentsystem.data.local.LocalDataSource;
import com.yeejoin.deloymentsystem.data.remote.RemoteDataSource;

/**
 * Created by maodou on 2017/12/28.
 */

public class Injection {

    public static DataRepository getDataRepository(Application application){
        return DataRepository.getInstance(RemoteDataSource.getInstance(),
                LocalDataSource.getInstance(),application);
    }
}
