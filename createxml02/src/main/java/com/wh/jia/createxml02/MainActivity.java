package com.wh.jia.createxml02;

import android.Manifest;
import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<SMS> smsList;
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



    //通过XmlSerializer的方式生成个xml文件
    public void createXml(View view) {
        try {
            //获取XmlSerializer实例通过Xml工具类
            XmlSerializer serializer = Xml.newSerializer();
            //设置XmlSerializer序列化器参数
            File file = new File(Environment.getExternalStorageDirectory().getPath(),"smabackup2.xml");
            FileOutputStream fos = new FileOutputStream(file);
            serializer.setOutput(fos,"utf-8");
            //文档开头
            serializer.startDocument("utf-8",true);
            //根节点
            serializer.startTag(null,"smss");
            //循环写节点
            for(SMS sms :smsList){
                //sms开始节点
                serializer.startTag(null,"sms");
                //address节点
                serializer.startTag(null,"address");
                serializer.text(sms.getAddress());
                serializer.endTag(null,"address");
                //body节点
                serializer.startTag(null,"body");
                serializer.text(sms.getBody());
                serializer.endTag(null,"body");
                //date节点
                serializer.startTag(null,"date");
                serializer.text(sms.getDate());
                serializer.endTag(null,"date");
                //sms结束节点
                serializer.endTag(null,"sms");
            }
            serializer.endTag(null,"smss");
            //文档结尾
            serializer.endDocument();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void queryAuthority() {
        int code = 0;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            code = ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(code!= PackageManager.PERMISSION_GRANTED){
            showDialogTipUserRequestPermission();
        }
    }

    private void showDialogTipUserRequestPermission() {
        new AlertDialog.Builder(this)
                .setTitle("存储权限不可用")
                .setMessage("该应用需要使用存储权限")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRequestPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();;
                    }
                })
                .setCancelable(false).show();
    }

    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
    }
    // 用户权限 申请 的回调方法

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 123:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    queryAuthority();
                }else {
                    Toast.makeText(MainActivity.this, "权限申请失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
