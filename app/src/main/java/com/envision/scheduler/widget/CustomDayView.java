package com.envision.scheduler.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.envision.calendar.Utils;
import com.envision.calendar.component.State;
import com.envision.calendar.interf.IDayRenderer;
import com.envision.calendar.model.CalendarDate;
import com.envision.calendar.view.DayView;
import com.envision.scheduler.R;


@SuppressLint("ViewConstructor")
public class CustomDayView extends DayView {

    private TextView dateTv;
    private ImageView marker;
    private View selectedBackground;
    private View todayBackground;
    private final CalendarDate today = new CalendarDate();

    /**
     * 构造器
     *
     * @param context 上下文
     * @param layoutResource 自定义DayView的layout资源
     */
    public CustomDayView(Context context, int layoutResource) {
        super(context, layoutResource);
        dateTv = findViewById(R.id.date);
        marker = findViewById(R.id.maker);
        selectedBackground = findViewById(R.id.selected_background);
        todayBackground = findViewById(R.id.today_background);
    }

    @Override
    public void refreshContent() {
        renderToday(day.getDate());
        renderSelect(day.getState());
        renderMarker(day.getDate(), day.getState());
        super.refreshContent();
    }

    private void renderMarker(CalendarDate date, State state) {
        if (Utils.loadMarkData().containsKey(date.toString())) {
            if (state == State.SELECT || date.toString().equals(today.toString())) {
                marker.setVisibility(GONE);
            } else {
                marker.setVisibility(VISIBLE);
                if (Utils.loadMarkData().get(date.toString()).equals("0")) {
                    marker.setEnabled(true);
                } else {
                    marker.setEnabled(false);
                }
            }
        } else {
            marker.setVisibility(GONE);
        }
    }

    private void renderSelect(State state) {
        if (state == State.SELECT) {
            selectedBackground.setVisibility(VISIBLE);
            dateTv.setTextColor(Color.parseColor("#f4511e"));
        } else if (state == State.NEXT_MONTH || state == State.PAST_MONTH) {
            selectedBackground.setVisibility(GONE);
            dateTv.setTextColor(Color.parseColor("#afbfff"));
        } else {
            selectedBackground.setVisibility(GONE);
            dateTv.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void renderToday(CalendarDate date) {
        if (date != null) {
            if (date.equals(today)) {
                dateTv.setText(new StringBuilder().append(String.valueOf(date.day)));
                dateTv.setTextColor(Color.parseColor("#f4511e"));
                todayBackground.setVisibility(VISIBLE);
            } else {
                dateTv.setText(new StringBuilder().append(String.valueOf(date.day)));
                todayBackground.setVisibility(GONE);
            }
        }
    }

    @Override
    public IDayRenderer copy() {
        return new CustomDayView(context, layoutResource);
    }
}
