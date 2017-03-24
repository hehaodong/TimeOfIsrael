package com.andy.timeofisrael;

import android.app.Application;

import com.andy.timeofisrael.bean.DaoMaster;
import com.andy.timeofisrael.bean.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lancelot on 2016/11/13.
 */

public class MyApplication extends Application {

    private static MyApplication sInstance;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "repos-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static MyApplication get() {
        return sInstance;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
