package com.yeejoin.deloymentsystem.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;
import com.yeejoin.deloymentsystem.data.model.entity.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by maodou on 2017/12/5.
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
        LiveData<List<NetConfig>> configs = mLocalDataSource.getNetConfigs();
        if (configs == null || configs.getValue() == null || configs.getValue().size() <= 1){
            return mRemoteDataSource.getNetConfigs();
        }
        return mLocalDataSource.getNetConfigs();
    }

    public LiveData<Login> login(String username, String password){
        return mRemoteDataSource.login(username, password);
    }

    public LiveData<Login> getLastLogin(){
        return mLocalDataSource.getLastLogin();
    }
}
