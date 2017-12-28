package com.yeejoin.commonapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yeejoin.commonapplication.data.DataRepository;
import com.yeejoin.commonapplication.data.Injection;
import com.yeejoin.commonapplication.data.model.entity.Login;
import com.yeejoin.commonapplication.data.model.entity.NetConfig;

import java.util.List;

/**
 * Created by maodou on 2017/12/28.
 *
 * 登录ViewModel
 */

public class LoginViewModel extends AndroidViewModel {
    private DataRepository repository = null;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = Injection.getDataRepository(application);
    }

    public LiveData<Login> login(String username, String password) {
        return repository.login(username,password);
    }

    public LiveData<Login> getLastLogin(){
        return repository.getLastLogin();
    }

    public LiveData<List<NetConfig>> getNetConfig(){
        return repository.getNetConfigs();
    }
}
