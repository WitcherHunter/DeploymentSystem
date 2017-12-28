package com.yeejoin.commonapplication.data.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by maodou on 2017/12/28.
 */

@Entity
public class Site {
    @PrimaryKey
    @ColumnInfo(name = "site_id")
    public int id;

    public String siteLevelDescription;

    public int sitePersonNumMax;

    public int sitePersonNumMin;

    public int siteClassNum;
}
