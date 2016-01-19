package com.softorg.rock.timer.util;

import android.app.Application;

import com.entity.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

/**
 * Created by Rock on 2016/1/14.
 */

public class RockApplication extends Application
{
    private static final String VALUE = "Harvey";

    private String value;

    private  User user;


    DbSync dbSync;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onCreate()
    {

        super.onCreate();
        setValue(VALUE); // 初始化全局变量
        super.onCreate();
        DbCore.init(this);
    }


    public void init(){
        dbSync.init(this);
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}