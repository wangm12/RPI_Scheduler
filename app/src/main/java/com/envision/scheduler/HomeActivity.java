package com.envision.scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.envision.scheduler.events.EventsActivity;
import com.envision.scheduler.facilities.FacilitiesActivity;
import com.envision.scheduler.time.SearchEventActivity;

import static com.envision.scheduler.Constants.KEY_TITLE;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        welcome(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.tv_facilities).setOnClickListener(this);
        findViewById(R.id.tv_time).setOnClickListener(this);
        findViewById(R.id.tv_events).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, FacilitiesActivity.class);
        switch (v.getId()) {
            case R.id.tv_facilities:
                intent.putExtra(KEY_TITLE, ((TextView)v).getText());
                startActivity(intent);
                break;
            case R.id.tv_time:
                intent = new Intent(this, SearchEventActivity.class);
                intent.putExtra(KEY_TITLE, ((TextView)v).getText());
                startActivity(intent);
                break;
            case R.id.tv_events:
                intent = new Intent(this, EventsActivity.class);
                intent.putExtra(KEY_TITLE, ((TextView)v).getText());
                startActivity(intent);
                break;
        }
    }
}
