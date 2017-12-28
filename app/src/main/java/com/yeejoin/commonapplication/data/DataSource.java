package com.yeejoin.commonapplication.data;

import android.arch.lifecycle.LiveData;

import com.yeejoin.commonapplication.data.model.entity.Login;
import com.yeejoin.commonapplication.data.model.entity.NetConfig;

import java.util.List;

/**
 * Created by maodou on 2017/12/28.
 * 数据源基类
 */

public abstract class DataSource {

    /**
     * 验证token
     * @param token
     * @return
     */
    protected LiveData<Boolean> tokenValid(String token){
        return null;
    }

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
