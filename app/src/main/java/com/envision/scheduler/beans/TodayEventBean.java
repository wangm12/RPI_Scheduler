package com.envision.scheduler.beans;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Calendar;

/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 29/3/2018 下午1:07
 */
public class TodayEventBean implements Serializable,Comparable<TodayEventBean> {
    private static final long serialVersionUID = 2854638927864500887L;
    public String start_time;
    public String end_time;
    public Calendar calendar;
    public String location;
    public String eventName;
    public String GroupName;

    public TodayEventBean(String start_time, String end_time, String location, String eventName, String GroupName) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.location = location;
        this.eventName = eventName;
        this.GroupName = GroupName;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    @Override
    public int compareTo(@NonNull TodayEventBean o) {
        return this.getLocation().compareTo(o.getLocation());
    }
}
