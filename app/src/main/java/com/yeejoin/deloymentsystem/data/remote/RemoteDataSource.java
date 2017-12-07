package com.yeejoin.deloymentsystem.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.yeejoin.deloymentsystem.AppExecutors;
import com.yeejoin.deloymentsystem.MyApplication;
import com.yeejoin.deloymentsystem.base.BaseNetworkService;
import com.yeejoin.deloymentsystem.base.RetrofitFactory;
import com.yeejoin.deloymentsystem.data.DataSource;
import com.yeejoin.deloymentsystem.data.local.db.AppDatabase;
import com.yeejoin.deloymentsystem.data.model.DataListResult;
import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;
import com.yeejoin.deloymentsystem.request.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maodou on 2017/12/5.
 * 网络数据源
 */

public class RemoteDataSource extends DataSource {
    private static RemoteDataSource instance = null;
    private AppDatabase database = null;
    private AppExecutors executors = null;

    private RemoteDataSource() {
        database = MyApplication.getInstance().getDatabase();
        executors = MyApplication.getInstance().getAppExecutors();
    }

    public static RemoteDataSource getInstance() {
        if (instance == null) {
            synchronized (RemoteDataSource.class) {
                if (instance == null)
                    instance = new RemoteDataSource();
            }
        }
        return instance;
    }

    @Override
    public LiveData<List<NetConfig>> getNetConfigs() {
        final MutableLiveData<List<NetConfig>> configs = new MutableLiveData<>();
        RetrofitFactory.getRetrofitClient("http://172.16.11.15:8000/")
                .create(BaseNetworkService.class)
                .getNetConfig()
                .enqueue(new Callback<DataListResult<List<NetConfig>>>() {
                    @Override
                    public void onResponse(Call<DataListResult<List<NetConfig>>> call, Response<DataListResult<List<NetConfig>>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            configs.setValue(response.body().dataList);
                        }
                    }

                    @Override
                    public void onFailure(Call<DataListResult<List<NetConfig>>> call, Throwable t) {
                        Log.e("RemoteDataSource", "onFailure: getNetConfigs -- " + t.getMessage());
                    }
                });
        return configs;
    }

    @Override
    public LiveData<Login> login(String username, String password) {
        MutableLiveData<Login> loginLiveData = new MutableLiveData<>();

        LoginRequest request = new LoginRequest();
        request.userName = username;
        request.password = password;

        RetrofitFactory.getRetrofitClient("http://172.16.11.15:8800")
                .create(BaseNetworkService.class)
                .login(request)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Login login = response.body();
                            executors.diskIO().execute(() -> database.loginDao().deleteAll());
                            executors.diskIO().execute(() -> database.loginDao().insertLogin(login));
                            MyApplication.getInstance().setLoginInfo(login);
                            loginLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
        return loginLiveData;
    }
}
