package com.wh.jia.createsql;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        //打开或者创建数据库  如果第一次就是创建
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
        //打开或者创建数据库  如果第一次就是创建 如果磁盘满了，返回只读
        //SQLiteDatabase sqLiteDatabase1 = myOpenHelper.getReadableDatabase();
    }
}
