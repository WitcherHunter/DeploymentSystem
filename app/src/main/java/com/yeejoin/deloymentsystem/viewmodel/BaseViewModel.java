package com.yeejoin.deloymentsystem.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.yeejoin.deloymentsystem.data.DataRepository;
import com.yeejoin.deloymentsystem.data.Injection;
import com.yeejoin.deloymentsystem.data.model.entity.Login;

/**
 * Created by maodou on 2017/12/7.
 */

public class SplashViewModel extends AndroidViewModel {
    private DataRepository dataRepository = null;


    public SplashViewModel(@NonNull Application application) {
        super(application);
        dataRepository = Injection.getDataRepository(application);
    }

    public LiveData<Boolean> tokenValid(String token){
        return dataRepository.tokenValid(token);
    }

    public LiveData<Login> getLastLogin(){
        return dataRepository.getLastLogin();
    }
}
