package com.wh.jia.newsclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout loading;
    private ListView lv_news;
    private List<NewsInfo> newsInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fillData();
    }

    //初始化控件
    private void init() {
        loading = findViewById(R.id.loading);
        lv_news = findViewById(R.id.lv_news);
    }
    /*利用AsyncHttpClient访问网络*/
    private void fillData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getString(R.string.server_url), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes,"utf-8");
                    newsInfoList = JsonParse.getNewInfos(json);
                    if(newsInfoList==null){
                        Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    }else {
                        loading.setVisibility(View.INVISIBLE);
                        lv_news.setVisibility(View.VISIBLE);
                        lv_news.setAdapter(new NewsAdapter(newsInfoList,MainActivity.this));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
