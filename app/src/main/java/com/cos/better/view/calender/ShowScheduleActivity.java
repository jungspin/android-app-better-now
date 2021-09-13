package com.cos.better.view.calender;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.better.R;
import com.cos.better.config.CalendarScheduleAdapter;
import com.cos.better.config.CustomDate;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.model.Diary;
import com.cos.better.view.diary.DetailDiaryActivity;
import com.cos.better.viewModel.CalenderListViewModel;
import com.cos.better.viewModel.DiaryListViewModel;
import com.cos.better.viewModel.DiaryViewModel;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;

// 이후 이름 수정해야함!!!! + xml 도 마찬가지
public class ShowScheduleActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "ShowScheduleActivity";
    private Context mContext = ShowScheduleActivity.this;

    private AppCompatButton btnAddSchedule;
    private TextView tvShowAll, tvShowDiary, tvShowHabit;
    private MaterialCardView card;
    private RecyclerView rvSchedules;
    private RecyclerView.LayoutManager layoutManager; // 리니어 레이아웃은 방향이 있음. 방향 설정을 위해
    private CalendarScheduleAdapter calendarScheduleAdapter; // 얘는 내가 띄워야함

    private DiaryViewModel dvm;
    private DiaryListViewModel vm;
    private CalenderListViewModel clv;

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
        initAdapter();
        initSetting();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void init() {
        tvShowAll = findViewById(R.id.tvShowAll);
        btnAddSchedule = findViewById(R.id.btnAddSchedule);
        tvShowDiary = findViewById(R.id.tvShowDiary);
        tvShowHabit = findViewById(R.id.tvShowHabit);
        rvSchedules = findViewById(R.id.rvSchedules);
        card = findViewById(R.id.card);


    }

    @Override
    public void initLr() {

        btnAddSchedule.setOnClickListener(v->{
            Intent intent = new Intent(mContext, AddScheduleActivity.class);
            CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
            intent.putExtra("date", date);
            startActivity(intent);
        });

        card.setOnClickListener(v->{
            CustomDate putDate = (CustomDate) getIntent().getSerializableExtra("date");
            Intent intent = new Intent(mContext, DetailDiaryActivity.class);
            intent.putExtra("date", putDate);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }

    @Override
    public void initAdapter() {
        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvSchedules.setLayoutManager(layoutManager);
        calendarScheduleAdapter = new CalendarScheduleAdapter();
        rvSchedules.setAdapter(calendarScheduleAdapter); // 어댑터와 리사이클러뷰 연결
    }

    @Override
    public void initSetting() {
        card.setVisibility(View.INVISIBLE);

        CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
        Log.d(TAG, "initSetting: getSerializableExtra(\"date\") : " + date.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        String s = sdf.format(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()).getDate());
        Log.d(TAG, "SimpleDateFormat: " + s);
        tvShowAll.setText(s);

       //vm = new ViewModelProvider((ShowScheduleActivity)mContext).get(DiaryShowScheduleViewModel.class);
        clv = new ViewModelProvider((ShowScheduleActivity)mContext).get(CalenderListViewModel.class);
        dvm = new ViewModelProvider((ShowScheduleActivity)mContext).get(DiaryViewModel.class);


    }

    @Override
    public void initData() {
        CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
        Log.d(TAG, "CalenderDayDTO: " + date);

        Log.d(TAG, "initData: CalendarDay : " + CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));

        // 일정 리스트
        clv.findSelected(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));
        clv.getMdCalenderList().observe((ShowScheduleActivity)mContext, data -> {
            if (data == null){
                Log.d(TAG, "initData: data null");
            } else {
                Log.d(TAG, "initData: data : " + data.size());
                calendarScheduleAdapter.addItems(data);
            }

        });

        // 일기 리스트
        dvm.findOne(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));
        dvm.getDiary().observe((ShowScheduleActivity)mContext, data ->{
            if (data == null){
                Log.d(TAG, "initData: null " );
                card.setVisibility(View.INVISIBLE);
            } else {
                Log.d(TAG, "initData: " + data.getToday());
                tvShowDiary.setText(data.getTitle());
                card.setVisibility(View.VISIBLE);
            }

        });

    }




}