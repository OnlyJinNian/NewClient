package com.wh.jia.newsclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by JIA on 2019/9/17.
 */

public class JsonParse {
    public static List<NewsInfo> getNewInfos(String json){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<NewsInfo>>(){}.getType();
        List<NewsInfo> newsInfoList = gson.fromJson(json,listType);
        return newsInfoList;
    }
}
