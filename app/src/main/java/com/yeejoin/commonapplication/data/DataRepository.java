package com.yeejoin.commonapplication.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yeejoin.commonapplication.data.model.entity.Login;
import com.yeejoin.commonapplication.data.model.entity.NetConfig;
import com.yeejoin.commonapplication.utils.Util;

import java.util.List;

/**
 * Created by maodou on 2017/12/28.
 */

public class DataRepository {
    private static DataRepository instance = null;

    private final DataSource mRemoteDataSource;

    private final DataSource mLocalDataSource;

    private static Application sApplication = null;

    private DataRepository(@NonNull DataSource remoteDataSource,
                           @NonNull DataSource localDataSource){
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static DataRepository getInstance(@NonNull DataSource remoteDataSource,
                                             @NonNull DataSource localDataSource,
                                             Application application){
        if (instance == null){
            synchronized (DataRepository.class){
                if (instance == null){
                    instance = new DataRepository(remoteDataSource,localDataSource);
                    sApplication = application;
                }
            }
        }
        return instance;
    }

    public LiveData<List<NetConfig>> getNetConfigs(){
        return Util.isNetworkConnected(sApplication) ? mRemoteDataSource.getNetConfigs() : mLocalDataSource.getNetConfigs();
    }

    public LiveData<Login> login(String username, String password){
        return mRemoteDataSource.login(username, password);
    }

    public LiveData<Login> getLastLogin(){
        return mLocalDataSource.getLastLogin();
    }

    public LiveData<Boolean> tokenValid(String token){
        return mRemoteDataSource.tokenValid(token);
    }
}
