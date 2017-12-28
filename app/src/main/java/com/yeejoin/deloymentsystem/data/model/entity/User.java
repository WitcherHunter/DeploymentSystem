package com.yeejoin.deloymentsystem.data.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.RoomWarnings;
import android.support.annotation.NonNull;

/**
 * Created by maodou on 2017/12/28.
 * 用户信息Entity
 */

@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
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
