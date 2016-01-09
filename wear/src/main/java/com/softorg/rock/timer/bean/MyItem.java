package com.softorg.rock.timer.bean;

public class MyItem {

    public int icon;
    public String date;
    public  String time;
    public  String content;
    public  String leftTime;
    public String notification;

    public MyItem(int icon,String date,String time,String content,String leftTime,String notification) {
        this.icon = icon;
        this.date = date;
        this.content=content;
        this.time = time;
        this.leftTime = time;
        this.notification =  notification;
    }
}