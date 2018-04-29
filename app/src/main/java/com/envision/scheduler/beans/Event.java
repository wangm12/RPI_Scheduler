package com.envision.scheduler.beans;

import android.graphics.Color;
import android.text.TextUtils;

import com.envision.weekview.WeekViewEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 26/3/2018 下午3:08
 */
public class Event {

    private String mName;
    private int mDayOfMonth;
    private String mStartTime;
    private String mEndTime;
    private String mColor;
    private String mTextColor;
    private String groupName;
    private int mMonth;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getDayOfMonth() {
        return mDayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.mDayOfMonth = dayOfMonth;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        this.mMonth = month;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        this.mStartTime = startTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        this.mEndTime = endTime;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        this.mColor = color;
    }

    public String getTextColor() {
        return mTextColor;
    }

    public void setTextColor(String mTextColor) {
        this.mTextColor = mTextColor;
    }

    public WeekViewEvent toWeekViewEvent(){

        // Parse time.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        Date start = new Date();
        Date end = new Date();
        try {
            start = sdf.parse(getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            end = sdf.parse(getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar now = Calendar.getInstance();
        Calendar startTime = (Calendar) now.clone();
        startTime.setTimeInMillis(start.getTime());
        startTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
        startTime.set(Calendar.MONTH, getMonth());
        startTime.set(Calendar.DAY_OF_MONTH, getDayOfMonth());
        Calendar endTime = (Calendar) startTime.clone();
        endTime.setTimeInMillis(end.getTime());
        endTime.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
        endTime.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
        endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));

        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setName(getName());
        weekViewEvent.setGroupName(getGroupName());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);
        weekViewEvent.setTextColor(TextUtils.isEmpty(getTextColor()) ? Color.parseColor("#80000000") : Color.parseColor(getTextColor()));
        weekViewEvent.setColor(Color.parseColor(getColor()));
        weekViewEvent.setData(this);

        return weekViewEvent;
    }
}
