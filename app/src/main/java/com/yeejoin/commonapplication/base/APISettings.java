package com.yeejoin.commonapplication.base;

/**
 * Created by maodou on 2017/12/28.
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
