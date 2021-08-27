package com.cos.better.view.calender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cos.better.R;

// 이후 이름 수정해야함!!!! + xml 도 마찬가지
public class AddScheduleActivity extends Activity {

    private static final String TAG = "AddScheduleActivity";

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        TextView tvCalender = findViewById(R.id.tvCalender);
        String date = getIntent().getStringExtra("date");
        tvCalender.setText(date);
    }


}