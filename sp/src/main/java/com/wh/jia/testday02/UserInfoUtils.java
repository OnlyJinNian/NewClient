package com.wh.jia.testday02;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by JIA on 2019/9/17.
 */

public class UserInfoUtils {
    public static boolean saveUserInfo(String username, String pwd, Context context){
        try {
            String result = username+"##"+pwd;
           /* String path = context.getFilesDir().getPath();
            //创建一个file类指定我们要把数据存储的位置
            //File file = new File("/data/data/com.wh.jia.testday02/userInfo.txt");
            File file = new File(path,"userInfo.txt");
            //创建一个文件的输出流
            FileOutputStream fos = new FileOutputStream(file);*/
           //数据保存到data目录
            //FileOutputStream fos = context.openFileOutput("userInfoo.txt",0);
            //把数据保存到sd卡
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path,"info.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(result.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static User readUserInfo(Context context){
        try {
            User user;
           /* String path = context.getFilesDir().getPath();
            File file = new File(path,"userInfo.txt");
            FileInputStream fis = new FileInputStream(file);*/
           // FileInputStream fis = context.openFileInput("userInfoo.txt");
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path,"info.txt");
            Log.i("路径",file.getPath());
            FileInputStream fis = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String content = bufferedReader.readLine();

            /*切割字符串*/
            String[] splits = content.split("##");
            String name = splits[0];
            String pwd = splits[1];
            user = new User(name,pwd);
            fis.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
