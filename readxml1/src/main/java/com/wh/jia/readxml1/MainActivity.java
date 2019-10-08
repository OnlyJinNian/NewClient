package com.wh.jia.readxml1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //显示天气信息
        tv_weather = findViewById(R.id.tv_weather);
        //获取资产的管理者
        try {
            InputStream in = getAssets().open("weather1.xml");
            List<City> infosList = WeatherParser.parserXml(in);
            StringBuffer stringBuffer = new StringBuffer();
            for(City city : infosList){
                stringBuffer.append(city.toString());
            }
            tv_weather.setText(stringBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
