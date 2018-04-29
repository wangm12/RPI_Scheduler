package com.envision.scheduler.facilities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.envision.scheduler.Constants;
import com.envision.scheduler.R;
import com.envision.scheduler.base.BaseRecyclerAdapter;
import com.envision.scheduler.beans.FacilityBean;

import java.util.ArrayList;

import static com.envision.scheduler.Constants.KEY_FACILITIES;
import static com.envision.scheduler.Constants.KEY_TITLE;


/**
 * TODO
 *
 * @author shizhi
 * @version V 1.0
 * @date 28/3/2018 上午1:08
 */
public class FacilitiesActivity extends AppCompatActivity {
    String[] name = {"87' Gym",
            "Academy Hall",
            "Armory",
            "Athletic Field",
            "ECAV",
            "Muller Center",
            "Playhouse",
            "Student Union"};
    int[] res = {R.drawable.img_87_gym,
            R.drawable.img_academy_hall,
            R.drawable.img_armory,
            R.drawable.img_athletic_field,
            R.drawable.img_ecav,
            R.drawable.img_muller_center,
            R.drawable.img_playhouse,
            R.drawable.img_student_union};
    int[] array = {R.array.array_87_gym,
            R.array.array_academy_hall,
            R.array.array_armory,
            R.array.array_athletic_fields,
            R.array.array_ecav,
            R.array.array_muller_center,
            R.array.array_playhouse,
            R.array.array_student_union};
    private ArrayList<FacilityBean> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_facilities);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getIntent().getStringExtra(Constants.KEY_TITLE));
        }
        RecyclerView recyclerView = findViewById(R.id.rv_court);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < name.length; i++) {
            FacilityBean bean = new FacilityBean(res[i], name[i], array[i]);
            data.add(bean);
        }
        FacilityAdapter adapter = new FacilityAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(FacilitiesActivity.this, FacilityDetailActivity.class);
                intent.putExtra(KEY_TITLE, data.get(position).name);
                intent.putExtra(KEY_FACILITIES, data.get(position).array);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
