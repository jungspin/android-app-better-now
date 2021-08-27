package com.cos.better.view.habit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.view.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddHabitActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "AddHabitActivity";
    private Context mContext = this;
    private Button btnClose, btnBack;
    private AppCompatButton btnHabitText,btnComplete;
    private FloatingActionButton btnAddAlarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        init();
        initData();
        initLr();

    }

    @Override
    public void init() {
        btnClose = findViewById(R.id.btnClose);
        btnBack = findViewById(R.id.btnBack);
        btnHabitText = findViewById(R.id.btnHabitText);
        btnAddAlarm = findViewById(R.id.btnAddAlarm);
        btnComplete = findViewById(R.id.btnComplete);
    }

    @Override
    public void initLr() {
        btnClose.setOnClickListener(v ->{
          finish();
        });
        btnBack.setOnClickListener(v ->{
            finish();
        });

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
       String categoryName = intent.getStringExtra("categoryName");
       String category = intent.getStringExtra(categoryName);
       btnHabitText.setText(category);
    }


}