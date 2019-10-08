package com.wh.jia.createsql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JIA on 2019/9/24.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context,"itheima.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*表结构初始化*/
        db.execSQL("create table info(_id integer primary key autoincrement,name  varchar(20) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table info add phone varchar(2)");

    }
}
