package com.yeejoin.deloymentsystem.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yeejoin.deloymentsystem.data.model.entity.Login;

import java.util.List;

/**
 * Created by maodou on 2017/12/28.
 * 登录Dao
 */

@Dao
public interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLogin(Login login);

    @Delete
    void deleteLogin(Login login);

    @Update
    void updateLogin(Login login);

    @Query("SELECT * FROM login LIMIT 1")
    LiveData<Login> loadLogin();

    @Query("DELETE FROM login")
    void deleteAll();
}
