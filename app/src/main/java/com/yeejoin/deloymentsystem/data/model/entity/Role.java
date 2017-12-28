package com.yeejoin.deloymentsystem.data.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by maodou on 2017/12/28.
 * 角色Entity
 */

@Entity
public class Role {
    @PrimaryKey
    public int id;

    public String name;

    public String description;

    public String roleType;

}
