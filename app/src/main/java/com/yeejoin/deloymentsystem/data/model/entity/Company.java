package com.yeejoin.deloymentsystem.data.model.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by maodou on 2017/12/4.
 * 单位Entity
 */

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
