package com.wh.jia.testday02;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText tv_username;
    private EditText tv_password;
    private CheckBox cb_check;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        queryAuthority();
    }



    private void init() {
        tv_username = findViewById(R.id.tv_username);
        tv_password = findViewById(R.id.tv_password);
        cb_check = findViewById(R.id.cb_ischeck);
        //使用SharedPreferences去保存数据，拿到SharePreferences实例
        //name 自动生成个 name.xml文件
        sharedPreferences = getSharedPreferences("infoo",MODE_PRIVATE);
       /* *//*读取data下userInfo的信息*//*
        user = UserInfoUtils.readUserInfo(MainActivity.this);
        if(user!=null){
            tv_username.setText(user.getUserName());
            tv_password.setText(user.getPwd());}*/
        //从利用SharedPreferences保存用户信息的xml文件中取出我们所关心的信息
        tv_username.setText(sharedPreferences.getString("username",""));
        tv_password.setText(sharedPreferences.getString("password",""));
        boolean t = sharedPreferences.getBoolean("checked",false);
        Toast.makeText(this, ""+t, Toast.LENGTH_SHORT).show();
        cb_check.setChecked(t);




    }

    public void login(View view) {
        String username = tv_username.getText().toString().trim();
        String password = tv_password.getText().toString().trim();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
        }else{

            if(cb_check.isChecked()) {

                //获取SharedPreferences的编辑器
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",username);
                editor.putString("password",password);
                editor.putBoolean("checked",true);
                editor.commit();
            }else{
                    Toast.makeText(this, "是否需要记住用户名与密码", Toast.LENGTH_SHORT).show();
                }


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
