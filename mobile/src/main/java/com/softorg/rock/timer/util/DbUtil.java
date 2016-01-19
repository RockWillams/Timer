package com.softorg.rock.timer.util;

import com.entity.Event;
import com.entity.EventDao;
import com.entity.EventService;
import com.entity.UserDao;
import com.entity.UserService;
import com.entity.Version;
import com.entity.VersionDao;
import com.entity.VersionService;

/**
 * Created by Rock on 2016/1/15.
 * if u add one Entity into com.entity package,please make sure you do the next 3 steps
 * 1:Edit  ExampleDaoGenerator to create Bean and BeanDao class in the com.entity;
 * 2:Manual create BeanService extending BaseService just like UserService and EventService;
 * 3:add the BeanService in to the DbUtil class and its get/set method;
 * 4:declear one BeanServce object and inite it by DbUtil class in anywhere you want,and then you can use the object beanService just like UserSercie and EventService.
 */
public class DbUtil {
    private static UserService userService;
    private static EventService eventService;
    private static VersionService versionService;



    private static UserDao getUserDao() {
        return DbCore.getDaoSession().getUserDao();
    }

    private static EventDao getEventDao() {
        return DbCore.getDaoSession().getEventDao();
    }

    private static VersionDao getVersionDao() {
        return DbCore.getDaoSession().getVersionDao();
    }



    public static EventService getEventService() {
        if (eventService == null) {
            eventService = new EventService(getEventDao());
        }
        return eventService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService(getUserDao());
        }
        return userService;
    }

    public static VersionService getVersionService() {
        if (versionService == null) {
            versionService = new VersionService(getVersionDao());
        }
        return versionService;
    }
}
