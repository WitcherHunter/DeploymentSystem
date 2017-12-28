package com.yeejoin.commonapplication.data.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by maodou on 2017/12/28.
 * 网络配置Entity
 */

@Entity
public class NetConfig {
    @PrimaryKey
    public int id;

    public String host;

    public String port;

    public String name;

    public String distinguish;

    public String type;
}
