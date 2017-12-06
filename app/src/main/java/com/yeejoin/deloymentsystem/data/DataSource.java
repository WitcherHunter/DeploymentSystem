package com.yeejoin.deloymentsystem.data;

import android.arch.lifecycle.LiveData;

import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;
import com.yeejoin.deloymentsystem.data.model.entity.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by maodou on 2017/12/5.
 * 数据源基类
 */

public abstract class DataSource {

    /**
     * 获取网络配置
     */
    protected abstract LiveData<List<NetConfig>> getNetConfigs();

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return Login Entity
     */
    protected LiveData<Login> login(String username, String password){
        return null;
    }

    /**
     * 获取登录信息
     * @return Login Entity
     */
    protected LiveData<Login> getLastLogin(){
        return null;
    }
}
