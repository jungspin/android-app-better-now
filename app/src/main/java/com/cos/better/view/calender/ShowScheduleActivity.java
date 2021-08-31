package com.cos.better.view.calender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderTestDTO;
import com.cos.better.view.HomeActivity;

// 이후 이름 수정해야함!!!! + xml 도 마찬가지
public class ShowScheduleActivity extends Activity implements InitSetting {

    private static final String TAG = "ShowScheduleActivity";
    private Context mContext = ShowScheduleActivity.this;

    private AppCompatButton btnAddSchedule;
    private TextView tvDetailSchedule;

    private ActivityResultLauncher<Intent> activityResultLauncher;



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
            startActivityForResult(intent, 1000);


        });


    }

    // 테스트용입니다!!!!!!!!
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 || requestCode == RESULT_OK){
            CalenderTestDTO calenderTestDTO = (CalenderTestDTO) data.getSerializableExtra("schedule");
            tvDetailSchedule.append(calenderTestDTO.getTitle());
            tvDetailSchedule.append(calenderTestDTO.getStartDate());
            tvDetailSchedule.append(calenderTestDTO.getEndDate());
            //data.getParcelableArrayListExtra("calendarDays");
           // Log.d(TAG, "onActivityResult: " + data.getParcelableArrayListExtra("calendarDays"));
        } else {
            Log.d(TAG, "onActivityResult: 그냥 닫으면 안됑");
        }
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