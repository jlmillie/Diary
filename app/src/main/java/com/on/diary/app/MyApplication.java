package com.on.diary.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.on.diary.db.DaoMaster;
import com.on.diary.db.DaoSession;

/**
 * Created by Administrator on 2018/2/3.
 */

public class MyApplication extends Application {
    public static Context mcontext;
    private static RequestQueue requestQueue;

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mcontext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(mcontext);

        initGreenDao();
    }

    public static Context getContextObject() {
        return mcontext;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    //初始化
    public void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "text.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();


    }

    //获取daosession
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}



