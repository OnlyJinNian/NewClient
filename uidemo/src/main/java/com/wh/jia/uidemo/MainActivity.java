package com.wh.jia.uidemo;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn_left;
    private Button btn_right;
    private Switch switch1;
    private ProgressBar progressBar3;
    private EditText edit_input;
    private Button btn_qd;
    private RadioGroup radio1;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private ImageView imageView;
    private SeekBar seekBar;
    private CheckBox cb_ch;
    private CheckBox cb_math;
    private CheckBox cb_en;
    private String ch = "";
    private String math = "";
    private String en = "";
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initEvent();
    }
    private void init() {
        tv = findViewById(R.id.tv);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        switch1 = findViewById(R.id.switch1);
        progressBar3 = findViewById(R.id.progressBar3);
        edit_input = findViewById(R.id.edit_input);
        btn_qd = findViewById(R.id.btn_qd);
        radio1 = findViewById(R.id.radio1);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        imageView = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);
        cb_ch = findViewById(R.id.cb_ch);
        cb_math = findViewById(R.id.cb_math);
        cb_en = findViewById(R.id.cb_en);
        ratingBar = findViewById(R.id.ratingBar);
    }
    private void initEvent() {

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("左边");
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("右边");
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                tv.setText("开");
                else
                    tv.setText("关");
            }
        });
        btn_qd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String s = edit_input.getText().toString().trim();
                if(TextUtils.isEmpty(s)){
                    s = "0";
                }
                progressBar3.setProgress(Integer.valueOf(s),true);
                tv.setText(s);
            }
        });
        radio1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton1){
                    imageView.setImageResource(R.drawable.iphone);
                }else{
                    imageView.setImageResource(R.drawable.android);
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        cb_ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ch = "语文";
                }else{
                    ch = "";
            }
            tv.setText(ch+math+en);
            }

        });
        cb_math.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    math = "数学";
                }else{
                    math = "";
                }
                tv.setText(ch+math+en);
            }
        });
        cb_en.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    en = "英语";
                }else{
                    en = "";
                }
                tv.setText(ch+math+en);
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, ""+rating, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
