package com.envision.calendar.interf;

import android.graphics.Canvas;

import com.envision.calendar.view.Day;


public interface IDayRenderer {

    void refreshContent();

    void drawDay(Canvas canvas, Day day);

    IDayRenderer copy();

}
