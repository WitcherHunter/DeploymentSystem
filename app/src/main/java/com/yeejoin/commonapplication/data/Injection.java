package com.yeejoin.commonapplication.data;

import android.app.Application;

import com.yeejoin.commonapplication.data.local.LocalDataSource;
import com.yeejoin.commonapplication.data.remote.RemoteDataSource;

/**
 * Created by maodou on 2017/12/28.
 */

public class Injection {

    public static DataRepository getDataRepository(Application application){
        return DataRepository.getInstance(RemoteDataSource.getInstance(),
                LocalDataSource.getInstance(),application);
    }
}
