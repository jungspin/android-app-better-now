package com.cos.better.view.habit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.view.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class AddHabitActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "AddHabitActivity";
    private Context mContext = this;
    private Button btnClose, btnBack;
    private AppCompatButton btnHabitText,btnComplete;
    private FloatingActionButton btnAddAlarm;
    private RadioGroup rgHabit;
    private LinearLayoutCompat container;
    private int alarmHour = -1, alarmMinute = -1;
    private int alarmCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        init();
        initData();
        initLr();

    }

    public void createAlarmTv(int alarmHour, int alarmMinute){
        //텍스트뷰 생성
        TextView timeView = new TextView(this);

        if(alarmHour >= 10){
            if(alarmMinute >=10)
            timeView.setText(alarmHour+":"+alarmMinute);
            else
                timeView.setText(alarmHour+":0"+alarmMinute);
        }else if(alarmHour <10 ){
            if(alarmMinute >=10)
                timeView.setText("0"+alarmHour+":"+alarmMinute);
            else{
                timeView.setText("0"+alarmHour+":0"+alarmMinute);
            }
        }
        timeView.setTextSize(20);

        LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        lp.setMargins(30,0,0,0);
        timeView.setLayoutParams(lp);
        container.addView(timeView);
    }

    @Override
    public void init() {
        btnClose = findViewById(R.id.btnClose);
        btnBack = findViewById(R.id.btnBack);
        btnHabitText = findViewById(R.id.btnHabitText);
        btnAddAlarm = findViewById(R.id.btnAddAlarm);
        btnComplete = findViewById(R.id.btnComplete);
        rgHabit = findViewById(R.id.rgHabit);
        container = findViewById(R.id.container);
    }

    @Override
    public void initLr() {
        btnClose.setOnClickListener(v ->{
          finish();
        });
        btnBack.setOnClickListener(v ->{
            finish();
        });

        rgHabit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rBtnEveryDay:
                        Log.d(TAG, "onCheckedChanged: 매일");


                        break;
                    case R.id.rBtnEveryWeek:
                        Log.d(TAG, "onCheckedChanged: 매주");
                        break;
                    case R.id.rBtnSetDay:
                        Log.d(TAG, "onCheckedChanged: 매달");
                        break;
                }
            }
        });

        btnAddAlarm.setOnClickListener(v -> {
            alarmHour = 0;
            alarmMinute = 0;
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddHabitActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth , new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    alarmHour = hourOfDay;
                    alarmMinute = minute;
                    if(alarmCount<5) {
                        createAlarmTv(alarmHour, alarmMinute);
                    }
                    else{
                        //토스트메시지
                    }
                    alarmCount++;
                    Log.d(TAG, "onTimeSet: "+alarmCount);
                }
            },alarmHour,alarmMinute,true);
            timePickerDialog.show();

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