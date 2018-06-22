package com.on.diary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.on.diary.app.MyApplication;
import com.on.diary.bean.User;
import com.on.diary.db.DaoSession;
import com.on.diary.db.UserDao;
import com.on.diary.execldata.ExeclAsynTask;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 * 数据库操作
 */

public class MpChartActivity extends Activity {
    UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mpchart);

        //使用数据库
        MyApplication application = new MyApplication();
        DaoSession daoSession = application.getDaoSession();
        userDao = daoSession.getUserDao();


    }

    private void clickExcelToDb() {
        ExeclAsynTask extraTask = new ExeclAsynTask(this);
        extraTask.execute();
    }
    //插入baocun数据
    public void insert() {
        User users = new User();
        users.setId(1);
        users.setAge(20);
        users.setName("小花");
        userDao.insert(users);
    }

    public void save() {
        User user = new User();
        user.setId(1);
        user.setName("小明");
        user.setAge(16);

        //插入或者替换
        userDao.insertOrReplace(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public List query() {
        return userDao.loadAll();
    }

    //根据id查数据
    public User query2() {
        return userDao.loadByRowId(1);

    }

    public void deletebyid(long userid) {
        userDao.deleteByKey(1L);
    }

    public List query4() {
        QueryBuilder builder = userDao.queryBuilder();
        return builder.where(UserDao.Properties.Age.gt(10)).build().list();
    }

    public List query3() {
        return userDao.queryRaw("where AGE>?", "10");//查询年龄大于10的用户
    }
}

