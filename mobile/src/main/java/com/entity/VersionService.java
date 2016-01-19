package com.entity;

/**
 * Created by Rock on 2016/1/15.
 */
public class VersionService extends BaseService<Version,Long> {
    public  VersionService(VersionDao dao){
        super(dao);
    };
}