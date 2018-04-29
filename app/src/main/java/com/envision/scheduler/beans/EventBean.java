package com.envision.scheduler.beans;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 29/3/2018 下午1:07
 */
public class EventBean implements Serializable,Comparable<EventBean> {
    private static final long serialVersionUID = 2854638927864500887L;
    public String name;
    public String subName;
    public String eventName;

    public EventBean(String name, String subName, String eventName) {
        this.name = name;
        this.subName = subName;
        this.eventName = eventName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public int compareTo(@NonNull EventBean o) {
        return this.getName().compareTo(o.getName());
    }
}
