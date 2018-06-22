package com.on.diary.execldata;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Administrator on 2018/3/24.
 */

public class ExeclAsynTask extends AsyncTask<Void, Void, Void> {

    private Context mContext = null;
    private DBmanager dbManager = null;

    public ExeclAsynTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        dbManager = DBmanager.getInstance(mContext);
        return null;
    }


}
