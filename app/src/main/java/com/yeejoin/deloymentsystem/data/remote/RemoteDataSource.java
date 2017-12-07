package com.yeejoin.deloymentsystem.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.yeejoin.deloymentsystem.AppExecutors;
import com.yeejoin.deloymentsystem.MyApplication;
import com.yeejoin.deloymentsystem.base.RetrofitFactory;
import com.yeejoin.deloymentsystem.data.DataSource;
import com.yeejoin.deloymentsystem.data.local.db.AppDatabase;
import com.yeejoin.deloymentsystem.data.model.DataListResult;
import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;
import com.yeejoin.deloymentsystem.data.model.entity.TokenValid;
import com.yeejoin.deloymentsystem.request.LoginRequest;
import com.yeejoin.deloymentsystem.utils.NetworkConstant;

import java.util.List;

import okhttp3.ResponseBody;
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
    protected LiveData<Boolean> tokenValid(String token) {
        final MutableLiveData<Boolean> valid = new MutableLiveData<>();

        RetrofitFactory.provideService(false,NetworkConstant.SafeBaseUrl)
                .tokenValid(token)
                .enqueue(new Callback<TokenValid>() {
                    @Override
                    public void onResponse(Call<TokenValid> call, Response<TokenValid> response) {
                        if (response.isSuccessful()){
                            if (response.body().result.equals("TOKEN_VALID_SUCCESS"))
                                valid.setValue(true);
                            else
                                valid.setValue(false);
                        } else
                            valid.setValue(false);
                    }

                    @Override
                    public void onFailure(Call<TokenValid> call, Throwable t) {
                        valid.setValue(false);
                    }
                });

        return valid;
    }

    @Override
    public LiveData<List<NetConfig>> getNetConfigs() {
        final MutableLiveData<List<NetConfig>> configs = new MutableLiveData<>();

        RetrofitFactory.provideService(false,NetworkConstant.ConfigBaseUrl)
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

        RetrofitFactory.provideService(false, NetworkConstant.SafeBaseUrl)
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
