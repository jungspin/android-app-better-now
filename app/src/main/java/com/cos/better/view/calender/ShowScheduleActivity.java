package com.cos.better.view.calender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.cos.better.R;
import com.cos.better.config.InitSetting;

// 이후 이름 수정해야함!!!! + xml 도 마찬가지
public class ShowScheduleActivity extends Activity implements InitSetting {

    private static final String TAG = "AddScheduleActivity";
    private Context mContext = ShowScheduleActivity.this;

    private AppCompatButton btnAddSchedule;
    private TextView tvDetailSchedule;



    @Override
    public Intent getIntent() {
        return super.getIntent();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);


        init();
        initLr();
        initSetting();
        initData();




    }


    @Override
    public void init() {
        tvDetailSchedule = findViewById(R.id.tvDetailSchedule);
        btnAddSchedule = findViewById(R.id.btnAddSchedule);

    }

    @Override
    public void initLr() {

        btnAddSchedule.setOnClickListener(v->{
            Intent intent = new Intent(mContext, AddScheduleActivity.class);
            startActivity(intent);
        });


    }

    @Override
    public void initSetting() {
        String date = getIntent().getStringExtra("date");
        tvDetailSchedule.setText(date);


    }

    @Override
    public void initData() {

    }
}