package com.envision.scheduler.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.envision.scheduler.Constants;
import com.envision.scheduler.R;

/**
 * TODO
 *
 * @author shizhi
 * @version V 1.0
 * @date 29/3/2018 下午7:03
 */
public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_event_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String event_name = getIntent().getStringExtra(Constants.KEY_TITLE);
        String time_date = getIntent().getStringExtra(Constants.KEY_TIME_DATE);
        String location = getIntent().getStringExtra(Constants.KEY_LOCATION);
        String group = getIntent().getStringExtra(Constants.KEY_GROUP);
        String events_coordinator = getIntent().getStringExtra(Constants.KEY_EVENTS_COORDINATOR);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(event_name);
        }
        ((TextView)findViewById(R.id.tv_event_name)).setText(event_name);
        ((TextView)findViewById(R.id.tv_time_date)).setText(time_date);
        ((TextView)findViewById(R.id.tv_location)).setText(location);
        ((TextView)findViewById(R.id.tv_group)).setText(group);
        ((TextView)findViewById(R.id.tv_events_coordinator)).setText(events_coordinator);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
