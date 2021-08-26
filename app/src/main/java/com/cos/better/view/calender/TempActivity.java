package com.cos.better.view.calender;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cos.better.R;

// 이후 이름 수정해야함!!!! + xml 도 마찬가지
public class TempActivity extends Activity {

    private static final String TAG = "TempActivity";

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        TextView tvCalender = findViewById(R.id.tvCalender);
        String date = getIntent().getStringExtra("date");
        tvCalender.setText(date);
    }
}