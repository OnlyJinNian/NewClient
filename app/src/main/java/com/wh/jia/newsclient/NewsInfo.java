package com.wh.jia.newsclient;

/**
 * Created by JIA on 2019/9/17.
 */

public class NewsInfo {
    private String icon;     //图片路径
    private String title;    //新闻标题
    private String content;  //新闻描述
    private int type;        //新闻类型
    private long comment;    //新闻评论数

    public NewsInfo() {
    }

    public NewsInfo(String icon, String title, String content, int type, long comment) {

        this.icon = icon;
        this.title = title;
        this.content = content;
        this.type = type;
        this.comment = comment;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getComment() {
        return comment;
    }

    public void setComment(long comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "图片路径='" + icon + '\'' +
                ", 新闻标题='" + title + '\'' +
                ", 新闻描述='" + content + '\'' +
                ", 新闻类型=" + type +
                ", 新闻评论数=" + comment +
                '}';
    }
}
