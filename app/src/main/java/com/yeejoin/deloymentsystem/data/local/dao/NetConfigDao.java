package com.yeejoin.deloymentsystem.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yeejoin.deloymentsystem.data.model.entity.NetConfig;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by maodou on 2017/12/28.
 * 网络配置Dao
 */

@Dao
public interface NetConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NetConfig> configs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveNetConfig(NetConfig config);

    @Update
    void updateNetConfig(NetConfig config);

    @Delete
    void deleteNetConfig(NetConfig config);

    @Query("SELECT * FROM netconfig")
    LiveData<List<NetConfig>> loadNetConfig();
}
