package com.yeejoin.commonapplication.base;

import com.yeejoin.commonapplication.data.model.DataListResult;
import com.yeejoin.commonapplication.data.model.entity.Login;
import com.yeejoin.commonapplication.data.model.entity.NetConfig;
import com.yeejoin.commonapplication.data.model.entity.TokenValid;
import com.yeejoin.commonapplication.request.LoginRequest;

import java.util.List;

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
