package com.on.diary.execldata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/3/24.
 * 数据库操作帮助类
 */

public class DBhelper extends SQLiteOpenHelper {

    public static final String TABLE_EXTRA_VOICE_INFO = "ExeclInfo";
    public static String DATA_BASE_NAME = "execl.db";
    private static final int VERSION = 1;
    private static DBhelper instance;

    public static DBhelper getInstance( Context context ) {
        if (instance == null) {
            instance = new DBhelper( context );
        }
        return instance;
    }

    public DBhelper(Context context){
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUser(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateTableUser(db, oldVersion, newVersion);
    }

    /**
     * 创建用户表
     *
     * */
    public void createTableUser(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EXTRA_VOICE_INFO + " (_id INTEGER PRIMARY KEY AUTOINCREMENT"
                + ",duty TEXT NOT NULL, name TEXT ,commpany TEXT, number TEXT )" );
    }

    /**
     * 更新用户表
     *
     * */
    public void updateTableUser(SQLiteDatabase db, int oldVersion, int newVersion){
        if ( oldVersion != newVersion ){
            db.execSQL( "DROP TABLE IF EXISTS " + TABLE_EXTRA_VOICE_INFO );
            createTableUser(db);
        }
    }
}



