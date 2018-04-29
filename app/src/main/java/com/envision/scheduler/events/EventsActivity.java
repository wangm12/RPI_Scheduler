package com.envision.scheduler.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.envision.scheduler.Constants;
import com.envision.scheduler.R;
import com.envision.scheduler.base.BaseRecyclerAdapter;
import com.envision.scheduler.beans.TodayEventBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.envision.scheduler.Constants.KEY_TITLE;
import static com.envision.scheduler.Constants.getEventBean2;

/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 29/3/2018 下午7:03
 */
public class EventsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<TodayEventBean> data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //addGroupNamexxx();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getIntent().getStringExtra(Constants.KEY_TITLE));
        }
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // gaizhege
        data = getEventBean2();



        adapter = new EventAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(EventsActivity.this, EventDetailActivity.class);
                intent.putExtra(Constants.KEY_TITLE, data.get(position).eventName);
                String startHour = data.get(position).start_time;
                //System.out.println(startHour);
                String endHour = data.get(position).end_time;
                String time_date = startHour + " --- " + endHour;
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMM dd'th', yyyy", Locale.ENGLISH);
                intent.putExtra(Constants.KEY_TIME_DATE, time_date + "\n" +  format.format(new Date().getTime()));
                intent.putExtra(Constants.KEY_LOCATION, data.get(position).getLocation());
                //intent.putExtra(Constants.KEY_GROUP, "GROUP NAME (TEST)");
                String groupName = data.get(position).GroupName;
                //intent.putExtra(Constants.KEY_GROUP, groupNamexxx.get((int )(Math.random() * 83)));
                intent.putExtra(Constants.KEY_GROUP, groupName);
                intent.putExtra(Constants.KEY_EVENTS_COORDINATOR, "John Sturman");
                intent.putExtra(KEY_TITLE, data.get(position).eventName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
