package com.yeejoin.deloymentsystem.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yeejoin.deloymentsystem.data.DataRepository;
import com.yeejoin.deloymentsystem.data.Injection;
import com.yeejoin.deloymentsystem.data.model.entity.Login;

/**
 * Created by maodou on 2017/12/28.
 *
 * 个人信息页ViewModel
 */

public class PersonalViewModel extends AndroidViewModel {
    private LiveData<Login> loginInfo = null;
    private DataRepository dataRepository = null;

    public PersonalViewModel(@NonNull Application application) {
        super(application);
        dataRepository = Injection.getDataRepository(application);
    }

    public LiveData<Login> getLoginInfo(){
        return dataRepository.getLastLogin();
    }
}
