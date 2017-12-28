package com.yeejoin.commonapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yeejoin.commonapplication.data.DataRepository;
import com.yeejoin.commonapplication.data.Injection;
import com.yeejoin.commonapplication.data.model.entity.Login;

/**
 * Created by maodou on 2017/12/28.
 */

public class BaseViewModel extends AndroidViewModel {
    private DataRepository dataRepository = null;


    public BaseViewModel(@NonNull Application application) {
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
