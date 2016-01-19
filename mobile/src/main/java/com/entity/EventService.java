package com.entity;

/**
 * Created by Rock on 2016/1/15.
 */
public class EventService extends BaseService<Event,Long> {
    public EventService(EventDao dao) {
        super(dao);
    }
}
