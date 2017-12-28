package com.yeejoin.commonapplication.base;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maodou on 2017/12/28.
 */

public class RetrofitFactory {

    public static Retrofit getRetrofitClient(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient provideClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
    }

    public static BaseNetworkService provideService(boolean hasToken, String baseUrl){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        if (hasToken)
            builder.client(provideClient());
        return builder.build().create(BaseNetworkService.class);
    }
}
