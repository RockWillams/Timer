package com.entity;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.entity.User;
import com.entity.log;
import com.entity.Version;
import com.entity.Event;

import com.entity.UserDao;
import com.entity.logDao;
import com.entity.VersionDao;
import com.entity.EventDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig logDaoConfig;
    private final DaoConfig versionDaoConfig;
    private final DaoConfig eventDaoConfig;

    private final UserDao userDao;
    private final logDao logDao;
    private final VersionDao versionDao;
    private final EventDao eventDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        logDaoConfig = daoConfigMap.get(logDao.class).clone();
        logDaoConfig.initIdentityScope(type);

        versionDaoConfig = daoConfigMap.get(VersionDao.class).clone();
        versionDaoConfig.initIdentityScope(type);

        eventDaoConfig = daoConfigMap.get(EventDao.class).clone();
        eventDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        logDao = new logDao(logDaoConfig, this);
        versionDao = new VersionDao(versionDaoConfig, this);
        eventDao = new EventDao(eventDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(log.class, logDao);
        registerDao(Version.class, versionDao);
        registerDao(Event.class, eventDao);
    }
    
    public void clear() {
        userDaoConfig.getIdentityScope().clear();
        logDaoConfig.getIdentityScope().clear();
        versionDaoConfig.getIdentityScope().clear();
        eventDaoConfig.getIdentityScope().clear();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public logDao getLogDao() {
        return logDao;
    }

    public VersionDao getVersionDao() {
        return versionDao;
    }

    public EventDao getEventDao() {
        return eventDao;
    }

}
