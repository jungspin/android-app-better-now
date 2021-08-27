package com.cos.better.view.calender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.prolificinteractive.materialcalendarview.CalendarDay;

// 이후 이름 수정해야함!!!! + xml 도 마찬가지
public class AddScheduleActivity extends Activity implements InitSetting {

    private static final String TAG = "AddScheduleActivity";
    private Context mContext = AddScheduleActivity.this;

    private CalendarDay calendarView;

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);







    }


    @Override
    public void init() {

    }

    @Override
    public void initLr() {

    }

    @Override
    public void initSetting() {


    }

    @Override
    public void initData() {

    }
}