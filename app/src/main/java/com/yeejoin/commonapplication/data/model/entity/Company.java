package com.yeejoin.commonapplication.data.model.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.RoomWarnings;

/**
 * Created by maodou on 2017/12/28.
 * 单位Entity
 */

@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Entity
public class Company {
    @PrimaryKey
    public int id;

    public String companyName;

    public String companyLevel;

    public String description;

    public String parentId;

    public String compCode;

    public String contact;

    public double latitude;

    public double longitude;

    public String address;

    public String telephone;

    public String email;

    @Embedded(prefix = "site")
    public Site site;
}
