package com.envision.scheduler.facilities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.envision.calendar.Utils;
import com.envision.calendar.component.CalendarAttr;
import com.envision.calendar.component.CalendarViewAdapter;
import com.envision.calendar.interf.OnSelectDateListener;
import com.envision.calendar.model.CalendarDate;
import com.envision.calendar.view.MonthPager;
import com.envision.scheduler.Constants;
import com.envision.scheduler.R;
import com.envision.scheduler.beans.Event;
import com.envision.scheduler.events.EventDetailActivity;
import com.envision.scheduler.widget.CustomDayView;
import com.envision.weekview.DateTimeInterpreter;
import com.envision.weekview.MonthLoader;
import com.envision.weekview.WeekView;
import com.envision.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * TODO
 *
 * @author mj
 * @version V 1.4
 * @date 29/3/2018 上午1:08
 */
public class FacilityDetailActivity extends AppCompatActivity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private PopupWindow window;
    private WeekView mWeekView;
    private MonthPager monthPager;
    private String facilityName = "";
    private CalendarViewAdapter calendarAdapter;
    private List<WeekViewEvent> events = new ArrayList<>();
    private TextView tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        facilityName = getIntent().getStringExtra(Constants.KEY_TITLE);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(facilityName);
        }

        spinner = findViewById(R.id.sp_facility);
        int array = getIntent().getIntExtra(Constants.KEY_FACILITIES, 0);
        if (array == 0) {
            spinner.setVisibility(View.GONE);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array
                , R.layout.item_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mWeekView = findViewById(R.id.weekView);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(java.util.Calendar date) {
                SimpleDateFormat format = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.ENGLISH);
                return format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Event realEvent = null;
        if (event.getData() instanceof Event) {
            realEvent = (Event) event.getData();
        }
        if (realEvent != null) {
            Intent intent = new Intent(FacilityDetailActivity.this, EventDetailActivity.class);
            intent.putExtra(Constants.KEY_TITLE, realEvent.getName());
            int startHour = event.getStartTime().get(Calendar.HOUR_OF_DAY);
            int endHour = event.getEndTime().get(Calendar.HOUR_OF_DAY);
            String startH = startHour > 11 ? (startHour - 12) + " PM" : (startHour == 0 ? "12 AM" : startHour + " AM");
            String endH = endHour > 11 ? (endHour - 12) + " PM" : (endHour == 0 ? "12 AM" : endHour + " AM");
            String time_date = startH + " --- " + endH;
            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMM dd'th', yyyy", Locale.ENGLISH);
            intent.putExtra(Constants.KEY_TIME_DATE, time_date + "\n" + format.format(event.getStartTime().getTime()));
            intent.putExtra(Constants.KEY_LOCATION, facilityName + "\n" + spinner.getSelectedItem().toString());
            String groupName = event.getGroupName();
            //System.out.println(groupName);
            intent.putExtra(Constants.KEY_GROUP, groupName);
            intent.putExtra(Constants.KEY_EVENTS_COORDINATOR, "John Sturman");
            startActivity(intent);
        }
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> matchedEvents = new ArrayList<>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        events.clear();
        String facility_name = (String) spinner.getSelectedItem();
        if (facility_name.equals("East Gym")) {
            Event event29 = new Event();
            String randomRGB29 = Constants.getRandomColorRGB();
            event29.setColor("#d0" + randomRGB29);
            event29.setTextColor("#d0424242");
            event29.setMonth(3);
            event29.setDayOfMonth(26);
            event29.setName("ASCE Concrete Canoe & Steel Bridge Competition Dinners");
            event29.setStartTime(21 + ":" + 00);
            event29.setEndTime(22 + ":" + 00);
            event29.setGroupName("American Society of Civil Engineers (ASCE)");
            events.add(event29.toWeekViewEvent());

            Event event41 = new Event();
            String randomRGB41 = Constants.getRandomColorRGB();
            event41.setColor("#d0" + randomRGB41);
            event41.setTextColor("#d0424242");
            event41.setMonth(3);
            event41.setDayOfMonth(26);
            event41.setName("ASCE Concrete Canoe & Steel Bridge Competition Dinners");
            event41.setStartTime(19 + ":" + 00);
            event41.setEndTime(21 + ":" + 00);
            event41.setGroupName("American Society of Civil Engineers (ASCE)");
            events.add(event41.toWeekViewEvent());

            Event event99 = new Event();
            String randomRGB99 = Constants.getRandomColorRGB();
            event99.setColor("#d0" + randomRGB99);
            event99.setTextColor("#d0424242");
            event99.setMonth(3);
            event99.setDayOfMonth(26);
            event99.setName("ASCE Concrete Canoe & Steel Bridge Competition Dinners");
            event99.setStartTime(18 + ":" + 00);
            event99.setEndTime(19 + ":" + 00);
            event99.setGroupName("American Society of Civil Engineers (ASCE)");
            events.add(event99.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Ballroom Team Practice");
            event1.setStartTime(18 + ":" + 00);
            event1.setEndTime(20 + ":" + 30);
            event1.setGroupName("Ballroom Dance Club");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB1 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB1);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(23);
            event2.setName("Men's Soccer Practice");
            event2.setStartTime(04 + ":" + 30);
            event2.setEndTime(06 + ":" + 30);
            event2.setGroupName("RPI Men's Soccer");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB2);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(23);
            event3.setName("Intramural Wiffleball");
            event3.setStartTime(21 + ":" + 00);
            event3.setEndTime(23 + ":" + 00);
            event3.setGroupName("Intramural Sports");
            events.add(event3.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Badminton Club Practice");
            event21_1.setStartTime(10 + ":" + 30);
            event21_1.setEndTime(12 + ":" + 30);
            event21_1.setGroupName("Badminton Club");
            events.add(event1.toWeekViewEvent());

            Event event21_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Humans Versus Zombies");
            event21_2.setStartTime(14 + ":" + 00);
            event21_2.setEndTime(17 + ":" + 00);
            event21_2.setGroupName("Humans vs Zombies");
            events.add(event21_2.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Badminton Club practice");
            event11.setStartTime(19 + ":" + 00);
            event11.setEndTime(22 + ":" + 00);
            event11.setGroupName("Badminton Club");
            events.add(event11.toWeekViewEvent());

            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Intramural Wiffleball");
            event22_1.setStartTime(18 + ":" + 00);
            event22_1.setEndTime(23 + ":" + 00);
            event22_1.setGroupName("Intramural Sports");
            events.add(event22_1.toWeekViewEvent());

            event29 = new Event();
            randomRGB29 = Constants.getRandomColorRGB();
            event29.setColor("#d0" + randomRGB29);
            event29.setTextColor("#d0424242");
            event29.setMonth(3);
            event29.setDayOfMonth(19);
            event29.setName("ASCE Concrete Canoe & Steel Bridge Competition Dinners");
            event29.setStartTime(21 + ":" + 00);
            event29.setEndTime(22 + ":" + 00);
            event29.setGroupName("American Society of Civil Engineers (ASCE)");
            events.add(event29.toWeekViewEvent());

            event41 = new Event();
            randomRGB41 = Constants.getRandomColorRGB();
            event41.setColor("#d0" + randomRGB41);
            event41.setTextColor("#d0424242");
            event41.setMonth(3);
            event41.setDayOfMonth(19);
            event41.setName("ASCE Concrete Canoe & Steel Bridge Competition Dinners");
            event41.setStartTime(19 + ":" + 00);
            event41.setEndTime(21 + ":" + 00);
            event41.setGroupName("American Society of Civil Engineers (ASCE)");
            events.add(event41.toWeekViewEvent());

            event99 = new Event();
            randomRGB99 = Constants.getRandomColorRGB();
            event99.setColor("#d0" + randomRGB99);
            event99.setTextColor("#d0424242");
            event99.setMonth(3);
            event99.setDayOfMonth(19);
            event99.setName("ASCE Concrete Canoe & Steel Bridge Competition Dinners");
            event99.setStartTime(18 + ":" + 00);
            event99.setEndTime(19 + ":" + 00);
            event99.setGroupName("American Society of Civil Engineers (ASCE)");
            events.add(event99.toWeekViewEvent());

            event1 = new Event();
            randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(18);
            event1.setName("Ballroom Team Practice");
            event1.setStartTime(18 + ":" + 00);
            event1.setEndTime(20 + ":" + 30);
            event1.setGroupName("Ballroom Dance Club");
            events.add(event1.toWeekViewEvent());

            event2 = new Event();
            randomRGB1 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB1);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(16);
            event2.setName("Men's Soccer Practice");
            event2.setStartTime(04 + ":" + 30);
            event2.setEndTime(06 + ":" + 30);
            event2.setGroupName("RPI Men's Soccer");
            events.add(event2.toWeekViewEvent());

            event3 = new Event();
            randomRGB2 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB2);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(16);
            event3.setName("Intramural Wiffleball");
            event3.setStartTime(21 + ":" + 00);
            event3.setEndTime(23 + ":" + 00);
            event3.setGroupName("Intramural Sports");
            events.add(event3.toWeekViewEvent());

            event21_1 = new Event();
            randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Badminton Club Practice");
            event21_1.setStartTime(10 + ":" + 30);
            event21_1.setEndTime(12 + ":" + 30);
            event21_1.setGroupName("Badminton Club");
            events.add(event1.toWeekViewEvent());

            event21_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Humans Versus Zombies");
            event21_2.setStartTime(14 + ":" + 00);
            event21_2.setEndTime(17 + ":" + 00);
            event21_2.setGroupName("Humans vs Zombies");
            events.add(event21_2.toWeekViewEvent());

            event11 = new Event();
            randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(17);
            event11.setName("Badminton Club practice");
            event11.setStartTime(19 + ":" + 00);
            event11.setEndTime(22 + ":" + 00);
            event11.setGroupName("Badminton Club");
            events.add(event11.toWeekViewEvent());

            event22_1 = new Event();
            randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(15);
            event22_1.setName("Intramural Wiffleball");
            event22_1.setStartTime(18 + ":" + 00);
            event22_1.setEndTime(23 + ":" + 00);
            event22_1.setGroupName("Intramural Sports");
            events.add(event22_1.toWeekViewEvent());
        } else if (facility_name.equals("West Gym")) {

            Event event84 = new Event();
            String randomRGB84 = Constants.getRandomColorRGB();
            event84.setColor("#d0" + randomRGB84);
            event84.setTextColor("#d0424242");
            event84.setMonth(3);
            event84.setDayOfMonth(26);
            event84.setName("Cheerleading Practice");
            event84.setStartTime(19 + ":" + 00);
            event84.setEndTime(21 + ":" + 00);
            event84.setGroupName("Cheerleading");
            events.add(event84.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Juggling Clubs");
            event1.setStartTime(20 + ":" + 30);
            event1.setEndTime(22 + ":" + 30);
            event1.setGroupName("Juggling Club");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("ASCE Steel Bridge Practice");
            event2.setStartTime(16 + ":" + 00);
            event2.setEndTime(17 + ":" + 00);
            event2.setGroupName("American Society of Civil Engineers (ASCE)");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(23);
            event3.setName("Juggling Clubs");
            event3.setStartTime(20 + ":" + 30);
            event3.setEndTime(22 + ":" + 30);
            event3.setGroupName("Juggling Clubs");
            events.add(event3.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Practice");
            event21_1.setStartTime(14 + ":" + 00);
            event21_1.setEndTime(16 + ":" + 00);
            event21_1.setGroupName("Cheerleading");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Practice");
            event21_2.setStartTime(16 + ":" + 00);
            event21_2.setEndTime(18 + ":" + 00);
            event21_2.setGroupName("German Long Sword(HEMA)");
            events.add(event21_2.toWeekViewEvent());

            Event event21_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event21_3.setColor("#d0" + randomRGB21_3);
            event21_3.setTextColor("#d0424242");
            event21_3.setMonth(3);
            event21_3.setDayOfMonth(21);
            event21_3.setName("RPIgnite Practice");
            event21_3.setStartTime(12 + ":" + 00);
            event21_3.setEndTime(14 + ":" + 00);
            event21_3.setGroupName("RPIgnite");
            events.add(event21_3.toWeekViewEvent());

            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("RPIgnite Practice");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(14 + ":" + 00);
            event22_1.setGroupName("RPIgnite");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Intramural Floor Hockey");
            event22_2.setStartTime(16 + ":" + 00);
            event22_2.setEndTime(23 + ":" + 00);
            event22_2.setGroupName("Intramural Sports");
            events.add(event22_2.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Cheerleading Practice");
            event11.setStartTime(19 + ":" + 00);
            event11.setEndTime(21 + ":" + 00);
            event11.setGroupName("Cheerleading");
            events.add(event11.toWeekViewEvent());
        } else if (facility_name.equals("Auditorium")) {
            Event event21 = new Event();
            String randomRGB21 = Constants.getRandomColorRGB();
            event21.setColor("#d0" + randomRGB21);
            event21.setTextColor("#d0424242");
            event21.setMonth(3);
            event21.setDayOfMonth(27);
            event21.setName("Formal");
            event21.setStartTime(15 + ":" + 00);
            event21.setEndTime(23 + ":" + 30);
            event21.setGroupName("CRU");
            events.add(event21.toWeekViewEvent());

            Event event74 = new Event();
            String randomRGB74 = Constants.getRandomColorRGB();
            event74.setColor("#d0" + randomRGB74);
            event74.setTextColor("#d0424242");
            event74.setMonth(3);
            event74.setDayOfMonth(26);
            event74.setName("Eighth Wonder");
            event74.setStartTime(20 + ":" + 00);
            event74.setEndTime(22 + ":" + 00);
            event74.setGroupName("Eighth Wonder");
            events.add(event74.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Bayanihan");
            event1.setStartTime(16 + ":" + 00);
            event1.setEndTime(23 + ":" + 45);
            event1.setGroupName("Philippine American League");
            events.add(event1.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("STEP Program Hackathon");
            event21_1.setStartTime(16 + ":" + 00);
            event21_1.setEndTime(19 + ":" + 00);
            event21_1.setGroupName("Dean of Students Office");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Salsa Socail Dance");
            event21_2.setStartTime(19 + ":" + 00);
            event21_2.setEndTime(23 + ":" + 00);
            event21_2.setGroupName("Ballroom Dance Club");
            events.add(event21_2.toWeekViewEvent());

            Event event21_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event21_3.setColor("#d0" + randomRGB21_3);
            event21_3.setTextColor("#d0424242");
            event21_3.setMonth(3);
            event21_3.setDayOfMonth(21);
            event21_3.setName("STEP");
            event21_3.setStartTime(8 + ":" + 00);
            event21_3.setEndTime(16 + ":" + 00);
            event21_3.setGroupName("Dean of Students Office");
            events.add(event21_3.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Rensselrics Rehearsal");
            event11.setStartTime(20 + ":" + 00);
            event11.setEndTime(22 + ":" + 00);
            event11.setGroupName("Rensselyrics");
            events.add(event11.toWeekViewEvent());

            event21 = new Event();
            randomRGB21 = Constants.getRandomColorRGB();
            event21.setColor("#d0" + randomRGB21);
            event21.setTextColor("#d0424242");
            event21.setMonth(3);
            event21.setDayOfMonth(26);
            event21.setName("Lindy Hop Practice");
            event21.setStartTime(18 + ":" + 00);
            event21.setEndTime(20 + ":" + 00);
            event21.setGroupName("Lindy Hop");
            events.add(event21.toWeekViewEvent());

            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("FIP Study Hours	RU");
            event22_1.setStartTime(16 + ":" + 00);
            event22_1.setEndTime(18 + ":" + 00);
            event22_1.setGroupName("FYE (First Year Experience)");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("RPI Ballroom Movie Night");
            event22_2.setStartTime(18 + ":" + 00);
            event22_2.setEndTime(23 + ":" + 00);
            event22_2.setGroupName("Ballroom Dance Club");
            events.add(event22_2.toWeekViewEvent());
        } else if (facility_name.equals("Dance Studio")) {
            Event event77 = new Event();
            String randomRGB77 = Constants.getRandomColorRGB();
            event77.setColor("#d0" + randomRGB77);
            event77.setTextColor("#d0424242");
            event77.setMonth(3);
            event77.setDayOfMonth(27);
            event77.setName("Muslim Prayer");
            event77.setStartTime(12 + ":" + 00);
            event77.setEndTime(14 + ":" + 00);
            event77.setGroupName("Muslim Students Association");
            events.add(event77.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Ballroom Club Practice");
            event1.setStartTime(12 + ":" + 00);
            event1.setEndTime(14 + ":" + 00);
            event1.setGroupName("Ballroom Dance Club");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Lindy Hop Lessons");
            event2.setStartTime(18 + ":" + 00);
            event2.setEndTime(20 + ":" + 00);
            event2.setGroupName("Ballroom Dance Club");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Eighth Wonder");
            event3.setStartTime(20 + ":" + 00);
            event3.setEndTime(22 + ":" + 00);
            event3.setGroupName("Eighth Wonder");
            events.add(event3.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Ballroom Team Practice");
            event21_1.setStartTime(12 + ":" + 00);
            event21_1.setEndTime(16 + ":" + 00);
            event21_1.setGroupName("Ballroom Dance Club");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("CASA Fan Dance Rehearsal");
            event21_2.setStartTime(20 + ":" + 00);
            event21_2.setEndTime(22 + ":" + 00);
            event21_2.setGroupName("CASA");
            events.add(event21_2.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(23);
            event4.setName("Ballroom Club Practice");
            event4.setStartTime(17 + ":" + 30);
            event4.setEndTime(22 + ":" + 00);
            event4.setGroupName("Ballroom Dance Club");
            events.add(event4.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Ballroom Club Lessons");
            event11.setStartTime(15 + ":" + 00);
            event11.setEndTime(22 + ":" + 00);
            event11.setGroupName("Ballroom Dance Club");
            events.add(event11.toWeekViewEvent());


            Event event98 = new Event();
            String randomRGB98 = Constants.getRandomColorRGB();
            event98.setColor("#d0" + randomRGB98);
            event98.setTextColor("#d0424242");
            event98.setMonth(3);
            event98.setDayOfMonth(26);
            event98.setName("Ballroom Team Practice");
            event98.setStartTime(15 + ":" + 00);
            event98.setEndTime(21 + ":" + 30);
            event98.setGroupName("Ballroom Dance Club");
            events.add(event98.toWeekViewEvent());

            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Ballroom Team Practice");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(15 + ":" + 00);
            event22_1.setGroupName("Ballroom Dance Club");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Ballroom Team Lessons");
            event22_2.setStartTime(15 + ":" + 00);
            event22_2.setEndTime(18 + ":" + 00);
            event22_2.setGroupName("Ballroom Dance Club");
            events.add(event22_2.toWeekViewEvent());

            Event event22_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event22_3.setColor("#d0" + randomRGB21_3);
            event22_3.setTextColor("#d0424242");
            event22_3.setMonth(3);
            event22_3.setDayOfMonth(21);
            event22_3.setName("CASA Fan Dance Rehearsal");
            event22_3.setStartTime(18 + ":" + 00);
            event22_3.setEndTime(20 + ":" + 00);
            event22_3.setGroupName("CASA");
            events.add(event22_3.toWeekViewEvent());
        } else if (facility_name.equals("Basement")) {
            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Table Tennis Practice");
            event21_1.setStartTime(12 + ":" + 00);
            event21_1.setEndTime(16 + ":" + 00);
            event21_1.setGroupName("RU Club Table Tennis");
            events.add(event21_1.toWeekViewEvent());

            Event event98 = new Event();
            String randomRGB98 = Constants.getRandomColorRGB();
            event98.setColor("#d0" + randomRGB98);
            event98.setTextColor("#d0424242");
            event98.setMonth(3);
            event98.setDayOfMonth(27);
            event98.setName("Isshinryu Karate Klub");
            event98.setStartTime(18 + ":" + 00);
            event98.setEndTime(20 + ":" + 00);
            event98.setGroupName("Isshinryu Karate");
            events.add(event98.toWeekViewEvent());

            Event event23 = new Event();
            String randomRGB23 = Constants.getRandomColorRGB();
            event23.setColor("#d0" + randomRGB23);
            event23.setTextColor("#d0424242");
            event23.setMonth(3);
            event23.setDayOfMonth(27);
            event23.setName("Table Tennis Practice");
            event23.setStartTime(15 + ":" + 00);
            event23.setEndTime(18 + ":" + 00);
            event23.setGroupName("RU Club Table Tennis");
            events.add(event23.toWeekViewEvent());

            Event event29 = new Event();
            String randomRGB29 = Constants.getRandomColorRGB();
            event29.setColor("#d0" + randomRGB29);
            event29.setTextColor("#d0424242");
            event29.setMonth(3);
            event29.setDayOfMonth(26);
            event29.setName("Fencing");
            event29.setStartTime(20 + ":" + 00);
            event29.setEndTime(23 + ":" + 00);
            event29.setGroupName("RU Club Fencing");
            events.add(event29.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("ChemE Car Testing");
            event1.setStartTime(10 + ":" + 00);
            event1.setEndTime(18 + ":" + 00);
            event1.setGroupName("AIChE");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Isshinryu Karate Klub");
            event2.setStartTime(18 + ":" + 00);
            event2.setEndTime(20 + ":" + 00);
            event2.setGroupName("Isshinryu Karate");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Table Tennis Practice");
            event3.setStartTime(20 + ":" + 00);
            event3.setEndTime(23 + ":" + 00);
            event3.setGroupName("RU Club Table Tennis");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(23);
            event4.setName("Isshinryu Karate Klub");
            event4.setStartTime(18 + ":" + 00);
            event4.setEndTime(20 + ":" + 00);
            event4.setGroupName("Isshinryu Karate");
            events.add(event4.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Fencing");
            event11.setStartTime(20 + ":" + 00);
            event11.setEndTime(23 + ":" + 00);
            event11.setGroupName("Club Fencing");
            events.add(event11.toWeekViewEvent());
        } else if (facility_name.equals("Court 1")) {
            Event event76 = new Event();
            String randomRGB76 = Constants.getRandomColorRGB();
            event76.setColor("#d0" + randomRGB76);
            event76.setTextColor("#d0424242");
            event76.setMonth(3);
            event76.setDayOfMonth(26);
            event76.setName("Intramural Handball");
            event76.setStartTime(21 + ":" + 00);
            event76.setEndTime(23 + ":" + 00);
            event76.setGroupName("Intramural Sports");
            events.add(event76.toWeekViewEvent());

            Event event77 = new Event();
            String randomRGB77 = Constants.getRandomColorRGB();
            event77.setColor("#d0" + randomRGB77);
            event77.setTextColor("#d0424242");
            event77.setMonth(3);
            event77.setDayOfMonth(26);
            event77.setName("NROTC Color Guard Practice");
            event77.setStartTime(18 + ":" + 00);
            event77.setEndTime(19 + ":" + 00);
            event77.setGroupName("Navy ROTC");
            events.add(event77.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Color Guard Practice");
            event1.setStartTime(20 + ":" + 00);
            event1.setEndTime(21 + ":" + 00);
            event1.setGroupName("Navy ROTC");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Intramural Indoor Soccer");
            event2.setStartTime(21 + ":" + 00);
            event2.setEndTime(23 + ":" + 00);
            event2.setGroupName("Intramural Sports");
            events.add(event2.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(23);
            event4.setName("NROTC Color Guard Practice");
            event4.setStartTime(18 + ":" + 00);
            event4.setEndTime(19 + ":" + 00);
            event4.setGroupName("Navy ROTC");
            events.add(event4.toWeekViewEvent());

            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Intramural Handball");
            event22_1.setStartTime(17 + ":" + 30);
            event22_1.setEndTime(21 + ":" + 00);
            event22_1.setGroupName("Intramural Sports");
            events.add(event22_1.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Intramural Handball");
            event11.setStartTime(21 + ":" + 00);
            event11.setEndTime(23 + ":" + 00);
            event11.setGroupName("Intramural Sports");
            events.add(event11.toWeekViewEvent());
        } else if (facility_name.equals("Court 2")) {

        } else if (facility_name.equals("Court 3")) {
            Event event73 = new Event();
            String randomRGB73 = Constants.getRandomColorRGB();
            event73.setColor("#d0" + randomRGB73);
            event73.setTextColor("#d0424242");
            event73.setMonth(3);
            event73.setDayOfMonth(26);
            event73.setName("Intramural Handball");
            event73.setStartTime(21 + ":" + 00);
            event73.setEndTime(23 + ":" + 00);
            event73.setGroupName("Intramural Sports");
            events.add(event73.toWeekViewEvent());
            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(23);
            event4.setName("Intramural Handball");
            event4.setStartTime(21 + ":" + 00);
            event4.setEndTime(23 + ":" + 00);
            event4.setGroupName("Intramural Sports");
            events.add(event4.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Intramural Handball");
            event11.setStartTime(21 + ":" + 00);
            event11.setEndTime(23 + ":" + 00);
            event11.setGroupName("Intramural Sports");
            events.add(event11.toWeekViewEvent());
        } else if (facility_name.equals("Track")) {
            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("GM Week Picnic (Rain Location)");
            event1.setStartTime(11 + ":" + 00);
            event1.setEndTime(15 + ":" + 00);
            event1.setGroupName("GM Week");
            events.add(event1.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Design Your Future Day 2018");
            event21_1.setStartTime(8 + ":" + 00);
            event21_1.setEndTime(23 + ":" + 59);
            event21_1.setGroupName("SWE (Society of Women Engineers)");
            events.add(event21_1.toWeekViewEvent());


            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(23);
            event4.setName("Track & Field Practice");
            event4.setStartTime(16 + ":" + 00);
            event4.setEndTime(18 + ":" + 00);
            event4.setGroupName("RPI Track & Field");
            events.add(event4.toWeekViewEvent());

            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Design Your Future Day 2018");
            event22_1.setStartTime(00 + ":" + 00);
            event22_1.setEndTime(17 + ":" + 30);
            event22_1.setGroupName("SWE (Society of Women Engineers)");
            events.add(event22_1.toWeekViewEvent());
        } else if (facility_name.equals("Robison Pool - Diving")) {
            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("RPI Swimming Swim Lessons");
            event21_1.setStartTime(8 + ":" + 00);
            event21_1.setEndTime(12 + ":" + 00);
            event21_1.setGroupName("RPI Swimming & Diving");
            events.add(event21_1.toWeekViewEvent());
        } else if (facility_name.equals("Robison Pool - Swimming")) {
            Event event92 = new Event();
            String randomRGB92 = Constants.getRandomColorRGB();
            event92.setColor("#d0" + randomRGB92);
            event92.setTextColor("#d0424242");
            event92.setMonth(3);
            event92.setDayOfMonth(27);
            event92.setName("Lifeguard Training - Full Course");
            event92.setStartTime(16 + ":" + 00);
            event92.setEndTime(20 + ":" + 00);
            event92.setGroupName("RPI Swimming & Diving");
            events.add(event92.toWeekViewEvent());

            Event event84 = new Event();
            String randomRGB84 = Constants.getRandomColorRGB();
            event84.setColor("#d0" + randomRGB84);
            event84.setTextColor("#d0424242");
            event84.setMonth(3);
            event84.setDayOfMonth(26);
            event84.setName("Kayak Practice");
            event84.setStartTime(22 + ":" + 00);
            event84.setEndTime(23 + ":" + 30);
            event84.setGroupName("RU Club Outing");
            events.add(event84.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("RPI Swim Club Practice");
            event1.setStartTime(22 + ":" + 00);
            event1.setEndTime(23 + ":" + 15);
            event1.setGroupName("Swim Club");
            events.add(event1.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("RPI Swimming Swim Lessons");
            event21_1.setStartTime(8 + ":" + 00);
            event21_1.setEndTime(12 + ":" + 00);
            event21_1.setGroupName("RPI Swimming & Diving");
            events.add(event21_1.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(23);
            event4.setName("RPI Swim Club Practice");
            event4.setStartTime(22 + ":" + 00);
            event4.setEndTime(23 + ":" + 15);
            event4.setGroupName("Swim Club");
            events.add(event4.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Kayak Practice");
            event11.setStartTime(22 + ":" + 00);
            event11.setEndTime(23 + ":" + 30);
            event11.setGroupName("Club Outing");
            events.add(event11.toWeekViewEvent());
        } else if (facility_name.equals("3202")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("40th NSBE/SHPE Career Fair");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(14 + ":" + 00);
            event22_1.setGroupName("Society of Hispanic Engineers");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("coding&&community Eboard meeting");
            event22_2.setStartTime(14 + ":" + 00);
            event22_2.setEndTime(15 + ":" + 00);
            event22_2.setGroupName("Community Service");
            events.add(event22_2.toWeekViewEvent());

            Event event22_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event22_3.setColor("#d0" + randomRGB21_3);
            event22_3.setTextColor("#d0424242");
            event22_3.setMonth(3);
            event22_3.setDayOfMonth(21);
            event22_3.setName("coding&&community GBM");
            event22_3.setStartTime(15 + ":" + 00);
            event22_3.setEndTime(17 + ":" + 00);
            event22_3.setGroupName("Community Service");
            events.add(event22_3.toWeekViewEvent());

            Event event22_4 = new Event();
            String randomRGB21_4 = Constants.getRandomColorRGB();
            event22_4.setColor("#d0" + randomRGB21_4);
            event22_4.setTextColor("#d0424242");
            event22_4.setMonth(3);
            event22_4.setDayOfMonth(21);
            event22_4.setName("Psi Upsilon Chapter Meeting");
            event22_4.setStartTime(18 + ":" + 00);
            event22_4.setEndTime(21 + ":" + 00);
            event22_4.setGroupName("Psi Upsilon");
            events.add(event22_4.toWeekViewEvent());

            Event event58 = new Event();
            String randomRGB58 = Constants.getRandomColorRGB();
            event58.setColor("#d0" + randomRGB58);
            event58.setTextColor("#d0424242");
            event58.setMonth(3);
            event58.setDayOfMonth(27);
            event58.setName("Bible study");
            event58.setStartTime(21 + ":" + 00);
            event58.setEndTime(21 + ":" + 30);
            event58.setGroupName("Chinese Christian Fellowship");
            events.add(event58.toWeekViewEvent());

            Event event54 = new Event();
            String randomRGB54 = Constants.getRandomColorRGB();
            event54.setColor("#d0" + randomRGB54);
            event54.setTextColor("#d0424242");
            event54.setMonth(3);
            event54.setDayOfMonth(27);
            event54.setName("Chinese Christian Fellowship Bible Study");
            event54.setStartTime(18 + ":" + 00);
            event54.setEndTime(21 + ":" + 00);
            event54.setGroupName("General Non-Club Meetings");
            events.add(event54.toWeekViewEvent());

            Event event73 = new Event();
            String randomRGB73 = Constants.getRandomColorRGB();
            event73.setColor("#d0" + randomRGB73);
            event73.setTextColor("#d0424242");
            event73.setMonth(3);
            event73.setDayOfMonth(27);
            event73.setName("Terra Cafe E-Board Meeting");
            event73.setStartTime(17 + ":" + 00);
            event73.setEndTime(18 + ":" + 00);
            event73.setGroupName("TerraCafe");
            events.add(event73.toWeekViewEvent());

            Event event45 = new Event();
            String randomRGB45 = Constants.getRandomColorRGB();
            event45.setColor("#d0" + randomRGB45);
            event45.setTextColor("#d0424242");
            event45.setMonth(3);
            event45.setDayOfMonth(27);
            event45.setName("Center for Financial Studies - 2018 Workshop");
            event45.setStartTime(8 + ":" + 00);
            event45.setEndTime(17 + ":" + 00);
            event45.setGroupName("Lally School of Management");
            events.add(event45.toWeekViewEvent());

            Event event89 = new Event();
            String randomRGB89 = Constants.getRandomColorRGB();
            event89.setColor("#d0" + randomRGB89);
            event89.setTextColor("#d0424242");
            event89.setMonth(3);
            event89.setDayOfMonth(26);
            event89.setName("UPAC Lights Meeting");
            event89.setStartTime(21 + ":" + 00);
            event89.setEndTime(23 + ":" + 00);
            event89.setGroupName("UPAC Lights");
            events.add(event89.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("We R Gold");
            event1.setStartTime(15 + ":" + 00);
            event1.setEndTime(16 + ":" + 30);
            event1.setGroupName("Rensselaer Union Administration Office");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("MA Officer Meeting");
            event2.setStartTime(16 + ":" + 30);
            event2.setEndTime(17 + ":" + 30);
            event2.setGroupName("Material Advantage");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("RPI Dance Team Eboard Meeting");
            event3.setStartTime(19 + ":" + 00);
            event3.setEndTime(20 + ":" + 00);
            event3.setGroupName("Dance Team");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(25);
            event4.setName("Colleges Against Cancer");
            event4.setStartTime(20 + ":" + 00);
            event4.setEndTime(21 + ":" + 00);
            event4.setGroupName("Colleges Against Cancer");
            events.add(event4.toWeekViewEvent());

            Event event5 = new Event();
            String randomRGB5 = Constants.getRandomColorRGB();
            event5.setColor("#d0" + randomRGB5);
            event5.setTextColor("#d0424242");
            event5.setMonth(3);
            event5.setDayOfMonth(25);
            event5.setName("RPI Flying Club General Body Meeting");
            event5.setStartTime(21 + ":" + 00);
            event5.setEndTime(22 + ":" + 00);
            event5.setGroupName("RPI Flying Club");
            events.add(event5.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("RPI Gaming Club");
            event21_1.setStartTime(12 + ":" + 00);
            event21_1.setEndTime(23 + ":" + 59);
            event21_1.setGroupName("Gaming Club");
            events.add(event21_1.toWeekViewEvent());

            Event event6 = new Event();
            String randomRGB6 = Constants.getRandomColorRGB();
            event6.setColor("#d0" + randomRGB6);
            event6.setTextColor("#d0424242");
            event6.setMonth(3);
            event6.setDayOfMonth(23);
            event6.setName("FIT");
            event6.setStartTime(13 + ":" + 30);
            event6.setEndTime(14 + ":" + 00);
            event6.setGroupName("FYE (First Year Experience)");
            events.add(event6.toWeekViewEvent());

            Event event7 = new Event();
            String randomRGB7 = Constants.getRandomColorRGB();
            event7.setColor("#d0" + randomRGB7);
            event7.setTextColor("#d0424242");
            event7.setMonth(3);
            event7.setDayOfMonth(23);
            event7.setName("GBM");
            event7.setStartTime(18 + ":" + 00);
            event7.setEndTime(19 + ":" + 00);
            event7.setGroupName("Japanese Cultural Association");
            events.add(event7.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("BMES E-Board Meeting");
            event8.setStartTime(19 + ":" + 00);
            event8.setEndTime(21 + ":" + 00);
            event8.setGroupName("Biomedical Engineering Society");
            events.add(event8.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("MLC Interviews");
            event11.setStartTime(9 + ":" + 00);
            event11.setEndTime(17 + ":" + 30);
            event11.setGroupName("Multicultural & Diversity Programs and Initiatives");
            events.add(event11.toWeekViewEvent());

            Event event98 = new Event();
            String randomRGB98 = Constants.getRandomColorRGB();
            event98.setColor("#d0" + randomRGB98);
            event98.setTextColor("#d0424242");
            event98.setMonth(3);
            event98.setDayOfMonth(26);
            event98.setName("PAL E-Board Meeting");
            event98.setStartTime(19 + ":" + 00);
            event98.setEndTime(20 + ":" + 30);
            event98.setGroupName("Philippine American League");
            events.add(event98.toWeekViewEvent());

            Event event70 = new Event();
            String randomRGB70 = Constants.getRandomColorRGB();
            event70.setColor("#d0" + randomRGB70);
            event70.setTextColor("#d0424242");
            event70.setMonth(3);
            event70.setDayOfMonth(26);
            event70.setName("KSA GBM");
            event70.setStartTime(18 + ":" + 00);
            event70.setEndTime(19 + ":" + 00);
            event70.setGroupName("Korean Students Association");
            events.add(event70.toWeekViewEvent());
        } else if (facility_name.equals("3510")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Psi Upsilon Chapter Meeting");
            event22_1.setStartTime(10 + ":" + 00);
            event22_1.setEndTime(12 + ":" + 00);
            event22_1.setGroupName("Psi Upsilon");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Phi Kappa Theta Trustees Meeting");
            event22_2.setStartTime(12 + ":" + 00);
            event22_2.setEndTime(14 + ":" + 00);
            event22_2.setGroupName("Phi Kappa Theta");
            events.add(event22_2.toWeekViewEvent());

            Event event22_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event22_3.setColor("#d0" + randomRGB21_3);
            event22_3.setTextColor("#d0424242");
            event22_3.setMonth(3);
            event22_3.setDayOfMonth(21);
            event22_3.setName("Bylaws Meeting");
            event22_3.setStartTime(15 + ":" + 00);
            event22_3.setEndTime(16 + ":" + 00);
            event22_3.setGroupName("SWE (Society of Women Engineers)");
            events.add(event22_3.toWeekViewEvent());

            Event event22_4 = new Event();
            String randomRGB21_4 = Constants.getRandomColorRGB();
            event22_4.setColor("#d0" + randomRGB21_4);
            event22_4.setTextColor("#d0424242");
            event22_4.setMonth(3);
            event22_4.setDayOfMonth(21);
            event22_4.setName("Hillel Board Meeting");
            event22_4.setStartTime(17 + ":" + 00);
            event22_4.setEndTime(18 + ":" + 00);
            event22_4.setGroupName("Hillel");
            events.add(event22_4.toWeekViewEvent());

            Event event79 = new Event();
            String randomRGB79 = Constants.getRandomColorRGB();
            event79.setColor("#d0" + randomRGB79);
            event79.setTextColor("#d0424242");
            event79.setMonth(3);
            event79.setDayOfMonth(27);
            event79.setName("Alumni Event");
            event79.setStartTime(21 + ":" + 00);
            event79.setEndTime(22 + ":" + 00);
            event79.setGroupName("Lambda Chi Alpha");
            events.add(event79.toWeekViewEvent());

            Event event38 = new Event();
            String randomRGB38 = Constants.getRandomColorRGB();
            event38.setColor("#d0" + randomRGB38);
            event38.setTextColor("#d0424242");
            event38.setMonth(3);
            event38.setDayOfMonth(27);
            event38.setName("Hillel Shabbat");
            event38.setStartTime(18 + ":" + 00);
            event38.setEndTime(20 + ":" + 45);
            event38.setGroupName("Hillel");
            events.add(event38.toWeekViewEvent());

            Event event28 = new Event();
            String randomRGB28 = Constants.getRandomColorRGB();
            event28.setColor("#d0" + randomRGB28);
            event28.setTextColor("#d0424242");
            event28.setMonth(3);
            event28.setDayOfMonth(27);
            event28.setName("Intro to Mgt Finals");
            event28.setStartTime(8 + ":" + 00);
            event28.setEndTime(18 + ":" + 00);
            event28.setGroupName("Intro to Management final presentations");
            events.add(event28.toWeekViewEvent());

            Event event78 = new Event();
            String randomRGB78 = Constants.getRandomColorRGB();
            event78.setColor("#d0" + randomRGB78);
            event78.setTextColor("#d0424242");
            event78.setMonth(3);
            event78.setDayOfMonth(26);
            event78.setName("Phi Mu Delta E-Board Meeting");
            event78.setStartTime(20 + ":" + 00);
            event78.setEndTime(21 + ":" + 00);
            event78.setGroupName("Phi Mu Delta");
            events.add(event78.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Lunch bible study");
            event1.setStartTime(12 + ":" + 00);
            event1.setEndTime(13 + ":" + 00);
            event1.setGroupName("Chinese Christian Fellowship");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Sigma Delta E-board Meeting");
            event2.setStartTime(15 + ":" + 00);
            event2.setEndTime(17 + ":" + 00);
            event2.setGroupName("Sigma Delta");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("APO VP Meeting");
            event3.setStartTime(17 + ":" + 00);
            event3.setEndTime(18 + ":" + 00);
            event3.setGroupName("Alpha Phi Omega");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(25);
            event4.setName("Student Sustainability Task Force Weekly Meeting");
            event4.setStartTime(18 + ":" + 00);
            event4.setEndTime(19 + ":" + 00);
            event4.setGroupName("SSTF");
            events.add(event4.toWeekViewEvent());

            Event event5 = new Event();
            String randomRGB5 = Constants.getRandomColorRGB();
            event5.setColor("#d0" + randomRGB5);
            event5.setTextColor("#d0424242");
            event5.setMonth(3);
            event5.setDayOfMonth(25);
            event5.setName("Torah Study");
            event5.setStartTime(19 + ":" + 30);
            event5.setEndTime(20 + ":" + 30);
            event5.setGroupName("Chabad on Campus");
            events.add(event5.toWeekViewEvent());

            Event event6 = new Event();
            String randomRGB6 = Constants.getRandomColorRGB();
            event6.setColor("#d0" + randomRGB6);
            event6.setTextColor("#d0424242");
            event6.setMonth(3);
            event6.setDayOfMonth(25);
            event6.setName("CSSA Eboard weekly meeting");
            event6.setStartTime(20 + ":" + 30);
            event6.setEndTime(22 + ":" + 00);
            event6.setGroupName("Chinese Students and Scholars Association");
            events.add(event6.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("Foreign Language Club Meeting");
            event8.setStartTime(20 + ":" + 00);
            event8.setEndTime(22 + ":" + 30);
            event8.setGroupName("Foreign Language Club");
            events.add(event8.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Daily Prayer Meeting");
            event11.setStartTime(15 + ":" + 00);
            event11.setEndTime(16 + ":" + 30);
            event11.setGroupName("Rensselaer Christian Association");
            events.add(event11.toWeekViewEvent());

            event78 = new Event();
            randomRGB78 = Constants.getRandomColorRGB();
            event78.setColor("#d0" + randomRGB78);
            event78.setTextColor("#d0424242");
            event78.setMonth(3);
            event78.setDayOfMonth(26);
            event78.setName("Intro to Mgt Finals");
            event78.setStartTime(8 + ":" + 00);
            event78.setEndTime(18 + ":" + 00);
            event78.setGroupName("Intro to Management final presentations");
            events.add(event78.toWeekViewEvent());

            Event event22 = new Event();
            String randomRGB22 = Constants.getRandomColorRGB();
            event22.setColor("#d0" + randomRGB22);
            event22.setTextColor("#d0424242");
            event22.setMonth(3);
            event22.setDayOfMonth(26);
            event22.setName("SWE Outreach Meeting");
            event22.setStartTime(19 + ":" + 00);
            event22.setEndTime(20 + ":" + 00);
            event22.setGroupName("SWE (Society of Women Engineers)");
            events.add(event22.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Humans Versus Zombies");
            event21_1.setStartTime(11 + ":" + 00);
            event21_1.setEndTime(14 + ":" + 00);
            event21_1.setGroupName("Humans vs Zombies");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Prayer Meeting");
            event21_2.setStartTime(16 + ":" + 00);
            event21_2.setEndTime(17 + ":" + 00);
            event21_2.setGroupName("Rensselaer Christian Association");
            events.add(event21_2.toWeekViewEvent());
        } else if (facility_name.equals("3511")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("AGD Executive Council Meeting");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(16 + ":" + 00);
            event22_1.setGroupName("Alpha Gamma Delta");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("GMB");
            event22_2.setStartTime(18 + ":" + 00);
            event22_2.setEndTime(19 + ":" + 00);
            event22_2.setGroupName("Global Medical Brigades");
            events.add(event22_2.toWeekViewEvent());

            Event event79 = new Event();
            String randomRGB79 = Constants.getRandomColorRGB();
            event79.setColor("#d0" + randomRGB79);
            event79.setTextColor("#d0424242");
            event79.setMonth(3);
            event79.setDayOfMonth(27);
            event79.setName("Alumni Event");
            event79.setStartTime(21 + ":" + 00);
            event79.setEndTime(22 + ":" + 00);
            event79.setGroupName("Lambda Chi Alpha");
            events.add(event79.toWeekViewEvent());

            Event event38 = new Event();
            String randomRGB38 = Constants.getRandomColorRGB();
            event38.setColor("#d0" + randomRGB38);
            event38.setTextColor("#d0424242");
            event38.setMonth(3);
            event38.setDayOfMonth(27);
            event38.setName("Hillel Shabbat");
            event38.setStartTime(18 + ":" + 00);
            event38.setEndTime(20 + ":" + 45);
            event38.setGroupName("Hillel");
            events.add(event38.toWeekViewEvent());

            Event event97 = new Event();
            String randomRGB97 = Constants.getRandomColorRGB();
            event97.setColor("#d0" + randomRGB97);
            event97.setTextColor("#d0424242");
            event97.setMonth(3);
            event97.setDayOfMonth(27);
            event97.setName("Intro to Mgt Finals");
            event97.setStartTime(8 + ":" + 00);
            event97.setEndTime(18 + ":" + 00);
            event97.setGroupName("Intro to Management final presentations");
            events.add(event97.toWeekViewEvent());

            Event event64 = new Event();
            String randomRGB64 = Constants.getRandomColorRGB();
            event64.setColor("#d0" + randomRGB64);
            event64.setTextColor("#d0424242");
            event64.setMonth(3);
            event64.setDayOfMonth(26);
            event64.setName("APO MemComm");
            event64.setStartTime(19 + ":" + 00);
            event64.setEndTime(21 + ":" + 00);
            event64.setGroupName("Alpha Phi Omega");
            events.add(event64.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Legal Services");
            event1.setStartTime(15 + ":" + 30);
            event1.setEndTime(17 + ":" + 30);
            event1.setGroupName("Legal Services");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Fellowship Committee Meeting");
            event2.setStartTime(18 + ":" + 00);
            event2.setEndTime(19 + ":" + 00);
            event2.setGroupName("Alpha Phi Omega");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Service Committee Meeting");
            event3.setStartTime(19 + ":" + 00);
            event3.setEndTime(20 + ":" + 00);
            event3.setGroupName("Alpha Phi Omega");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(25);
            event4.setName("MLC");
            event4.setStartTime(20 + ":" + 00);
            event4.setEndTime(21 + ":" + 00);
            event4.setGroupName("Multicultural & Diversity Programs and Initiatives");
            events.add(event4.toWeekViewEvent());

            Event event5 = new Event();
            String randomRGB5 = Constants.getRandomColorRGB();
            event5.setColor("#d0" + randomRGB5);
            event5.setTextColor("#d0424242");
            event5.setMonth(3);
            event5.setDayOfMonth(25);
            event5.setName("SSA Meeting");
            event5.setStartTime(21 + ":" + 00);
            event5.setEndTime(23 + ":" + 00);
            event5.setGroupName("Secular Student Alliance");
            events.add(event5.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("RPI TV Executive Committee Meeting");
            event8.setStartTime(20 + ":" + 00);
            event8.setEndTime(23 + ":" + 00);
            event8.setGroupName("RPI TV");
            events.add(event8.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Daily Prayer Meeting");
            event11.setStartTime(15 + ":" + 00);
            event11.setEndTime(16 + ":" + 30);
            event11.setGroupName("Rensselaer Christian Association");
            events.add(event11.toWeekViewEvent());


            Event event91 = new Event();
            String randomRGB91 = Constants.getRandomColorRGB();
            event91.setColor("#d0" + randomRGB91);
            event91.setTextColor("#d0424242");
            event91.setMonth(3);
            event91.setDayOfMonth(26);
            event91.setName("Intro to Mgt Finals");
            event91.setStartTime(8 + ":" + 00);
            event91.setEndTime(18 + ":" + 00);
            event91.setGroupName("Intro to Management final presentations");
            events.add(event91.toWeekViewEvent());
        } else if (facility_name.equals("Elsworth")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Initiation");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(14 + ":" + 00);
            event22_1.setGroupName("Pi Tau Sigma");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Alpha Omega Epsilon Meeting");
            event22_2.setStartTime(17 + ":" + 30);
            event22_2.setEndTime(21 + ":" + 00);
            event22_2.setGroupName("Alpha Omega Epsilon");
            events.add(event22_2.toWeekViewEvent());

            Event event28 = new Event();
            String randomRGB28 = Constants.getRandomColorRGB();
            event28.setColor("#d0" + randomRGB28);
            event28.setTextColor("#d0424242");
            event28.setMonth(3);
            event28.setDayOfMonth(27);
            event28.setName("Rensselaer Christian Association");
            event28.setStartTime(16 + ":" + 00);
            event28.setEndTime(20 + ":" + 00);
            event28.setGroupName("Rensselaer Christian Association");
            events.add(event28.toWeekViewEvent());

            Event event82 = new Event();
            String randomRGB82 = Constants.getRandomColorRGB();
            event82.setColor("#d0" + randomRGB82);
            event82.setTextColor("#d0424242");
            event82.setMonth(3);
            event82.setDayOfMonth(27);
            event82.setName("Admissions");
            event82.setStartTime(8 + ":" + 15);
            event82.setEndTime(14 + ":" + 15);
            event82.setGroupName("Admissions");
            events.add(event82.toWeekViewEvent());

            Event event69 = new Event();
            String randomRGB69 = Constants.getRandomColorRGB();
            event69.setColor("#d0" + randomRGB69);
            event69.setTextColor("#d0424242");
            event69.setMonth(3);
            event69.setDayOfMonth(26);
            event69.setName("Rensselaer Pride Alliance");
            event69.setStartTime(20 + ":" + 30);
            event69.setEndTime(22 + ":" + 00);
            event69.setGroupName("General Non-Club Meetings");
            events.add(event69.toWeekViewEvent());

            Event event27 = new Event();
            String randomRGB27 = Constants.getRandomColorRGB();
            event27.setColor("#d0" + randomRGB27);
            event27.setTextColor("#d0424242");
            event27.setMonth(3);
            event27.setDayOfMonth(26);
            event27.setName("Active Minds Elections Meeting");
            event27.setStartTime(19 + ":" + 30);
            event27.setEndTime(20 + ":" + 30);
            event27.setGroupName("Active Minds");
            events.add(event27.toWeekViewEvent());

            Event event54 = new Event();
            String randomRGB54 = Constants.getRandomColorRGB();
            event54.setColor("#d0" + randomRGB54);
            event54.setTextColor("#d0424242");
            event54.setMonth(3);
            event54.setDayOfMonth(26);
            event54.setName("Society Of Hispanic Pofessional Engineers General Body Meetings");
            event54.setStartTime(17 + ":" + 00);
            event54.setEndTime(19 + ":" + 30);
            event54.setGroupName("Society of Hispanic Engineers");
            events.add(event54.toWeekViewEvent());


            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Admissions");
            event1.setStartTime(8 + ":" + 15);
            event1.setEndTime(14 + ":" + 15);
            event1.setGroupName("Admissions");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Black Students' Alliance General Body Meeting");
            event2.setStartTime(17 + ":" + 30);
            event2.setEndTime(19 + ":" + 30);
            event2.setGroupName("Black Students Alliance");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Photo Club GBM");
            event3.setStartTime(20 + ":" + 00);
            event3.setEndTime(22 + ":" + 00);
            event3.setGroupName("Photo Club");
            events.add(event3.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Sexual Harassment Workshop");
            event11.setStartTime(17 + ":" + 30);
            event11.setEndTime(19 + ":" + 30);
            event11.setGroupName("SWE (Society of Women Engineers)");
            events.add(event11.toWeekViewEvent());

            Event event12 = new Event();
            String randomRGB12 = Constants.getRandomColorRGB();
            event12.setColor("#d0" + randomRGB12);
            event12.setTextColor("#d0424242");
            event12.setMonth(3);
            event12.setDayOfMonth(24);
            event12.setName("RPI TV");
            event12.setStartTime(21 + ":" + 00);
            event12.setEndTime(22 + ":" + 00);
            event12.setGroupName("RPI TV");
            events.add(event12.toWeekViewEvent());

            Event event46 = new Event();
            String randomRGB46 = Constants.getRandomColorRGB();
            event46.setColor("#d0" + randomRGB46);
            event46.setTextColor("#d0424242");
            event46.setMonth(3);
            event46.setDayOfMonth(26);
            event46.setName("Admissions");
            event46.setStartTime(8 + ":" + 15);
            event46.setEndTime(14 + ":" + 15);
            event46.setGroupName("Admissions");
            events.add(event46.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Fighting Game Club Final Semester Meeting");
            event21_1.setStartTime(18 + ":" + 00);
            event21_1.setEndTime(23 + ":" + 59);
            event21_1.setGroupName("Fighting Games Club");
            events.add(event21_1.toWeekViewEvent());
        } else if (facility_name.equals("Games Room")) {
            Event event96 = new Event();
            String randomRGB96 = Constants.getRandomColorRGB();
            event96.setColor("#d0" + randomRGB96);
            event96.setTextColor("#d0424242");
            event96.setMonth(3);
            event96.setDayOfMonth(27);
            event96.setName("Smash Club Weekly Meeting");
            event96.setStartTime(17 + ":" + 30);
            event96.setEndTime(23 + ":" + 45);
            event96.setGroupName("Super Smash Brothers Club");
            events.add(event96.toWeekViewEvent());

            Event event78 = new Event();
            String randomRGB78 = Constants.getRandomColorRGB();
            event78.setColor("#d0" + randomRGB78);
            event78.setTextColor("#d0424242");
            event78.setMonth(3);
            event78.setDayOfMonth(26);
            event78.setName("Magic Tournament/Casual Play");
            event78.setStartTime(19 + ":" + 00);
            event78.setEndTime(23 + ":" + 45);
            event78.setGroupName("Magic Club");
            events.add(event78.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Senior Photos");
            event1.setStartTime(7 + ":" + 00);
            event1.setEndTime(12 + ":" + 00);
            event1.setGroupName("Rensselaer Union Administration Office");
            events.add(event1.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Charity Smash Tournament");
            event21_1.setStartTime(10 + ":" + 00);
            event21_1.setEndTime(18 + ":" + 00);
            event21_1.setGroupName("Sigma Chi");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Magic Tournament/Casual Play");
            event21_2.setStartTime(19 + ":" + 00);
            event21_2.setEndTime(23 + ":" + 45);
            event21_2.setGroupName("Magic Club");
            events.add(event21_2.toWeekViewEvent());

            Event event7 = new Event();
            String randomRGB7 = Constants.getRandomColorRGB();
            event7.setColor("#d0" + randomRGB7);
            event7.setTextColor("#d0424242");
            event7.setMonth(3);
            event7.setDayOfMonth(23);
            event7.setName("Cubing Club");
            event7.setStartTime(20 + ":" + 30);
            event7.setEndTime(22 + ":" + 30);
            event7.setGroupName("Cubing Club");
            events.add(event7.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("ANS Game Night");
            event8.setStartTime(18 + ":" + 00);
            event8.setEndTime(20 + ":" + 00);
            event8.setGroupName("American Nuclear Society");
            events.add(event8.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Senior Photos");
            event11.setStartTime(07 + ":" + 00);
            event11.setEndTime(12 + ":" + 00);
            event11.setGroupName("Rensselaer Union Administration Office");
            events.add(event11.toWeekViewEvent());
        } else if (facility_name.equals("McNeil Room")) {
            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("BSA Fashion Show");
            event21_1.setStartTime(10 + ":" + 00);
            event21_1.setEndTime(23 + ":" + 59);
            event21_1.setGroupName("Black Students Alliance");
            events.add(event21_1.toWeekViewEvent());
            Event event52 = new Event();
            String randomRGB52 = Constants.getRandomColorRGB();
            event52.setColor("#d0" + randomRGB52);
            event52.setTextColor("#d0424242");
            event52.setMonth(3);
            event52.setDayOfMonth(27);
            event52.setName("RPI Martial Arts Expo");
            event52.setStartTime(18 + ":" + 15);
            event52.setEndTime(23 + ":" + 00);
            event52.setGroupName("Kung Fu");
            events.add(event52.toWeekViewEvent());
        } else if (facility_name.equals("Mother's")) {

            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("SO Team Training");
            event22_1.setStartTime(9 + ":" + 00);
            event22_1.setEndTime(13 + ":" + 00);
            event22_1.setGroupName("FYE (First Year Experience)");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Circle K Divisional");
            event22_2.setStartTime(13 + ":" + 00);
            event22_2.setEndTime(15 + ":" + 00);
            event22_2.setGroupName("Circle K");
            events.add(event22_2.toWeekViewEvent());

            Event event22_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event22_3.setColor("#d0" + randomRGB21_3);
            event22_3.setTextColor("#d0424242");
            event22_3.setMonth(3);
            event22_3.setDayOfMonth(21);
            event22_3.setName("Sheer Idiocy Practice");
            event22_3.setStartTime(15 + ":" + 00);
            event22_3.setEndTime(17 + ":" + 00);
            event22_3.setGroupName("Sheer Idiocy");
            events.add(event22_3.toWeekViewEvent());

            Event event22_4 = new Event();
            String randomRGB21_4 = Constants.getRandomColorRGB();
            event22_4.setColor("#d0" + randomRGB21_4);
            event22_4.setTextColor("#d0424242");
            event22_4.setMonth(3);
            event22_4.setDayOfMonth(21);
            event22_4.setName("Chapter (Ritual)");
            event22_4.setStartTime(18 + ":" + 00);
            event22_4.setEndTime(20 + ":" + 30);
            event22_4.setGroupName("Tau Kappa Epsilon");
            events.add(event22_4.toWeekViewEvent());

            Event event97 = new Event();
            String randomRGB97 = Constants.getRandomColorRGB();
            event97.setColor("#d0" + randomRGB97);
            event97.setTextColor("#d0424242");
            event97.setMonth(3);
            event97.setDayOfMonth(27);
            event97.setName("Sheer Idiocy Show Cont.");
            event97.setStartTime(22 + ":" + 00);
            event97.setEndTime(23 + ":" + 00);
            event97.setGroupName("Sheer Idiocy");
            events.add(event97.toWeekViewEvent());

            Event event47 = new Event();
            String randomRGB47 = Constants.getRandomColorRGB();
            event47.setColor("#d0" + randomRGB47);
            event47.setTextColor("#d0424242");
            event47.setMonth(3);
            event47.setDayOfMonth(27);
            event47.setName("Sheer Idiocy Show");
            event47.setStartTime(18 + ":" + 00);
            event47.setEndTime(22 + ":" + 00);
            event47.setGroupName("Sheer Idiocy");
            events.add(event47.toWeekViewEvent());

            event97 = new Event();
            randomRGB97 = Constants.getRandomColorRGB();
            event97.setColor("#d0" + randomRGB97);
            event97.setTextColor("#d0424242");
            event97.setMonth(3);
            event97.setDayOfMonth(27);
            event97.setName("MLC Interviews");
            event97.setStartTime(16 + ":" + 00);
            event97.setEndTime(18 + ":" + 00);
            event97.setGroupName("Multicultural & Diversity Programs and Initiatives");
            events.add(event97.toWeekViewEvent());

            Event event46 = new Event();
            String randomRGB46 = Constants.getRandomColorRGB();
            event46.setColor("#d0" + randomRGB46);
            event46.setTextColor("#d0424242");
            event46.setMonth(3);
            event46.setDayOfMonth(26);
            event46.setName("Sheer Idiocy Practice");
            event46.setStartTime(20 + ":" + 00);
            event46.setEndTime(22 + ":" + 00);
            event46.setGroupName("Sheer Idiocy");
            events.add(event46.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("MLC Interviews");
            event1.setStartTime(14 + ":" + 00);
            event1.setEndTime(18 + ":" + 00);
            event1.setGroupName("Multicultural & Diversity Programs and Initiatives");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Senior Council TIPs Training");
            event2.setStartTime(18 + ":" + 00);
            event2.setEndTime(20 + ":" + 00);
            event2.setGroupName("Class of 2018");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("RPI Domino Toppling");
            event3.setStartTime(20 + ":" + 00);
            event3.setEndTime(22 + ":" + 30);
            event3.setGroupName("CASA");
            events.add(event3.toWeekViewEvent());

            Event event91 = new Event();
            String randomRGB91 = Constants.getRandomColorRGB();
            event91.setColor("#d0" + randomRGB91);
            event91.setTextColor("#d0424242");
            event91.setMonth(3);
            event91.setDayOfMonth(26);
            event91.setName("Panhellenic Council - Herb Potting Event");
            event91.setStartTime(16 + ":" + 00);
            event91.setEndTime(19 + ":" + 00);
            event91.setGroupName("RPI Panhellenic Council");
            events.add(event91.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Smash Tournament");
            event21_1.setStartTime(8 + ":" + 00);
            event21_1.setEndTime(23 + ":" + 45);
            event21_1.setGroupName("Super Smash Brothers Club");
            events.add(event21_1.toWeekViewEvent());
        } else if (facility_name.equals("Piano Room 2610")) {

        } else if (facility_name.equals("Anderson Field")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Rugby Alumni Weekend");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(18 + ":" + 00);
            event22_1.setGroupName("Rugby Club");
            events.add(event22_1.toWeekViewEvent());

            Event event28 = new Event();
            String randomRGB28 = Constants.getRandomColorRGB();
            event28.setColor("#d0" + randomRGB28);
            event28.setTextColor("#d0424242");
            event28.setMonth(3);
            event28.setDayOfMonth(26);
            event28.setName("Rugby Practice");
            event28.setStartTime(20 + ":" + 00);
            event28.setEndTime(22 + ":" + 00);
            event28.setGroupName("Rugby Club");
            events.add(event28.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Women's Ultimate Frisbee");
            event1.setStartTime(18 + ":" + 00);
            event1.setEndTime(20 + ":" + 00);
            event1.setGroupName("Ultimate Frisbee Club");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(23);
            event2.setName("Women's Ultimate Frisbee");
            event2.setStartTime(20 + ":" + 00);
            event2.setEndTime(22 + ":" + 00);
            event2.setGroupName("Ultimate Frisbee Club");
            events.add(event1.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("Ultimate Frisbee Practice");
            event8.setStartTime(18 + ":" + 00);
            event8.setEndTime(20 + ":" + 00);
            event8.setGroupName("Ultimate Frisbee");
            events.add(event8.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Rugby Practice");
            event11.setStartTime(20 + ":" + 00);
            event11.setEndTime(22 + ":" + 00);
            event11.setGroupName("Rugby Club");
            events.add(event11.toWeekViewEvent());

            Event event12 = new Event();
            String randomRGB12 = Constants.getRandomColorRGB();
            event12.setColor("#d0" + randomRGB12);
            event12.setTextColor("#d0424242");
            event12.setMonth(3);
            event12.setDayOfMonth(24);
            event12.setName("Ultimate Frisbee Practice");
            event12.setStartTime(18 + ":" + 00);
            event12.setEndTime(20 + ":" + 00);
            event12.setGroupName("Ultimate Frisbee");
            events.add(event12.toWeekViewEvent());

            Event event36 = new Event();
            String randomRGB36 = Constants.getRandomColorRGB();
            event36.setColor("#d0" + randomRGB36);
            event36.setTextColor("#d0424242");
            event36.setMonth(3);
            event36.setDayOfMonth(26);
            event36.setName("Women's Ultimate Frisbee");
            event36.setStartTime(18 + ":" + 00);
            event36.setEndTime(20 + ":" + 00);
            event36.setGroupName("Ultimate Frisbee Club");
            events.add(event36.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Rugby Alumni Weekend");
            event21_1.setStartTime(12 + ":" + 00);
            event21_1.setEndTime(18 + ":" + 00);
            event21_1.setGroupName("Rugby Club");
            events.add(event21_1.toWeekViewEvent());
        } else if (facility_name.equals("Harkness Field")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("US Field Hockey Futures");
            event22_1.setStartTime(9 + ":" + 00);
            event22_1.setEndTime(12 + ":" + 00);
            event22_1.setGroupName("USA Field Hockey Futures");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("ROTC Taylor Trophy - Ultimate Frisbeey");
            event22_2.setStartTime(12 + ":" + 00);
            event22_2.setEndTime(14 + ":" + 00);
            event22_2.setGroupName("Navy ROTC");
            events.add(event22_2.toWeekViewEvent());

            Event event22_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event22_3.setColor("#d0" + randomRGB21_3);
            event22_3.setTextColor("#d0424242");
            event22_3.setMonth(3);
            event22_3.setDayOfMonth(21);
            event22_3.setName("Intramural Field Hockey");
            event22_3.setStartTime(14 + ":" + 00);
            event22_3.setEndTime(17 + ":" + 00);
            event22_3.setGroupName("Intramural Sports");
            events.add(event22_3.toWeekViewEvent());

            Event event22_4 = new Event();
            String randomRGB21_4 = Constants.getRandomColorRGB();
            event22_4.setColor("#d0" + randomRGB21_4);
            event22_4.setTextColor("#d0424242");
            event22_4.setMonth(3);
            event22_4.setDayOfMonth(21);
            event22_4.setName("Intramural Outdoor Soccer");
            event22_4.setStartTime(17 + ":" + 00);
            event22_4.setEndTime(23 + ":" + 00);
            event22_4.setGroupName("Intramural Sports");
            events.add(event22_4.toWeekViewEvent());

            Event event74 = new Event();
            String randomRGB74 = Constants.getRandomColorRGB();
            event74.setColor("#d0" + randomRGB74);
            event74.setTextColor("#d0424242");
            event74.setMonth(3);
            event74.setDayOfMonth(27);
            event74.setName("Track & Field Practice");
            event74.setStartTime(16 + ":" + 00);
            event74.setEndTime(18 + ":" + 00);
            event74.setGroupName("RPI Track & Field");
            events.add(event74.toWeekViewEvent());

            Event event45 = new Event();
            String randomRGB45 = Constants.getRandomColorRGB();
            event45.setColor("#d0" + randomRGB45);
            event45.setTextColor("#d0424242");
            event45.setMonth(3);
            event45.setDayOfMonth(26);
            event45.setName("Intramural Outdoor Soccer");
            event45.setStartTime(22 + ":" + 00);
            event45.setEndTime(23 + ":" + 00);
            event45.setGroupName("Intramural Sports");
            events.add(event45.toWeekViewEvent());

            Event event27 = new Event();
            String randomRGB27 = Constants.getRandomColorRGB();
            event27.setColor("#d0" + randomRGB27);
            event27.setTextColor("#d0424242");
            event27.setMonth(3);
            event27.setDayOfMonth(26);
            event27.setName("Mens Club Soccer Practice");
            event27.setStartTime(20 + ":" + 00);
            event27.setEndTime(22 + ":" + 00);
            event27.setGroupName("Mens Club Soccer");
            events.add(event27.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Quidditch Practice");
            event1.setStartTime(18 + ":" + 00);
            event1.setEndTime(20 + ":" + 00);
            event1.setGroupName("RU Club Quidditch");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Intramural Outdoor Soccer");
            event2.setStartTime(21 + ":" + 00);
            event2.setEndTime(23 + ":" + 00);
            event2.setGroupName("Intramural Sports");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(23);
            event3.setName("Intramural Field Hockey");
            event3.setStartTime(22 + ":" + 00);
            event3.setEndTime(23 + ":" + 00);
            event3.setGroupName("Intramural Sports");
            events.add(event3.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("Mens Club Soccer Practice");
            event8.setStartTime(20 + ":" + 00);
            event8.setEndTime(22 + ":" + 00);
            event8.setGroupName("Mens Club Soccer");
            events.add(event8.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Cricket");
            event11.setStartTime(18 + ":" + 00);
            event11.setEndTime(19 + ":" + 00);
            event11.setGroupName("RPI Cricket Club");
            events.add(event11.toWeekViewEvent());

            Event event12 = new Event();
            String randomRGB12 = Constants.getRandomColorRGB();
            event12.setColor("#d0" + randomRGB12);
            event12.setTextColor("#d0424242");
            event12.setMonth(3);
            event12.setDayOfMonth(24);
            event12.setName("Intramural Outdoor Soccer");
            event12.setStartTime(18 + ":" + 00);
            event12.setEndTime(20 + ":" + 00);
            event12.setGroupName("Intramural Sports");
            events.add(event12.toWeekViewEvent());

            Event event54 = new Event();
            String randomRGB54 = Constants.getRandomColorRGB();
            event54.setColor("#d0" + randomRGB54);
            event54.setTextColor("#d0424242");
            event54.setMonth(3);
            event54.setDayOfMonth(26);
            event54.setName("Track & Field Practice");
            event54.setStartTime(16 + ":" + 00);
            event54.setEndTime(18 + ":" + 00);
            event54.setGroupName("RPI Track & Field");
            events.add(event54.toWeekViewEvent());

            Event event53 = new Event();
            String randomRGB53 = Constants.getRandomColorRGB();
            event53.setColor("#d0" + randomRGB53);
            event53.setTextColor("#d0424242");
            event53.setMonth(3);
            event53.setDayOfMonth(26);
            event53.setName("Quidditch Practice");
            event53.setStartTime(18 + ":" + 00);
            event53.setEndTime(20 + ":" + 00);
            event53.setGroupName("RU Club Quidditch");
            events.add(event53.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Cricket");
            event21_1.setStartTime(10 + ":" + 00);
            event21_1.setEndTime(11 + ":" + 00);
            event21_1.setGroupName("RPI Cricket Club");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Women's Club Soccer Game");
            event21_2.setStartTime(12 + ":" + 00);
            event21_2.setEndTime(15 + ":" + 00);
            event21_2.setGroupName("RU Club Soccer - Women's");
            events.add(event21_2.toWeekViewEvent());

            Event event21_3 = new Event();
            randomRGB21_3 = Constants.getRandomColorRGB();
            event21_3.setColor("#d0" + randomRGB21_3);
            event21_3.setTextColor("#d0424242");
            event21_3.setMonth(3);
            event21_3.setDayOfMonth(21);
            event21_3.setName("Mens Club Soccer Practice (Club Game)");
            event21_3.setStartTime(16 + ":" + 00);
            event21_3.setEndTime(19 + ":" + 00);
            event21_3.setGroupName("Mens Club Soccer");
            events.add(event21_3.toWeekViewEvent());
        } else if (facility_name.equals("Harkness Track")) {
            Event event74 = new Event();
            String randomRGB74 = Constants.getRandomColorRGB();
            event74.setColor("#d0" + randomRGB74);
            event74.setTextColor("#d0424242");
            event74.setMonth(3);
            event74.setDayOfMonth(26);
            event74.setName("Track & Field Practice");
            event74.setStartTime(16 + ":" + 00);
            event74.setEndTime(18 + ":" + 00);
            event74.setGroupName("RPI Track & Field");
            events.add(event74.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Track & Field Practice");
            event1.setStartTime(16 + ":" + 00);
            event1.setEndTime(18 + ":" + 00);
            event1.setGroupName("RPI Track & Field");
            events.add(event1.toWeekViewEvent());

            Event event54 = new Event();
            String randomRGB54 = Constants.getRandomColorRGB();
            event54.setColor("#d0" + randomRGB54);
            event54.setTextColor("#d0424242");
            event54.setMonth(3);
            event54.setDayOfMonth(26);
            event54.setName("Track & Field Practice");
            event54.setStartTime(16 + ":" + 00);
            event54.setEndTime(18 + ":" + 00);
            event54.setGroupName("RPI Track & Field");
            events.add(event54.toWeekViewEvent());

        } else if (facility_name.equals("Lower Renwyck Grass Field")) {
            Event event76 = new Event();
            String randomRGB76 = Constants.getRandomColorRGB();
            event76.setColor("#d0" + randomRGB76);
            event76.setTextColor("#d0424242");
            event76.setMonth(3);
            event76.setDayOfMonth(27);
            event76.setName("Track & Field Practice");
            event76.setStartTime(16 + ":" + 00);
            event76.setEndTime(18 + ":" + 00);
            event76.setGroupName("RPI Track & Field");
            events.add(event76.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Track & Field Practice");
            event1.setStartTime(16 + ":" + 00);
            event1.setEndTime(18 + ":" + 00);
            event1.setGroupName("RPI Track & Field");
            events.add(event1.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Women's Soccer Practice");
            event11.setStartTime(16 + ":" + 30);
            event11.setEndTime(18 + ":" + 00);
            event11.setGroupName("RPI Women's Soccer");
            events.add(event11.toWeekViewEvent());

            Event event60 = new Event();
            String randomRGB60 = Constants.getRandomColorRGB();
            event60.setColor("#d0" + randomRGB60);
            event60.setTextColor("#d0424242");
            event60.setMonth(3);
            event60.setDayOfMonth(26);
            event60.setName("Track & Field Practice");
            event60.setStartTime(16 + ":" + 00);
            event60.setEndTime(18 + ":" + 00);
            event60.setGroupName("RPI Track & Field");
            events.add(event60.toWeekViewEvent());
        } else if (facility_name.equals("Lower Renwyck Turf Field")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("US Field Hockey Futures");
            event22_1.setStartTime(9 + ":" + 00);
            event22_1.setEndTime(12 + ":" + 00);
            event22_1.setGroupName("USA Field Hockey Futures");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Intramural Outdoor Soccer");
            event22_2.setStartTime(14 + ":" + 00);
            event22_2.setEndTime(23 + ":" + 00);
            event22_2.setGroupName("Intramural Sports");
            events.add(event22_2.toWeekViewEvent());

            Event event91 = new Event();
            String randomRGB91 = Constants.getRandomColorRGB();
            event91.setColor("#d0" + randomRGB91);
            event91.setTextColor("#d0424242");
            event91.setMonth(3);
            event91.setDayOfMonth(27);
            event91.setName("Cricket");
            event91.setStartTime(18 + ":" + 00);
            event91.setEndTime(19 + ":" + 15);
            event91.setGroupName("RPI Cricket Club");
            events.add(event91.toWeekViewEvent());

            Event event50 = new Event();
            String randomRGB50 = Constants.getRandomColorRGB();
            event50.setColor("#d0" + randomRGB50);
            event50.setTextColor("#d0424242");
            event50.setMonth(3);
            event50.setDayOfMonth(27);
            event50.setName("Women's Soccer Practice");
            event50.setStartTime(16 + ":" + 30);
            event50.setEndTime(18 + ":" + 00);
            event50.setGroupName("RPI Women's Soccer");
            events.add(event50.toWeekViewEvent());

            Event event93 = new Event();
            String randomRGB93 = Constants.getRandomColorRGB();
            event93.setColor("#d0" + randomRGB93);
            event93.setTextColor("#d0424242");
            event93.setMonth(3);
            event93.setDayOfMonth(26);
            event93.setName("Intramural Outdoor Soccer");
            event93.setStartTime(21 + ":" + 00);
            event93.setEndTime(23 + ":" + 00);
            event93.setGroupName("Intramural Sports");
            events.add(event93.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Women's Lacrosse Practice");
            event1.setStartTime(16 + ":" + 30);
            event1.setEndTime(18 + ":" + 30);
            event1.setGroupName("RPI Women's Lacrosse");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Mens Club Soccer Practice");
            event2.setStartTime(20 + ":" + 00);
            event2.setEndTime(22 + ":" + 00);
            event2.setGroupName("Mens Club Soccer");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Intramural Outdoor Soccer");
            event3.setStartTime(22 + ":" + 00);
            event3.setEndTime(23 + ":" + 30);
            event3.setGroupName("Intramural Sports");
            events.add(event3.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Cricket");
            event21_1.setStartTime(10 + ":" + 00);
            event21_1.setEndTime(11 + ":" + 00);
            event21_1.setGroupName("RPI Cricket Club");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Women's Lacrosse vs. Bard");
            event21_2.setStartTime(14 + ":" + 00);
            event21_2.setEndTime(16 + ":" + 00);
            event21_2.setGroupName("RPI Women's Lacrosse");
            events.add(event21_2.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(23);
            event4.setName("Intramural Outdoor Soccer");
            event4.setStartTime(22 + ":" + 00);
            event4.setEndTime(23 + ":" + 30);
            event4.setGroupName("Intramural Sports");
            events.add(event4.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("Women's Soccer Practice");
            event8.setStartTime(19 + ":" + 00);
            event8.setEndTime(20 + ":" + 30);
            event8.setGroupName("RPI Women's Soccer");
            events.add(event8.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Women's Lacrosse Practice");
            event11.setStartTime(16 + ":" + 30);
            event11.setEndTime(18 + ":" + 00);
            event11.setGroupName("RPI Women's Lacrosse");
            events.add(event11.toWeekViewEvent());

            Event event97 = new Event();
            String randomRGB97 = Constants.getRandomColorRGB();
            event97.setColor("#d0" + randomRGB97);
            event97.setTextColor("#d0424242");
            event97.setMonth(3);
            event97.setDayOfMonth(26);
            event97.setName("RPI Women's Lacrosse Practice");
            event97.setStartTime(18 + ":" + 30);
            event97.setEndTime(20 + ":" + 30);
            event97.setGroupName("RPI Women's Lacrosse");
            events.add(event97.toWeekViewEvent());
        } else if (facility_name.equals("Upper Renwyck Grass Field")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Intramural Spike Ball");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(15 + ":" + 00);
            event22_1.setGroupName("Intramural Sports");
            events.add(event22_1.toWeekViewEvent());
        } else if (facility_name.equals("Robison Baseball Field")) {

        } else if (facility_name.equals("Robison Softball Field")) {

        } else if (facility_name.equals("Main Gym")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("3 vs 3 Basketball Tournament");
            event22_1.setStartTime(13 + ":" + 00);
            event22_1.setEndTime(16 + ":" + 00);
            event22_1.setGroupName("Pi Kappa Alpha (PIKES)");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("ROTC Taylor Trophy - Basketball Tournament");
            event22_2.setStartTime(16 + ":" + 00);
            event22_2.setEndTime(20 + ":" + 00);
            event22_2.setGroupName("Navy ROTC");
            events.add(event22_2.toWeekViewEvent());

            Event event99 = new Event();
            String randomRGB99 = Constants.getRandomColorRGB();
            event99.setColor("#d0" + randomRGB99);
            event99.setTextColor("#d0424242");
            event99.setMonth(3);
            event99.setDayOfMonth(26);
            event99.setName("Spring Basketball");
            event99.setStartTime(17 + ":" + 00);
            event99.setEndTime(23 + ":" + 00);
            event99.setGroupName("RPI Men's Basketball");
            events.add(event99.toWeekViewEvent());

            Event event66 = new Event();
            String randomRGB66 = Constants.getRandomColorRGB();
            event66.setColor("#d0" + randomRGB66);
            event66.setTextColor("#d0424242");
            event66.setMonth(3);
            event66.setDayOfMonth(27);
            event66.setName("Football Practice");
            event66.setStartTime(6 + ":" + 00);
            event66.setEndTime(8 + ":" + 00);
            event66.setGroupName("RPI Football");
            events.add(event66.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Men's Basketball Practice");
            event1.setStartTime(16 + ":" + 30);
            event1.setEndTime(18 + ":" + 30);
            event1.setGroupName("RPI Men's Basketball");
            events.add(event1.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Basketball Practice");
            event21_1.setStartTime(12 + ":" + 00);
            event21_1.setEndTime(14 + ":" + 30);
            event21_1.setGroupName("RPI Men's Basketball");
            events.add(event21_1.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Spring Basketball");
            event11.setStartTime(15 + ":" + 30);
            event11.setEndTime(16 + ":" + 30);
            event11.setGroupName("RPI Men's Basketball");
            events.add(event11.toWeekViewEvent());
        } else if (facility_name.equals("Practice Gym")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("ROTC Taylor Trophy - Volleyball Tournament");
            event22_1.setStartTime(16 + ":" + 00);
            event22_1.setEndTime(20 + ":" + 00);
            event22_1.setGroupName("Navy ROTC");
            events.add(event22_1.toWeekViewEvent());

            Event event54 = new Event();
            String randomRGB54 = Constants.getRandomColorRGB();
            event54.setColor("#d0" + randomRGB54);
            event54.setTextColor("#d0424242");
            event54.setMonth(3);
            event54.setDayOfMonth(27);
            event54.setName("ASCE Concrete Canoe & Steel Bridge Competition Dinner (Setup)");
            event54.setStartTime(6 + ":" + 00);
            event54.setEndTime(23 + ":" + 59);
            event54.setGroupName("American Society of Civil Engineers (ASCE)");
            events.add(event54.toWeekViewEvent());

            Event event95 = new Event();
            String randomRGB95 = Constants.getRandomColorRGB();
            event95.setColor("#d0" + randomRGB95);
            event95.setTextColor("#d0424242");
            event95.setMonth(3);
            event95.setDayOfMonth(26);
            event95.setName("Club Volleyball Practice");
            event95.setStartTime(19 + ":" + 00);
            event95.setEndTime(23 + ":" + 00);
            event95.setGroupName("RU Club Volleyball - Women's");
            events.add(event95.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Acoustic Measurements");
            event1.setStartTime(8 + ":" + 30);
            event1.setEndTime(13 + ":" + 30);
            event1.setGroupName("School of Architecture");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Women's Hockey Yoga");
            event2.setStartTime(16 + ":" + 00);
            event2.setEndTime(17 + ":" + 00);
            event2.setGroupName("RPI Women's Hockey");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Club Volleyball Practice");
            event3.setStartTime(20 + ":" + 30);
            event3.setEndTime(23 + ":" + 00);
            event3.setGroupName("RU Club Volleyball - Women's");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(23);
            event4.setName("Delta Phi Volleyball");
            event4.setStartTime(19 + ":" + 00);
            event4.setEndTime(21 + ":" + 00);
            event4.setGroupName("Delta Phi");
            events.add(event4.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("Strength & Conditioning");
            event8.setStartTime(06 + ":" + 30);
            event8.setEndTime(07 + ":" + 30);
            event8.setGroupName("Practice Gym	Strength & Conditioning");
            events.add(event8.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Club Volleyball Practice");
            event11.setStartTime(20 + ":" + 30);
            event11.setEndTime(23 + ":" + 00);
            event11.setGroupName("Club Volleyball - Women's");
            events.add(event11.toWeekViewEvent());

            Event event87 = new Event();
            String randomRGB87 = Constants.getRandomColorRGB();
            event87.setColor("#d0" + randomRGB87);
            event87.setTextColor("#d0424242");
            event87.setMonth(3);
            event87.setDayOfMonth(26);
            event87.setName("Acoustic Measurements");
            event87.setStartTime(8 + ":" + 30);
            event87.setEndTime(13 + ":" + 30);
            event87.setGroupName("School of Architecture");
            events.add(event87.toWeekViewEvent());

            Event event27 = new Event();
            String randomRGB27 = Constants.getRandomColorRGB();
            event27.setColor("#d0" + randomRGB27);
            event27.setTextColor("#d0424242");
            event27.setMonth(3);
            event27.setDayOfMonth(26);
            event27.setName("Field Hockey Conditioning Test");
            event27.setStartTime(16 + ":" + 00);
            event27.setEndTime(17 + ":" + 00);
            event27.setGroupName("Strength & Conditioning");
            events.add(event27.toWeekViewEvent());
        } else if (facility_name.equals("Classroom")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Fitness Class Fall 2018");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(13 + ":" + 00);
            event22_1.setGroupName("Mueller Center Classes");
            events.add(event22_1.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("NSBE E-Board Meeting");
            event22_2.setStartTime(14 + ":" + 00);
            event22_2.setEndTime(15 + ":" + 30);
            event22_2.setGroupName("NSBE (National Society of Black Engineers)");
            events.add(event22_2.toWeekViewEvent());

            Event event22_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event22_3.setColor("#d0" + randomRGB21_3);
            event22_3.setTextColor("#d0424242");
            event22_3.setMonth(3);
            event22_3.setDayOfMonth(21);
            event22_3.setName("Dance Dance Revolution Club");
            event22_3.setStartTime(17 + ":" + 30);
            event22_3.setEndTime(20 + ":" + 30);
            event22_3.setGroupName("Dance Dance Revolution");
            events.add(event22_3.toWeekViewEvent());

            Event event37 = new Event();
            String randomRGB37 = Constants.getRandomColorRGB();
            event37.setColor("#d0" + randomRGB37);
            event37.setTextColor("#d0424242");
            event37.setMonth(3);
            event37.setDayOfMonth(27);
            event37.setName("Eighth Wonder");
            event37.setStartTime(18 + ":" + 00);
            event37.setEndTime(20 + ":" + 00);
            event37.setGroupName("Eighth Wonder");
            events.add(event37.toWeekViewEvent());

            Event event20 = new Event();
            String randomRGB20 = Constants.getRandomColorRGB();
            event20.setColor("#d0" + randomRGB20);
            event20.setTextColor("#d0424242");
            event20.setMonth(3);
            event20.setDayOfMonth(27);
            event20.setName("Fitness Classes");
            event20.setStartTime(17 + ":" + 00);
            event20.setEndTime(18 + ":" + 00);
            event20.setGroupName("Mueller Center Classes");
            events.add(event20.toWeekViewEvent());

            Event event60 = new Event();
            String randomRGB60 = Constants.getRandomColorRGB();
            event60.setColor("#d0" + randomRGB60);
            event60.setTextColor("#d0424242");
            event60.setMonth(3);
            event60.setDayOfMonth(27);
            event60.setName("Fitness Class Fall 2018");
            event60.setStartTime(12 + ":" + 00);
            event60.setEndTime(13 + ":" + 00);
            event60.setGroupName("Mueller Center Classes");
            events.add(event60.toWeekViewEvent());

            Event event85 = new Event();
            String randomRGB85 = Constants.getRandomColorRGB();
            event85.setColor("#d0" + randomRGB85);
            event85.setTextColor("#d0424242");
            event85.setMonth(3);
            event85.setDayOfMonth(27);
            event85.setName("Fitness Class Fall 2018");
            event85.setStartTime(12 + ":" + 00);
            event85.setEndTime(13 + ":" + 00);
            event85.setGroupName("Mueller Center Classes");
            events.add(event85.toWeekViewEvent());

            Event event44 = new Event();
            String randomRGB44 = Constants.getRandomColorRGB();
            event44.setColor("#d0" + randomRGB44);
            event44.setTextColor("#d0424242");
            event44.setMonth(3);
            event44.setDayOfMonth(26);
            event44.setName("RPI Weight Lifting Club");
            event44.setStartTime(20 + ":" + 00);
            event44.setEndTime(20 + ":" + 30);
            event44.setGroupName("Weight Lifting");
            events.add(event44.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Fitness Class Fall 2018");
            event1.setStartTime(12 + ":" + 00);
            event1.setEndTime(13 + ":" + 00);
            event1.setGroupName("Mueller Center Classes");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Pro Staff");
            event2.setStartTime(14 + ":" + 00);
            event2.setEndTime(16 + ":" + 00);
            event2.setGroupName("Mueller Center Classes");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Fitness Classes");
            event3.setStartTime(17 + ":" + 00);
            event3.setEndTime(18 + ":" + 00);
            event3.setGroupName("Mueller Center Classes");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(25);
            event4.setName("Dance Dance Revolution Club");
            event4.setStartTime(18 + ":" + 00);
            event4.setEndTime(20 + ":" + 30);
            event4.setGroupName("Dance Dance Revolution");
            events.add(event4.toWeekViewEvent());

            Event event5 = new Event();
            String randomRGB5 = Constants.getRandomColorRGB();
            event5.setColor("#d0" + randomRGB5);
            event5.setTextColor("#d0424242");
            event5.setMonth(3);
            event5.setDayOfMonth(25);
            event5.setName("swimming Team");
            event5.setStartTime(20 + ":" + 30);
            event5.setEndTime(22 + ":" + 00);
            event5.setGroupName("Mueller Center Classes");
            events.add(event5.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Mueller Center Enrichment Class");
            event21_1.setStartTime(10 + ":" + 00);
            event21_1.setEndTime(11 + ":" + 00);
            event21_1.setGroupName("Mueller Center Classes");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Mueller Center Enrichment Class");
            event21_2.setStartTime(11 + ":" + 00);
            event21_2.setEndTime(12 + ":" + 00);
            event21_2.setGroupName("Mueller Center Classes");
            events.add(event21_2.toWeekViewEvent());

            Event event21_3 = new Event();
            randomRGB21_3 = Constants.getRandomColorRGB();
            event21_3.setColor("#d0" + randomRGB21_3);
            event21_3.setTextColor("#d0424242");
            event21_3.setMonth(3);
            event21_3.setDayOfMonth(21);
            event21_3.setName("Fitness Class Fall 2018");
            event21_3.setStartTime(12 + ":" + 00);
            event21_3.setEndTime(13 + ":" + 00);
            event21_3.setGroupName("Mueller Center Classes");
            events.add(event21_3.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Bi-weekly Meetings");
            event11.setStartTime(21 + ":" + 00);
            event11.setEndTime(22 + ":" + 00);
            event11.setGroupName("Order of Omega");
            events.add(event11.toWeekViewEvent());

            Event event31 = new Event();
            String randomRGB31 = Constants.getRandomColorRGB();
            event31.setColor("#d0" + randomRGB31);
            event31.setTextColor("#d0424242");
            event31.setMonth(3);
            event31.setDayOfMonth(27);
            event31.setName("Fitness Classes");
            event31.setStartTime(17 + ":" + 00);
            event31.setEndTime(18 + ":" + 00);
            event31.setGroupName("Mueller Center Classes");
            events.add(event31.toWeekViewEvent());
        } else if (facility_name.equals("Multipurpose Room 1")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Fitness Class Fall 2018");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(13 + ":" + 00);
            event22_1.setGroupName("Mueller Center Classes");
            events.add(event22_1.toWeekViewEvent());

            Event event83 = new Event();
            String randomRGB83 = Constants.getRandomColorRGB();
            event83.setColor("#d0" + randomRGB83);
            event83.setTextColor("#d0424242");
            event83.setMonth(3);
            event83.setDayOfMonth(27);
            event83.setName("Eighth Wonder");
            event83.setStartTime(18 + ":" + 00);
            event83.setEndTime(20 + ":" + 00);
            event83.setGroupName("Eighth Wonder");
            events.add(event83.toWeekViewEvent());

            Event event85 = new Event();
            String randomRGB85 = Constants.getRandomColorRGB();
            event85.setColor("#d0" + randomRGB85);
            event85.setTextColor("#d0424242");
            event85.setMonth(3);
            event85.setDayOfMonth(26);
            event85.setName("Fitness Classes");
            event85.setStartTime(17 + ":" + 00);
            event85.setEndTime(18 + ":" + 00);
            event85.setGroupName("Mueller Center Classes");
            events.add(event85.toWeekViewEvent());

            Event event71 = new Event();
            String randomRGB71 = Constants.getRandomColorRGB();
            event71.setColor("#d0" + randomRGB71);
            event71.setTextColor("#d0424242");
            event71.setMonth(3);
            event71.setDayOfMonth(27);
            event71.setName("Fitness Class Fall 2018");
            event71.setStartTime(12 + ":" + 00);
            event71.setEndTime(13 + ":" + 00);
            event71.setGroupName("Mueller Center Classes");
            events.add(event71.toWeekViewEvent());

            Event event92 = new Event();
            String randomRGB92 = Constants.getRandomColorRGB();
            event92.setColor("#d0" + randomRGB92);
            event92.setTextColor("#d0424242");
            event92.setMonth(3);
            event92.setDayOfMonth(26);
            event92.setName("Aikido Practice");
            event92.setStartTime(20 + ":" + 00);
            event92.setEndTime(21 + ":" + 45);
            event92.setGroupName("Aikido");
            events.add(event92.toWeekViewEvent());

            Event event78 = new Event();
            String randomRGB78 = Constants.getRandomColorRGB();
            event78.setColor("#d0" + randomRGB78);
            event78.setTextColor("#d0424242");
            event78.setMonth(3);
            event78.setDayOfMonth(26);
            event78.setName("Fitness Class Fall 2018");
            event78.setStartTime(12 + ":" + 00);
            event78.setEndTime(13 + ":" + 00);
            event78.setGroupName("Mueller Center Classes");
            events.add(event78.toWeekViewEvent());

            Event event89 = new Event();
            String randomRGB89 = Constants.getRandomColorRGB();
            event89.setColor("#d0" + randomRGB89);
            event89.setTextColor("#d0424242");
            event89.setMonth(3);
            event89.setDayOfMonth(26);
            event89.setName("Taekwondo Practice");
            event89.setStartTime(18 + ":" + 00);
            event89.setEndTime(20 + ":" + 00);
            event89.setGroupName("Sport Taekwondo");
            events.add(event89.toWeekViewEvent());

            Event event64 = new Event();
            String randomRGB64 = Constants.getRandomColorRGB();
            event64.setColor("#d0" + randomRGB64);
            event64.setTextColor("#d0424242");
            event64.setMonth(3);
            event64.setDayOfMonth(26);
            event64.setName("Dance Club");
            event64.setStartTime(18 + ":" + 30);
            event64.setEndTime(20 + ":" + 30);
            event64.setGroupName("Dance Club");
            events.add(event64.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Fitness Class Fall 2018");
            event1.setStartTime(12 + ":" + 00);
            event1.setEndTime(13 + ":" + 00);
            event1.setGroupName("Mueller Center Classes");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Fitness Classes");
            event2.setStartTime(17 + ":" + 00);
            event2.setEndTime(18 + ":" + 00);
            event2.setGroupName("Mueller Center Classes");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("RPI Kendo Club Practice");
            event3.setStartTime(18 + ":" + 30);
            event3.setEndTime(20 + ":" + 00);
            event3.setGroupName("Kendo Club");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(25);
            event4.setName("RPI Dance Team Practice");
            event4.setStartTime(20 + ":" + 15);
            event4.setEndTime(22 + ":" + 15);
            event4.setGroupName("Dance Team");
            events.add(event4.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Crew Club");
            event21_1.setStartTime(11 + ":" + 00);
            event21_1.setEndTime(12 + ":" + 00);
            event21_1.setGroupName("Mueller Center Classes");
            events.add(event21_1.toWeekViewEvent());

            Event event21_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event21_3.setColor("#d0" + randomRGB21_3);
            event21_3.setTextColor("#d0424242");
            event21_3.setMonth(3);
            event21_3.setDayOfMonth(21);
            event21_3.setName("Fitness Class Fall 2018");
            event21_3.setStartTime(12 + ":" + 00);
            event21_3.setEndTime(13 + ":" + 00);
            event21_3.setGroupName("Mueller Center Classes");
            events.add(event21_3.toWeekViewEvent());

            Event event5 = new Event();
            String randomRGB5 = Constants.getRandomColorRGB();
            event5.setColor("#d0" + randomRGB5);
            event5.setTextColor("#d0424242");
            event5.setMonth(3);
            event5.setDayOfMonth(23);
            event5.setName("RPI Bhangra");
            event5.setStartTime(21 + ":" + 00);
            event5.setEndTime(23 + ":" + 00);
            event5.setGroupName("RPI Bhangra");
            events.add(event5.toWeekViewEvent());

            Event event7 = new Event();
            String randomRGB7 = Constants.getRandomColorRGB();
            event7.setColor("#d0" + randomRGB7);
            event7.setTextColor("#d0424242");
            event7.setMonth(3);
            event7.setDayOfMonth(23);
            event7.setName("Fitness Class Fall 2018");
            event7.setStartTime(17 + ":" + 00);
            event7.setEndTime(18 + ":" + 00);
            event7.setGroupName("Mueller Center Classes");
            events.add(event7.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("Fitness Class Fall 2018");
            event8.setStartTime(19 + ":" + 00);
            event8.setEndTime(20 + ":" + 00);
            event8.setGroupName("Mueller Center Classes");
            events.add(event8.toWeekViewEvent());

            Event event9 = new Event();
            String randomRGB9 = Constants.getRandomColorRGB();
            event9.setColor("#d0" + randomRGB9);
            event9.setTextColor("#d0424242");
            event9.setMonth(3);
            event9.setDayOfMonth(23);
            event9.setName("Fitness Class Fall 2018");
            event9.setStartTime(12 + ":" + 00);
            event9.setEndTime(13 + ":" + 00);
            event9.setGroupName("Mueller Center Classes");
            events.add(event9.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Fitness Class Fall 2018");
            event11.setStartTime(12 + ":" + 00);
            event11.setEndTime(13 + ":" + 00);
            event11.setGroupName("Mueller Center Classes");
            events.add(event11.toWeekViewEvent());

            Event event12 = new Event();
            String randomRGB12 = Constants.getRandomColorRGB();
            event12.setColor("#d0" + randomRGB12);
            event12.setTextColor("#d0424242");
            event12.setMonth(3);
            event12.setDayOfMonth(24);
            event12.setName("Fitness Classes");
            event12.setStartTime(17 + ":" + 00);
            event12.setEndTime(18 + ":" + 00);
            event12.setGroupName("Mueller Center Classes");
            events.add(event12.toWeekViewEvent());

            Event event40 = new Event();
            String randomRGB40 = Constants.getRandomColorRGB();
            event40.setColor("#d0" + randomRGB40);
            event40.setTextColor("#d0424242");
            event40.setMonth(3);
            event40.setDayOfMonth(26);
            event40.setName("Fitness Classes");
            event40.setStartTime(17 + ":" + 00);
            event40.setEndTime(18 + ":" + 00);
            event40.setGroupName("Mueller Center Classes");
            events.add(event40.toWeekViewEvent());

            Event event30 = new Event();
            String randomRGB30 = Constants.getRandomColorRGB();
            event30.setColor("#d0" + randomRGB30);
            event30.setTextColor("#d0424242");
            event30.setMonth(3);
            event30.setDayOfMonth(27);
            event30.setName("Technique Hour");
            event30.setStartTime(7 + ":" + 00);
            event30.setEndTime(8 + ":" + 00);
            event30.setGroupName("Dance Team");
            events.add(event30.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Dance Practice");
            event22_2.setStartTime(13 + ":" + 15);
            event22_2.setEndTime(14 + ":" + 45);
            event22_2.setGroupName("Student Activities Special Events");
            events.add(event22_2.toWeekViewEvent());
        } else if (facility_name.equals("Multipurpose Room 2")) {
            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Fitness Class Fall 2018");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(13 + ":" + 00);
            event22_1.setGroupName("Mueller Center Classes");
            events.add(event22_1.toWeekViewEvent());

            Event event20 = new Event();
            String randomRGB20 = Constants.getRandomColorRGB();
            event20.setColor("#d0" + randomRGB20);
            event20.setTextColor("#d0424242");
            event20.setMonth(3);
            event20.setDayOfMonth(27);
            event20.setName("Dance Club");
            event20.setStartTime(19 + ":" + 00);
            event20.setEndTime(20 + ":" + 30);
            event20.setGroupName("Dance Club");
            events.add(event20.toWeekViewEvent());

            Event event94 = new Event();
            String randomRGB94 = Constants.getRandomColorRGB();
            event94.setColor("#d0" + randomRGB94);
            event94.setTextColor("#d0424242");
            event94.setMonth(3);
            event94.setDayOfMonth(27);
            event94.setName("Eighth Wonder");
            event94.setStartTime(18 + ":" + 00);
            event94.setEndTime(19 + ":" + 00);
            event94.setGroupName("Eighth Wonder");
            events.add(event94.toWeekViewEvent());

            Event event31 = new Event();
            String randomRGB31 = Constants.getRandomColorRGB();
            event31.setColor("#d0" + randomRGB31);
            event31.setTextColor("#d0424242");
            event31.setMonth(3);
            event31.setDayOfMonth(26);
            event31.setName("Fitness Classes");
            event31.setStartTime(17 + ":" + 00);
            event31.setEndTime(18 + ":" + 00);
            event31.setGroupName("Mueller Center Classes");
            events.add(event31.toWeekViewEvent());

            Event event79 = new Event();
            String randomRGB79 = Constants.getRandomColorRGB();
            event79.setColor("#d0" + randomRGB79);
            event79.setTextColor("#d0424242");
            event79.setMonth(3);
            event79.setDayOfMonth(27);
            event79.setName("Fitness Class Fall 2018");
            event79.setStartTime(12 + ":" + 00);
            event79.setEndTime(13 + ":" + 00);
            event79.setGroupName("Mueller Center Classes");
            events.add(event79.toWeekViewEvent());

            Event event30 = new Event();
            String randomRGB30 = Constants.getRandomColorRGB();
            event30.setColor("#d0" + randomRGB30);
            event30.setTextColor("#d0424242");
            event30.setMonth(3);
            event30.setDayOfMonth(27);
            event30.setName("Technique Hour");
            event30.setStartTime(7 + ":" + 00);
            event30.setEndTime(8 + ":" + 00);
            event30.setGroupName("Dance Team");
            events.add(event30.toWeekViewEvent());

            Event event35 = new Event();
            String randomRGB35 = Constants.getRandomColorRGB();
            event35.setColor("#d0" + randomRGB35);
            event35.setTextColor("#d0424242");
            event35.setMonth(3);
            event35.setDayOfMonth(26);
            event35.setName("Capoeira");
            event35.setStartTime(20 + ":" + 00);
            event35.setEndTime(22 + ":" + 00);
            event35.setGroupName("Capoeira");
            events.add(event35.toWeekViewEvent());

            Event event69 = new Event();
            String randomRGB69 = Constants.getRandomColorRGB();
            event69.setColor("#d0" + randomRGB69);
            event69.setTextColor("#d0424242");
            event69.setMonth(3);
            event69.setDayOfMonth(26);
            event69.setName("Fitness Class Fall 2018");
            event69.setStartTime(12 + ":" + 00);
            event69.setEndTime(13 + ":" + 00);
            event69.setGroupName("Mueller Center Classes");
            events.add(event69.toWeekViewEvent());

            Event event75 = new Event();
            String randomRGB75 = Constants.getRandomColorRGB();
            event75.setColor("#d0" + randomRGB75);
            event75.setTextColor("#d0424242");
            event75.setMonth(3);
            event75.setDayOfMonth(26);
            event75.setName("Fitness Classes");
            event75.setStartTime(17 + ":" + 00);
            event75.setEndTime(18 + ":" + 00);
            event75.setGroupName("Mueller Center Classes");
            events.add(event75.toWeekViewEvent());

            Event event89 = new Event();
            String randomRGB89 = Constants.getRandomColorRGB();
            event89.setColor("#d0" + randomRGB89);
            event89.setTextColor("#d0424242");
            event89.setMonth(3);
            event89.setDayOfMonth(26);
            event89.setName("Taekwondo Practice");
            event89.setStartTime(18 + ":" + 00);
            event89.setEndTime(20 + ":" + 00);
            event89.setGroupName("Sport Taekwondo");
            events.add(event89.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Fitness Class Fall 2018");
            event1.setStartTime(12 + ":" + 00);
            event1.setEndTime(13 + ":" + 00);
            event1.setGroupName("Mueller Center Classes");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Fitness Classes");
            event2.setStartTime(17 + ":" + 00);
            event2.setEndTime(18 + ":" + 00);
            event2.setGroupName("Mueller Center Classes");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Dance Club");
            event3.setStartTime(18 + ":" + 15);
            event3.setEndTime(20 + ":" + 00);
            event3.setGroupName("Dance Club");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(25);
            event4.setName("RPI Dance Team Practice");
            event4.setStartTime(20 + ":" + 15);
            event4.setEndTime(22 + ":" + 15);
            event4.setGroupName("Dance Team");
            events.add(event4.toWeekViewEvent());

            Event event21_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event21_3.setColor("#d0" + randomRGB21_3);
            event21_3.setTextColor("#d0424242");
            event21_3.setMonth(3);
            event21_3.setDayOfMonth(21);
            event21_3.setName("Fitness Class Fall 2018");
            event21_3.setStartTime(12 + ":" + 00);
            event21_3.setEndTime(13 + ":" + 00);
            event21_3.setGroupName("Mueller Center Classes");
            events.add(event21_3.toWeekViewEvent());

            Event event7 = new Event();
            String randomRGB7 = Constants.getRandomColorRGB();
            event7.setColor("#d0" + randomRGB7);
            event7.setTextColor("#d0424242");
            event7.setMonth(3);
            event7.setDayOfMonth(23);
            event7.setName("Fitness Class Fall 2018");
            event7.setStartTime(17 + ":" + 00);
            event7.setEndTime(18 + ":" + 00);
            event7.setGroupName("Mueller Center Classes");
            events.add(event7.toWeekViewEvent());

            Event event9 = new Event();
            String randomRGB9 = Constants.getRandomColorRGB();
            event9.setColor("#d0" + randomRGB9);
            event9.setTextColor("#d0424242");
            event9.setMonth(3);
            event9.setDayOfMonth(23);
            event9.setName("Fitness Class Fall 2018");
            event9.setStartTime(12 + ":" + 00);
            event9.setEndTime(13 + ":" + 00);
            event9.setGroupName("Mueller Center Classes");
            events.add(event9.toWeekViewEvent());

            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Dance Practice");
            event22_2.setStartTime(13 + ":" + 15);
            event22_2.setEndTime(14 + ":" + 45);
            event22_2.setGroupName("Student Activities Special Events");
            events.add(event22_2.toWeekViewEvent());


            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Fitness Class Fall 2018");
            event11.setStartTime(12 + ":" + 00);
            event11.setEndTime(13 + ":" + 00);
            event11.setGroupName("Mueller Center Classes");
            events.add(event11.toWeekViewEvent());

            Event event12 = new Event();
            String randomRGB12 = Constants.getRandomColorRGB();
            event12.setColor("#d0" + randomRGB12);
            event12.setTextColor("#d0424242");
            event12.setMonth(3);
            event12.setDayOfMonth(24);
            event12.setName("Fitness Classes");
            event12.setStartTime(17 + ":" + 00);
            event12.setEndTime(18 + ":" + 00);
            event12.setGroupName("Mueller Center Classes");
            events.add(event12.toWeekViewEvent());
        } else if (facility_name.equals("Multipurpose Room 3")) {
            Event event22_2 = new Event();
            String randomRGB21_2 = Constants.getRandomColorRGB();
            event22_2.setColor("#d0" + randomRGB21_2);
            event22_2.setTextColor("#d0424242");
            event22_2.setMonth(3);
            event22_2.setDayOfMonth(21);
            event22_2.setName("Boxing Club Practice");
            event22_2.setStartTime(16 + ":" + 00);
            event22_2.setEndTime(18 + ":" + 00);
            event22_2.setGroupName("General Non-Club Meetings");
            events.add(event22_2.toWeekViewEvent());

            Event event22_3 = new Event();
            String randomRGB21_3 = Constants.getRandomColorRGB();
            event22_3.setColor("#d0" + randomRGB21_3);
            event22_3.setTextColor("#d0424242");
            event22_3.setMonth(3);
            event22_3.setDayOfMonth(21);
            event22_3.setName("Recital rehersals");
            event22_3.setStartTime(18 + ":" + 00);
            event22_3.setEndTime(20 + ":" + 00);
            event22_3.setGroupName("Dance Club");
            events.add(event22_3.toWeekViewEvent());

            Event event22_1 = new Event();
            String randomRGB22_1 = Constants.getRandomColorRGB();
            event22_1.setColor("#d0" + randomRGB22_1);
            event22_1.setTextColor("#d0424242");
            event22_1.setMonth(3);
            event22_1.setDayOfMonth(22);
            event22_1.setName("Fitness Class Fall 2018");
            event22_1.setStartTime(12 + ":" + 00);
            event22_1.setEndTime(13 + ":" + 00);
            event22_1.setGroupName("Mueller Center Classes");
            events.add(event22_1.toWeekViewEvent());

            Event event67 = new Event();
            String randomRGB67 = Constants.getRandomColorRGB();
            event67.setColor("#d0" + randomRGB67);
            event67.setTextColor("#d0424242");
            event67.setMonth(3);
            event67.setDayOfMonth(27);
            event67.setName("RPI Sangam Practice");
            event67.setStartTime(18 + ":" + 00);
            event67.setEndTime(20 + ":" + 00);
            event67.setGroupName("Indian Student Association");
            events.add(event67.toWeekViewEvent());

            Event event79 = new Event();
            String randomRGB79 = Constants.getRandomColorRGB();
            event79.setColor("#d0" + randomRGB79);
            event79.setTextColor("#d0424242");
            event79.setMonth(3);
            event79.setDayOfMonth(27);
            event79.setName("Fitness Classes");
            event79.setStartTime(17 + ":" + 00);
            event79.setEndTime(18 + ":" + 00);
            event79.setGroupName("Mueller Center Classes");
            events.add(event79.toWeekViewEvent());

            Event event96 = new Event();
            String randomRGB96 = Constants.getRandomColorRGB();
            event96.setColor("#d0" + randomRGB96);
            event96.setTextColor("#d0424242");
            event96.setMonth(3);
            event96.setDayOfMonth(27);
            event96.setName("Fitness Class Fall 2018");
            event96.setStartTime(12 + ":" + 00);
            event96.setEndTime(13 + ":" + 00);
            event96.setGroupName("Mueller Center Classes");
            events.add(event96.toWeekViewEvent());

            Event event59 = new Event();
            String randomRGB59 = Constants.getRandomColorRGB();
            event59.setColor("#d0" + randomRGB59);
            event59.setTextColor("#d0424242");
            event59.setMonth(3);
            event59.setDayOfMonth(26);
            event59.setName("Fitness Classes");
            event59.setStartTime(17 + ":" + 00);
            event59.setEndTime(18 + ":" + 00);
            event59.setGroupName("Mueller Center Classes");
            events.add(event59.toWeekViewEvent());

            Event event1 = new Event();
            String randomRGB = Constants.getRandomColorRGB();
            event1.setColor("#d0" + randomRGB);
            event1.setTextColor("#d0424242");
            event1.setMonth(3);
            event1.setDayOfMonth(25);
            event1.setName("Fitness Class Fall 2018");
            event1.setStartTime(17 + ":" + 00);
            event1.setEndTime(18 + ":" + 00);
            event1.setGroupName("Mueller Center Classes");
            events.add(event1.toWeekViewEvent());

            Event event2 = new Event();
            String randomRGB2 = Constants.getRandomColorRGB();
            event2.setColor("#d0" + randomRGB2);
            event2.setTextColor("#d0424242");
            event2.setMonth(3);
            event2.setDayOfMonth(25);
            event2.setName("Boxing Club Practice");
            event2.setStartTime(18 + ":" + 00);
            event2.setEndTime(19 + ":" + 00);
            event2.setGroupName("General Non-Club Meetings");
            events.add(event2.toWeekViewEvent());

            Event event3 = new Event();
            String randomRGB3 = Constants.getRandomColorRGB();
            event3.setColor("#d0" + randomRGB3);
            event3.setTextColor("#d0424242");
            event3.setMonth(3);
            event3.setDayOfMonth(25);
            event3.setName("Capoeira");
            event3.setStartTime(19 + ":" + 00);
            event3.setEndTime(20 + ":" + 00);
            event3.setGroupName("Capoeira");
            events.add(event3.toWeekViewEvent());

            Event event4 = new Event();
            String randomRGB4 = Constants.getRandomColorRGB();
            event4.setColor("#d0" + randomRGB4);
            event4.setTextColor("#d0424242");
            event4.setMonth(3);
            event4.setDayOfMonth(25);
            event4.setName("Bollywood Fusion Practice");
            event4.setStartTime(20 + ":" + 00);
            event4.setEndTime(22 + ":" + 00);
            event4.setGroupName("RPI Bhangra");
            events.add(event4.toWeekViewEvent());

            Event event7 = new Event();
            String randomRGB7 = Constants.getRandomColorRGB();
            event7.setColor("#d0" + randomRGB7);
            event7.setTextColor("#d0424242");
            event7.setMonth(3);
            event7.setDayOfMonth(23);
            event7.setName("Fitness Class Fall 2018");
            event7.setStartTime(17 + ":" + 00);
            event7.setEndTime(18 + ":" + 00);
            event7.setGroupName("Mueller Center Classes");
            events.add(event7.toWeekViewEvent());

            Event event8 = new Event();
            String randomRGB8 = Constants.getRandomColorRGB();
            event8.setColor("#d0" + randomRGB8);
            event8.setTextColor("#d0424242");
            event8.setMonth(3);
            event8.setDayOfMonth(23);
            event8.setName("Fitness Class Fall 2018");
            event8.setStartTime(19 + ":" + 00);
            event8.setEndTime(20 + ":" + 00);
            event8.setGroupName("Mueller Center Classes");
            events.add(event8.toWeekViewEvent());

            Event event9 = new Event();
            String randomRGB9 = Constants.getRandomColorRGB();
            event9.setColor("#d0" + randomRGB9);
            event9.setTextColor("#d0424242");
            event9.setMonth(3);
            event9.setDayOfMonth(23);
            event9.setName("Fitness Class Fall 2018");
            event9.setStartTime(12 + ":" + 00);
            event9.setEndTime(13 + ":" + 00);
            event9.setGroupName("Mueller Center Classes");
            events.add(event9.toWeekViewEvent());

            Event event11 = new Event();
            String randomRGB11 = Constants.getRandomColorRGB();
            event11.setColor("#d0" + randomRGB11);
            event11.setTextColor("#d0424242");
            event11.setMonth(3);
            event11.setDayOfMonth(24);
            event11.setName("Fitness Class Fall 2018");
            event11.setStartTime(12 + ":" + 00);
            event11.setEndTime(13 + ":" + 00);
            event11.setGroupName("Mueller Center Classes");
            events.add(event11.toWeekViewEvent());

            Event event12 = new Event();
            String randomRGB12 = Constants.getRandomColorRGB();
            event12.setColor("#d0" + randomRGB12);
            event12.setTextColor("#d0424242");
            event12.setMonth(3);
            event12.setDayOfMonth(24);
            event12.setName("Fitness Classes");
            event12.setStartTime(17 + ":" + 00);
            event12.setEndTime(18 + ":" + 00);
            event12.setGroupName("Mueller Center Classes");
            events.add(event12.toWeekViewEvent());

            Event event69 = new Event();
            String randomRGB69 = Constants.getRandomColorRGB();
            event69.setColor("#d0" + randomRGB69);
            event69.setTextColor("#d0424242");
            event69.setMonth(3);
            event69.setDayOfMonth(26);
            event69.setName("Fitness Class Fall 2018");
            event69.setStartTime(7 + ":" + 00);
            event69.setEndTime(8 + ":" + 00);
            event69.setGroupName("Mueller Center Classes");
            events.add(event69.toWeekViewEvent());

            Event event71 = new Event();
            String randomRGB71 = Constants.getRandomColorRGB();
            event71.setColor("#d0" + randomRGB71);
            event71.setTextColor("#d0424242");
            event71.setMonth(3);
            event71.setDayOfMonth(26);
            event71.setName("Fitness Class Fall 2018");
            event71.setStartTime(12 + ":" + 00);
            event71.setEndTime(13 + ":" + 00);
            event71.setGroupName("Mueller Center Classes");
            events.add(event71.toWeekViewEvent());

            Event event21_1 = new Event();
            String randomRGB21_1 = Constants.getRandomColorRGB();
            event21_1.setColor("#d0" + randomRGB21_1);
            event21_1.setTextColor("#d0424242");
            event21_1.setMonth(3);
            event21_1.setDayOfMonth(21);
            event21_1.setName("Boxing Club Introductory Class");
            event21_1.setStartTime(9 + ":" + 00);
            event21_1.setEndTime(10 + ":" + 00);
            event21_1.setGroupName("General Non-Club Meetings");
            events.add(event21_1.toWeekViewEvent());

            Event event21_2 = new Event();
            randomRGB21_2 = Constants.getRandomColorRGB();
            event21_2.setColor("#d0" + randomRGB21_2);
            event21_2.setTextColor("#d0424242");
            event21_2.setMonth(3);
            event21_2.setDayOfMonth(21);
            event21_2.setName("Boxing Club Practice");
            event21_2.setStartTime(10 + ":" + 00);
            event21_2.setEndTime(12 + ":" + 00);
            event21_2.setGroupName("General Non-Club Meetings");
            events.add(event21_2.toWeekViewEvent());

            Event event21_3 = new Event();
            randomRGB21_3 = Constants.getRandomColorRGB();
            event21_3.setColor("#d0" + randomRGB21_3);
            event21_3.setTextColor("#d0424242");
            event21_3.setMonth(3);
            event21_3.setDayOfMonth(21);
            event21_3.setName("Fitness Class Fall 2018");
            event21_3.setStartTime(12 + ":" + 00);
            event21_3.setEndTime(13 + ":" + 00);
            event21_3.setGroupName("Mueller Center Classes");
            events.add(event21_3.toWeekViewEvent());

            Event event21_4 = new Event();
            String randomRGB21_4 = Constants.getRandomColorRGB();
            event21_4.setColor("#d0" + randomRGB21_4);
            event21_4.setTextColor("#d0424242");
            event21_4.setMonth(3);
            event21_4.setDayOfMonth(21);
            event21_4.setName("RPI Kendo Club Practice");
            event21_4.setStartTime(13 + ":" + 00);
            event21_4.setEndTime(15 + ":" + 00);
            event21_4.setGroupName("Kendo Club");
            events.add(event21_4.toWeekViewEvent());
        }
        mWeekView.notifyDatasetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_facility, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_select_date:
                showDateDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (window != null && window.isShowing()) {
            window.dismiss();
        } else {
            finish();
        }
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    private void initCalendarView() {
        OnSelectDateListener onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                if (tv_date != null) {
                    String month = date.getMonth() < 10 ? "0" + date.getMonth() : "" + date.getMonth();
                    tv_date.setText(String.format(Locale.ENGLISH, "%s / %d", month, date.getYear()));
                }
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, date.getYear());
                calendar.set(Calendar.MONTH, date.getMonth() - 1);
                calendar.set(Calendar.DAY_OF_MONTH, date.getDay());
                mWeekView.goToDate(calendar);
                if (window != null) window.dismiss();
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                monthPager.selectOtherMonth(offset);
            }
        };
        CustomDayView customDayView = new CustomDayView(this, R.layout.custom_day);
        calendarAdapter = new CalendarViewAdapter(this, onSelectDateListener, CalendarAttr.WeekArrayType.Monday, customDayView);
        calendarAdapter.setOnCalendarTypeChangedListener(new CalendarViewAdapter.OnCalendarTypeChanged() {
            @Override
            public void onCalendarTypeChanged(CalendarAttr.CalendarType type) {
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
                ArrayList<com.envision.calendar.view.Calendar> currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) != null) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    if (tv_date != null) {
                        String month = date.getMonth() < 10 ? "0" + date.getMonth() : "" + date.getMonth();
                        tv_date.setText(String.format(Locale.ENGLISH, "%s / %d", month, date.getYear()));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void showDateDialog() {
        if (window == null) {
            window = new PopupWindow(this);
            window.setBackgroundDrawable(null);
            @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_select_date, null, true);
            monthPager = view.findViewById(R.id.calendar_view);
            tv_date = view.findViewById(R.id.tv_date);
            monthPager.setViewHeight(Utils.dpi2px(this, 270));
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            window.setWidth(metrics.widthPixels);
            initCalendarView();
            window.setContentView(view);
            CalendarDate today = new CalendarDate();
            String month = today.getMonth() < 10 ? "0" + today.getMonth() : "" + today.getMonth();
            tv_date.setText(String.format(Locale.ENGLISH, "%s / %d", month, today.getYear()));
            calendarAdapter.notifyDataChanged(today);
            window.setOutsideTouchable(true);
            window.setFocusable(true);
        }
        window.showAsDropDown(findViewById(R.id.toolbar));
    }
}
