package com.softorg.rock.timer.util;

public class MyItem {

    public String date;
    public  String time;
    public  String content;
    public  String leftTime;
    public String notification;

    public MyItem( String date, String time, String content, String leftTime, String notification) {
        this.date = date;
        this.content=content;
        this.time = time;
        this.leftTime = leftTime;
        this.notification =  notification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}