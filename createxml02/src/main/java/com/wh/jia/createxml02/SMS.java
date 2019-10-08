package com.wh.jia.createxml02;

/**
 * Created by JIA on 2019/9/19.
 */

public class SMS {
    private String address;
    private String body;
    private String date;

    public SMS() {
    }

    public SMS(String address, String body, String date) {
        this.address = address;
        this.body = body;
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
