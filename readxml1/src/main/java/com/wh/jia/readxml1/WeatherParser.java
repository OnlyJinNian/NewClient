package com.wh.jia.readxml1;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JIA on 2019/9/20.
 */

public class WeatherParser {
    public static List<City> parserXml(InputStream in) throws XmlPullParserException, IOException {
        //声明集合对象
        List<City> infolist = null;
        //声明城市对象
        City city =null;
        //获取XmlPullParser解析实例
        XmlPullParser xmlPullParser = Xml.newPullParser();
        //设置XmlPullParser的参数
        xmlPullParser.setInput(in,"utf-8");
        //获取事件类型
        int type = xmlPullParser.getEventType();
        while(type!= XmlPullParser.END_DOCUMENT){

            //具体判断下解析到了那个节点
            switch (type){
                case XmlPullParser.START_TAG://解析开始标签
                    //判断那个节点
                    if("infos".equals(xmlPullParser.getName())){
                    //创建一个集合对象
                    infolist = new ArrayList<City>();
                    }else if ("city".equals(xmlPullParser.getName())){
                        //创建city对象
                        city = new City();
                        //获取id值
                        String id = xmlPullParser.getAttributeValue(0);
                        city.setId(id);
                    }else if ("temp".equals(xmlPullParser.getName())){//判断温度temp节点
                        //获取temp值
                        String temp = xmlPullParser.nextText();
                        city.setTemp(temp);
                    }else if ("weather".equals(xmlPullParser.getName())){//判断天气weather节点
                        //获取weather值
                        String weather = xmlPullParser.nextText();
                        city.setWeather(weather);
                    }else if ("name".equals(xmlPullParser.getName())){//判断城市名字name节点
                        //获取name值
                        String name = xmlPullParser.nextText();
                        city.setName(name);
                    }else if ("pm".equals(xmlPullParser.getName())){//判断pm节点
                        //获取pm值
                        String pm = xmlPullParser.nextText();
                        city.setPm(pm);
                    }else if ("wind".equals(xmlPullParser.getName())){//判断wind节点
                        //获取wind值
                        String wind = xmlPullParser.nextText();
                        city.setWind(wind);
                    }

                    break;
                case XmlPullParser.END_TAG://解析结束标签
                //判断解析的结束标签
                    if("city".equals(xmlPullParser.getName())){
                    infolist.add(city);
                    }
                    break;
            }
            //不停向下解析
            type = xmlPullParser.next();
        }
        return infolist;
    }
}
