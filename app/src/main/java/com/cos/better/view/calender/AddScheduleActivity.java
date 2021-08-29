package com.cos.better.view.calender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.google.android.material.textfield.TextInputEditText;

public class AddScheduleActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "AddScheduleActivity";
    private Context mContext = AddScheduleActivity.this;

    private ImageView ivCancel, ivSave;
    private TextView tvStartDate, tvEndDate;
    private TextInputEditText tfTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        init();
        initLr();
    }

    @Override
    public void init() {
        ivCancel = findViewById(R.id.ivCancel);
        ivSave = findViewById(R.id.ivSave);
        tfTitle = findViewById(R.id.tfTitle);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
    }

    @Override
    public void initLr() {
        ivCancel.setOnClickListener(v->{
            finish();
        });
        ivSave.setOnClickListener(v->{
            // 아무것도 안하고 저장하려고 하면 막아야됨
        });
        tvStartDate.setOnClickListener(v->{
            Log.d(TAG, "initLr: tvStartDate clicked");
        });
        tvEndDate.setOnClickListener(v->{
            Log.d(TAG, "initLr: tvEndDate clicked");
        });

    }

    @Override
    public void initSetting() {

    }

    @Override
    public void initData() {

    }
}