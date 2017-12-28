package com.yeejoin.deloymentsystem.base;

import android.arch.lifecycle.LiveData;

import com.yeejoin.deloymentsystem.data.model.DataListResult;
import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;
import com.yeejoin.deloymentsystem.data.model.entity.TokenValid;
import com.yeejoin.deloymentsystem.data.model.entity.User;
import com.yeejoin.deloymentsystem.request.LoginRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by maodou on 2017/12/28.
 */

public interface BaseNetworkService {
    @GET(APISettings.TokenValid)
    Call<TokenValid> tokenValid(@Path("authToken") String token);

    @POST(APISettings.LoginUrl)
    Call<Login> login(@Body LoginRequest request);

    @GET(APISettings.NetConfig)
    Call<DataListResult<List<NetConfig>>> getNetConfig();
}
