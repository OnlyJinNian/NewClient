package com.wh.jia.readxml1;

/**
 * Created by JIA on 2019/9/20.
 */

public class City {
    private String id;
    private String temp;
    private String weather;
    private String name;
    private String pm;
    private String wind;

    public City() {
    }

    public City(String id, String temp, String weather, String name, String pm, String wind) {
        this.id = id;
        this.temp = temp;
        this.weather = weather;
        this.name = name;
        this.pm = pm;
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", temp='" + temp + '\'' +
                ", weather='" + weather + '\'' +
                ", name='" + name + '\'' +
                ", pm='" + pm + '\'' +
                ", wind='" + wind + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
