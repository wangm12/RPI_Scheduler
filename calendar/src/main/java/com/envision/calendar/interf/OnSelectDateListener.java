package com.envision.calendar.interf;

import com.envision.calendar.model.CalendarDate;


public interface OnSelectDateListener {
    void onSelectDate(CalendarDate date);

    void onSelectOtherMonth(int offset);//点击其它月份日期
}
