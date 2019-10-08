package com.wh.jia.createxml01;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<SMS> smsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryAuthority();
        //初始化备份数据
        smsList = new ArrayList<SMS>();
        for(int i=0;i<10;i++){
            SMS sms = new SMS("10086"+i,"hello world"+i,"2019年"+i+"月");
            smsList.add(sms);
        }

    }
    //通过StringBuffer的方式生成个xml文件
    public void createXml(View view) {
        //创建StringBuffer对象
        StringBuffer stringBuffer = new StringBuffer();
        //开始组拼xml头
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"+"\n");
        //开始组拼xml根节点
        stringBuffer.append("<smss>"+"\n");
        //开始组拼xml sms节点
        for(SMS sms:smsList){
            stringBuffer.append("<sms>"+"\n");
            stringBuffer.append("<address>");
            stringBuffer.append(sms.getAddress());
            stringBuffer.append("</address>"+"\n");
            stringBuffer.append("<body>");
            stringBuffer.append(sms.getBody());
            stringBuffer.append("</body>"+"\n");
            stringBuffer.append("<date>");
            stringBuffer.append(sms.getDate());
            stringBuffer.append("</date>"+"\n");
            stringBuffer.append("</sms>"+"\n");
        }

        stringBuffer.append("</smss>");
        //保存到sd卡
        File file = new File(Environment.getExternalStorageDirectory().getPath(),"smsbackup.xml");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(stringBuffer.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*动态申请权限*/
    private void queryAuthority() {
        int hasRedContactsPermission = 0;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            hasRedContactsPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(hasRedContactsPermission != PackageManager.PERMISSION_GRANTED){
            // 如果没有授予该权限，就去提示用户请求
            showDialogTipUserRequestPermission();
        }
        return;
    }

    private void showDialogTipUserRequestPermission() {
        new AlertDialog.Builder(this)
                .setTitle("存储权限不可用")
                .setMessage("本应用登录需要有访问SD卡权限")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRequestPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }

    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
    }
    // 用户权限 申请 的回调方法
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    queryAuthority();
                } else {
                    Toast.makeText(MainActivity.this, "权限申请失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
