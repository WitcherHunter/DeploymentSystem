package com.yeejoin.deloymentsystem.data.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Created by maodou on 2017/12/4.
 * 用户信息Entity
 */

@Entity(primaryKeys = {"user_id","user_name"})
public class User {
    @ColumnInfo(name = "user_id")
    public int id;

    @Embedded(prefix = "role")
    public Role role;

    @NonNull
    @ColumnInfo(name = "user_name")
    public String userName;

    @Embedded(prefix = "company")
    public Company company;

    public String name;

    public String mobile;
}
