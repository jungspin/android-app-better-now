package com.cos.better.view.habit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cos.better.R;
import com.cos.better.config.InitSetting;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class AddHabitActivity extends AppCompatActivity implements InitSetting, dayFragment.MyWeekListener{

    private static final String TAG = "AddHabitActivity";
    private Context mContext = this;

    private TextInputEditText etTitle;
    private AppCompatButton btnHabitText,btnComplete;
    private FloatingActionButton btnAddAlarm;
    private RadioGroup rgHabit;
    private LinearLayoutCompat container,lyDate;
    private int alarmHour = -1, alarmMinute = -1, alarmCount=0, cycleCode = 0;
    private dayFragment dayFragment;
    private MonthDialog monthDialog;
    private MaterialButton btnSelectDate, btnBack;
    private Map<String, Object> habit = new HashMap<>();
    private FirebaseFirestore db;
    private String category;
    private String habitTitle;
    private String weekData, dayData, notification="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

//        AddHabitViewModel addHabitViewModel = new ViewModelProvider(this).get(AddHabitViewModel.class);
//
//        addHabitViewModel.init();
//        addHabitViewModel.getHabit().observe(this,changeHabit ->{
//
//        });
        init();
        initData();
        initLr();

    }

    public void createAlarmTv(int alarmHour, int alarmMinute){
        //텍스트뷰 생성
        TextView timeView = new TextView(mContext);

        if(alarmHour >= 10){
            if(alarmMinute >=10) {
                timeView.setText(alarmHour + ":" + alarmMinute);
                notification += alarmHour + ":" + alarmMinute+" ";
            }
            else {
                timeView.setText(alarmHour + ":0" + alarmMinute);
                notification += alarmHour + ":0" + alarmMinute+" ";
            }
        }else if(alarmHour <10 ){
            if(alarmMinute >=10) {
                timeView.setText("0" + alarmHour + ":" + alarmMinute);
                notification += "0" + alarmHour + ":" + alarmMinute+" ";
            }
            else{
                timeView.setText("0"+alarmHour+":0"+alarmMinute);
                notification += "0"+alarmHour+":0"+alarmMinute+" ";
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
        monthDialog = new MonthDialog(mContext, new MonthDialog.MyDayListener() {
            @Override
            public void onReceivedData(String data) {
                dayData = data;
                Log.d(TAG, "onReceivedData: "+dayData);
            }
        });
        dayFragment = new dayFragment(mContext);
        btnBack = findViewById(R.id.btnBack);
        btnHabitText = findViewById(R.id.btnHabitText);
        btnAddAlarm = findViewById(R.id.btnAddAlarm);
        btnComplete = findViewById(R.id.btnComplete);
        rgHabit = findViewById(R.id.rgHabit);
        container = findViewById(R.id.container);
        lyDate = findViewById(R.id.lyDate);
        btnSelectDate =findViewById(R.id.btnSelectDate);
        btnComplete = findViewById(R.id.btnComplete);
        etTitle = findViewById(R.id.etTitle);
    }

    @Override
    public void initLr() {
        btnSelectDate.setOnClickListener(v->{
            monthDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            monthDialog.setCanceledOnTouchOutside(true);
            monthDialog.setCancelable(true);
            monthDialog.show();
        });
        btnBack.setOnClickListener(v ->{
            finish();
        });

        rgHabit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rBtnEveryDay:
                        weekData ="";
                        cycleCode = 0;
                        Log.d(TAG, "onCheckedChanged: 매일");
                        getSupportFragmentManager().beginTransaction()
                                .remove(dayFragment)
                                .commit();
                        lyDate.setVisibility(View.GONE);
                        break;
                    case R.id.rBtnEveryWeek:
                        cycleCode = 1;
                        Log.d(TAG, "onCheckedChanged: 매주");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.WeekContainer,dayFragment)
                                .commit();
                        lyDate.setVisibility(View.GONE);
                        break;
                    case R.id.rBtnSetDay:
                        weekData ="";
                        cycleCode = 2;
                        Log.d(TAG, "onCheckedChanged: 매달");
                        getSupportFragmentManager().beginTransaction()
                                .remove(dayFragment)
                                .commit();
                        lyDate.setVisibility(View.VISIBLE);
                        monthDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                        monthDialog.setCanceledOnTouchOutside(true);
                        monthDialog.setCancelable(true);
                        monthDialog.show();
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
                        Toast.makeText(mContext, "알람은 5개까지만 설정이 가능합니다", Toast.LENGTH_SHORT).show();
                    }
                    alarmCount++;
                    //Log.d(TAG, "onTimeSet: "+alarmCount);
                }
            },alarmHour,alarmMinute,true);
            timePickerDialog.show();

        });

        btnComplete.setOnClickListener(v -> {
            habitTitle = etTitle.getText().toString();
            if(!habitTitle.equals("")) {
                finish();
            }else{
                Toast.makeText(mContext, "습관을 입력하세요", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
       category = intent.getStringExtra("categoryName");
       btnHabitText.setText(category);
    }

    public void addDb(){
        db = FirebaseFirestore.getInstance();
        habit.put("category",category);
        habit.put("habitTitle",habitTitle);
        habit.put("cycleCode",cycleCode);
        if(cycleCode == 0)
            habit.put("cycle","매일");
        else if(cycleCode ==1 ) {
            Log.d(TAG, "addDb: "+weekData);
            habit.put("cycle", weekData);
        }
        else if(cycleCode ==2) {
            Log.d(TAG, "addDb: "+dayData);
            habit.put("cycle", dayData);
        }
        habit.put("notification",notification);

        db.collection("habits")
                .add(habit)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: "+documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+e);
                    }
                });
    }

    @Override
    public void onReceivedData(String data) {
        weekData = data;
        Log.d(TAG, "onReceivedData: "+weekData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: activity onDestroy"+weekData);
        addDb();
    }
}