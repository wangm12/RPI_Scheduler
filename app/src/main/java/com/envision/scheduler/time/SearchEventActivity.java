package com.envision.scheduler.time;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.envision.calendar.Utils;
import com.envision.calendar.component.CalendarAttr;
import com.envision.calendar.component.CalendarViewAdapter;
import com.envision.calendar.interf.OnSelectDateListener;
import com.envision.calendar.model.CalendarDate;
import com.envision.calendar.view.Calendar;
import com.envision.calendar.view.MonthPager;
import com.envision.scheduler.Constants;
import com.envision.scheduler.R;
import com.envision.scheduler.beans.EventBean;
import com.envision.scheduler.widget.CustomDayView;
import com.envision.timepickerdialog.TimePickerDialog;
import com.envision.timepickerdialog.data.Type;
import com.envision.timepickerdialog.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.envision.scheduler.Constants.getEventBean;


/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 29/3/2018 下午12:12
 */
public class SearchEventActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_current_date;
    private TextView tv_start_time;
    private TextView tv_end_time;
    private SearchEventAdapter adapter;
    SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH);
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

    long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
    long currentTimeMillis = System.currentTimeMillis();
    long afterTwoHourTimeMillis = currentTimeMillis + 2 * 60 * 60 * 1000;
    private List<EventBean> data;
    private TextView tv_date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_time);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getIntent().getStringExtra(Constants.KEY_TITLE));
        }
        tv_current_date = findViewById(R.id.tv_current_date);
        tv_start_time = findViewById(R.id.tv_start_time);
        tv_end_time = findViewById(R.id.tv_end_time);

        String weekday = weekdayNameFormat.format(currentTimeMillis);
        tv_current_date.setText(weekday);
        tv_current_date.setOnClickListener(this);
        tv_start_time.setOnClickListener(this);
        tv_start_time.setTag(currentTimeMillis);

        tv_end_time.setOnClickListener(this);
        tv_end_time.setTag(afterTwoHourTimeMillis);

        tv_start_time.setText(String.format("Start Time\n%s", timeFormat.format(currentTimeMillis)));
        tv_end_time.setText(String.format("End Time\n%s", timeFormat.format(afterTwoHourTimeMillis)));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = getEventBean((Long) tv_end_time.getTag());
        adapter = new SearchEventAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_current_date:
                if (window != null && window.isShowing()) {
                    window.dismiss();
                } else {
                    showDateDialog();
                }
                break;
            case R.id.tv_start_time:
                long minStart = (long) v.getTag() - tenYears;
                long maxStart = tv_end_time.getTag() == null ? (long) v.getTag() + tenYears : (long) tv_end_time.getTag();
                showTimeDialog(v,"Start Time", minStart, maxStart, (long) v.getTag());
                break;
            case R.id.tv_end_time:
                long minEnd = tv_start_time.getTag() == null ? (long) v.getTag() - tenYears : (long) tv_start_time.getTag();
                long maxEnd = (long) v.getTag() + tenYears;
                showTimeDialog(v,"End Time", minEnd, maxEnd, (long) v.getTag());
                break;
        }
    }

    private void showTimeDialog(final View view, String label, long minMillseconds, long maxMillseconds, long currentMillseconds) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.ENGLISH);

        Log.e("6666","min : " +  format.format(new Date(minMillseconds)));
        Log.e("6666","max : " +  format.format(new Date(maxMillseconds)));
        Log.e("6666","curr : " +  format.format(new Date(currentMillseconds)));
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        view.setTag(millseconds);
                        switch (view.getId()) {
                            case R.id.tv_start_time:
                                ((TextView)view).setText(String.format("Start Time\n%s", timeFormat.format(millseconds)));
                                break;
                            case R.id.tv_end_time:
                                ((TextView)view).setText(String.format("End Time\n%s", timeFormat.format(millseconds)));
                                data.clear();
                                //System.out.println(millseconds);
                                data.addAll(getEventBean(millseconds));
                                adapter.notifyDataSetChanged();
                                break;
                        }
                    }
                })
                .setCancelStringId("CANCEL")
                .setSureStringId("OK")
                .setTitleStringId(label)
                .setYearText("")
                .setMonthText("")
                .setDayText("")
                .setHourText("")
                .setMinuteText("")
                .setCyclic(false)
                .setMinMillseconds(minMillseconds)
                .setMaxMillseconds(maxMillseconds)
                .setCurrentMillseconds(currentMillseconds)
                .setBackgroundColor(getResources().getColor(R.color.white))
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.deep_orange_600))
                .setWheelItemTextSize(14)
                .build();
        mDialogAll.show(getSupportFragmentManager(), "all");
    }

    @Override
    public void onBackPressed() {
        if (window != null && window.isShowing()) {
            window.dismiss();
        } else {
            finish();
        }
    }

    private PopupWindow window;
    private MonthPager monthPager;
    private CalendarViewAdapter calendarAdapter;

    private void showDateDialog() {
        if (window == null) {
            window = new PopupWindow(this);
            window.setBackgroundDrawable(null);
            @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_select_date, null);
            monthPager = view.findViewById(R.id.calendar_view);
            tv_date = view.findViewById(R.id.tv_date);
            //此处强行setViewHeight，毕竟你知道你的日历牌的高度
            monthPager.setViewHeight(Utils.dpi2px(this, 270));
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            window.setWidth(metrics.widthPixels);
            initCalendarView();
            window.setContentView(view);
            CalendarDate today = new CalendarDate();
            String month = today.getMonth() < 10 ? "0"+today.getMonth():""+today.getMonth();
            tv_date.setText(String.format(Locale.ENGLISH, "%s / %d", month, today.getYear()));
            calendarAdapter.notifyDataChanged(today);
            window.setOutsideTouchable(true);
            window.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        }

        window.showAsDropDown(findViewById(R.id.tv_current_date));
//        backgroundAlpha(0.5f);
    }

//    // 设置屏幕透明度
//    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = bgAlpha; // 0.0~1.0
//        getWindow().setAttributes(lp);
//    }

    /**
     * 初始化CustomDayView，并作为CalendarViewAdapter的参数传入
     */
    private void initCalendarView() {
        OnSelectDateListener onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
//                refreshClickDate(date);
                if (tv_date != null) {
                    String month = date.getMonth() < 10 ? "0"+date.getMonth():""+date.getMonth();
                    tv_date.setText(String.format(Locale.ENGLISH, "%s / %d", month, date.getYear()));
                }
                tv_current_date.setText(weekdayNameFormat.format(Utils.getDateFromString(date.getYear(), date.getMonth(), date.getDay())));
                if (window != null) {
                    window.dismiss();
                }
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
                monthPager.selectOtherMonth(offset);
            }
        };
        CustomDayView customDayView = new CustomDayView(this, R.layout.custom_day);
        calendarAdapter = new CalendarViewAdapter(
                this,
                onSelectDateListener,
                CalendarAttr.WeekArrayType.Monday,
                customDayView);
        calendarAdapter.setOnCalendarTypeChangedListener(new CalendarViewAdapter.OnCalendarTypeChanged() {
            @Override
            public void onCalendarTypeChanged(CalendarAttr.CalendarType type) {
//                rvToDoList.scrollToPosition(0);
            }
        });

        monthPager.setAdapter(calendarAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ArrayList<Calendar> currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) != null) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    if (tv_date != null) {
                        String month = date.getMonth() < 10 ? "0"+date.getMonth():""+date.getMonth();
                        tv_date.setText(String.format(Locale.ENGLISH, "%s / %d", month, date.getYear()));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
