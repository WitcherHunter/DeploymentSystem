package com.yeejoin.deloymentsystem.data.model.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by maodou on 2017/12/6.
 * 登录信息Entity
 */

@Entity
public class Login {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("X-Access-Token")
    public String token;

    @Embedded
    public User user;
}
