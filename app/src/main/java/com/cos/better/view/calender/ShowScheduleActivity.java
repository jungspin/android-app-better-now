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
    private TextView tvShowAll, tvShowSchedule, tvShowDiary, tvShowHabit;
    private MaterialCardView card;
    private RecyclerView rvSchedules;
    private RecyclerView.LayoutManager layoutManager; // 리니어 레이아웃은 방향이 있음. 방향 설정을 위해
    private CalendarScheduleAdapter calendarScheduleAdapter; // 얘는 내가 띄워야함




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
        //tvShowSchedule = findViewById(R.id.tvShowSchedule);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        String s = sdf.format(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()).getDate());
        Log.d(TAG, "SimpleDateFormat: " + s);
        tvShowAll.setText(s);

       //vm = new ViewModelProvider((ShowScheduleActivity)mContext).get(DiaryShowScheduleViewModel.class);
        clv = new ViewModelProvider((ShowScheduleActivity)mContext).get(CalenderListViewModel.class);

    }

    @Override
    public void initData() {
        CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
        Log.d(TAG, "CalenderDayDTO: " + date);

        Log.d(TAG, "initData: CalendarDay : " + CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));


        findOne(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));

        clv.findSelected(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));
        clv.getMdCalenderList().observe((ShowScheduleActivity)mContext, data -> {
            if (data == null){
                Log.d(TAG, "initData: data null");
            } else {
                Log.d(TAG, "initData: data : " + data.size());
                calendarScheduleAdapter.addItems(data);
//                tvShowSchedule.setText("");
//                for (int i=0; i<data.size(); i++){
//                    calendarScheduleAdapter.addItem(data.get);
//                    tvShowSchedule.append(data.get(i).getTitle() + "\n");
//                    Log.d(TAG, "getDate: " + data.get(i).getCalendarDayList().get(i).getDate());
//                    Log.d(TAG, "getMonth: " + data.get(i).getCalendarDayList().get(i).getMonth());
//                    Log.d(TAG, "getDay: " + data.get(i).getCalendarDayList().get(i).getDay());
//                }
            }

        });

    }

    private void findOne(CalendarDay today){ // 사실은 한건이 아님. 몇건일지 모름
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        db.collection("diary")
                .whereEqualTo("today", today).whereEqualTo("user", user.getEmail()) // 쿼리는 이런식으로 작성하면 될듯
                .get()
                .addOnCompleteListener(runnable -> {
                    if (runnable.getResult().size() == 0){
                        Log.d(TAG, "findOne: 데이터 없음");
                        //card.setVisibility(View.INVISIBLE);
                    } else {
                        Log.d(TAG, "findAllDiary: success:  " + runnable.getResult().size());
                        Diary newDiary = runnable.getResult().toObjects(Diary.class).get(0);
                        Log.d(TAG, "findAllDiary: success:  " + newDiary.toString());
                        Log.d(TAG, "findAllDiary: success:  " + newDiary.getContent());
                        tvShowDiary.setText(newDiary.getTitle());
                        card.setVisibility(View.VISIBLE);
                    }


                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                    Log.d(TAG, "findOne: 에러");

                });
    }


}