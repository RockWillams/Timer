package com.entity;

/**
 * Created by Rock on 2016/1/15.
 */
public class UserService extends BaseService<User,Long> {
    public  UserService(UserDao dao){
        super(dao);
    };
}
