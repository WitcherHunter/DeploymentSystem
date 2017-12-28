package com.yeejoin.commonapplication.data.local.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.yeejoin.commonapplication.MyApplication;
import com.yeejoin.commonapplication.data.local.dao.LoginDao;
import com.yeejoin.commonapplication.data.local.dao.NetConfigDao;
import com.yeejoin.commonapplication.data.model.entity.Company;
import com.yeejoin.commonapplication.data.model.entity.Login;
import com.yeejoin.commonapplication.data.model.entity.NetConfig;
import com.yeejoin.commonapplication.data.model.entity.Role;
import com.yeejoin.commonapplication.data.model.entity.Site;
import com.yeejoin.commonapplication.data.model.entity.User;

/**
 * Created by maodou on 2017/12/28.
 * 数据库
 */

@Database(entities = {User.class, Company.class, Role.class, Site.class,
        NetConfig.class, Login.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance = null;

    @VisibleForTesting
    public static final String DB_NAME = "deploySystem.db";

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        MyApplication.getInstance().getAppExecutors().diskIO()
                                .execute(() -> {
                                    AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
                                    NetConfig config = new NetConfig();
                                    config.id = 10000;
                                    config.host = "172.16.11.15";
                                    config.port = "8000";
                                    config.name = "网络配置接口";
                                    database.runInTransaction(() -> database.netConfigDao().saveNetConfig(config));
                                });
                    }
                }).build();
    }

    public abstract NetConfigDao netConfigDao();

    public abstract LoginDao loginDao();
}
