package com.yeejoin.deloymentsystem.base;

import android.arch.lifecycle.LiveData;

import com.yeejoin.deloymentsystem.data.model.DataListResult;
import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;
import com.yeejoin.deloymentsystem.data.model.entity.User;
import com.yeejoin.deloymentsystem.request.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by maodou on 2017/12/4.
 */

public interface BaseNetworkService {

    @POST(APISettings.LoginUrl)
    Call<Login> login(@Body LoginRequest request);

    @GET(APISettings.NetConfig)
    Call<DataListResult<List<NetConfig>>> getNetConfig();
}
