package com.yeejoin.deloymentsystem.base;

/**
 * Created by maodou on 2017/12/5.
 */

public interface APISettings {

    /**
     * 登录
     */
    String LoginUrl = "system/login";

    /**
     * 网络配置
     */
    String NetConfig = "location/hosts";

    /**
     * token验证
     */
    String TokenValid = "system/tokenHeartbeatMonitor/{authToken}";
}
