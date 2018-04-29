package com.envision.timepickerdialog.data.source;

import com.envision.timepickerdialog.data.WheelCalendar;

/**
 * Created by zihao
 */
public interface TimeDataSource {

    int getMinYear();

    int getMaxYear();

    int getMinMonth(int currentYear);

    int getMaxMonth(int currentYear);

    int getMinDay(int year, int month);

    int getMaxDay(int year, int month);

    int getMinHour(int year, int month, int day);

    int getMaxHour(int year, int month, int day);

    int getMinMinute(int year, int month, int day, int hour);

    int getMaxMinute(int year, int month, int day, int hour);

    boolean isMinYear(int year);

    boolean isMinMonth(int year, int month);

    boolean isMinDay(int year, int month, int day);

    boolean isMinHour(int year, int month, int day, int hour);
//
//    boolean isMaxYear(int year);
//
//    boolean isMaxMonth(int year, int month);
//
//    boolean isMaxDay(int year, int month, int day);
//
//    boolean isMaxMinute(int year, int month, int day, int hour);

    WheelCalendar getDefaultCalendar();

}
