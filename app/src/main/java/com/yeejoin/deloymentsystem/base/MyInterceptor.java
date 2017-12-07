package com.yeejoin.deloymentsystem.base;

import com.yeejoin.deloymentsystem.MyApplication;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by maodou on 2017/12/7.
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Headers headers = original.headers();
        Headers newHeaders = headers.newBuilder()
                .add("X-Access-Token", MyApplication.getInstance().getLoginInfo().token)
                .build();

        Request request = original.newBuilder()
                .headers(newHeaders)
                .build();

        return chain.proceed(request);
    }
}
