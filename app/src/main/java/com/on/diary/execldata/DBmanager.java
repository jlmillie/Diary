package com.on.diary.execldata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by Administrator on 2018/3/24.
 */

public class DBmanager {
    public static final String IS_READED_EXTRA_SOUND_DATA = "is_readed_extra_sound_data";
    private static final String TAG = "DBmanager";
    private static final String EXCEPTION = "exception";
    private DBhelper mDBHelper = null;
    private static DBmanager instance = null;

    public static DBmanager getInstance(Context context) {
        if (instance == null) {
            instance = new DBmanager(context.getApplicationContext());
        }
        return instance;
    }

    private DBmanager(Context context) {
        mDBHelper = DBhelper.getInstance(context);
        if (PreferencesUtils.getBoolean(context, IS_READED_EXTRA_SOUND_DATA, true)) {
            readExcelToDB(context);
        }
    }

    /**
     * 读取excel数据到数据库里
     * @param context
     */
    private void readExcelToDB(Context context) {
        try {
            InputStream is = context.getAssets().open("vip.xlsx");
            Workbook book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            ExeclBean info = null;
            for (int i = 1; i < Rows; ++i) {
                String duty = (sheet.getCell(0, i)).getContents();
                String name = (sheet.getCell(1, i)).getContents();
                String commpany = (sheet.getCell(2, i)).getContents();
                String number = (sheet.getCell(3, i)).getContents();

                info = new ExeclBean(duty, name, commpany, number);
                saveInfoToDataBase(info);
            }
            book.close();
            PreferencesUtils.putBoolean(context,IS_READED_EXTRA_SOUND_DATA, false);
        } catch (Exception e) {
           PreferencesUtils.putBoolean(context, IS_READED_EXTRA_SOUND_DATA, true);
            Log.e(TAG, EXCEPTION, e);
        }
    }

    /**
     * 保存该条数据到数据库
     * @param info excel中的某条数据
     */
    private void saveInfoToDataBase(ExeclBean info) {
        if (mDBHelper == null) {
            return;
        }
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("duty", info.getDuty());
            values.put("name", info.getName());
            values.put("commpany", info.getCommpany());
            values.put("number", info.getNumber());
            db.insert(DBhelper.TABLE_EXTRA_VOICE_INFO, null, values);
        } catch (SQLiteException e) {
            Log.e(TAG, EXCEPTION, e);
        } catch (Exception e){
            Log.e(TAG, EXCEPTION, e);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 根据内容获取 整条数据(ExtraVoiceInfo)
     * @param contentStr
     * @return
     */
    public ExeclBean getExtraVoiceInfo(String contentStr) {
        ExeclBean info = null;
        if (mDBHelper == null) {
            return info;
        }

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        if (db == null) {
            return info;
        }

        Cursor cursor = db.rawQuery("select * from ExeclBean where name = ?", new String[] { contentStr });
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String duty = cursor.getString(cursor.getColumnIndex("duty"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String commpany = cursor.getString(cursor.getColumnIndex("commpany"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));
                    info = new ExeclBean(duty, name, commpany, number);
                } while (cursor.moveToNext());
            }

        } catch (SQLiteException e) {
            Log.e(TAG, EXCEPTION, e);
        }  catch (Exception e){
            Log.e(TAG, EXCEPTION, e);
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
            if (db != null) {
                db.close();
            }
        }
        return info;
    }


}
