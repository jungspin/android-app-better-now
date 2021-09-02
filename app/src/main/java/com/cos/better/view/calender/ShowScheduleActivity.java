package com.cos.better.view.calender;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.cos.better.R;
import com.cos.better.config.CustomDate;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.model.Diary;
import com.cos.better.viewModel.CalenderListViewModel;
import com.cos.better.viewModel.DiaryListViewModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;

// 이후 이름 수정해야함!!!! + xml 도 마찬가지
public class ShowScheduleActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "ShowScheduleActivity";
    private Context mContext = ShowScheduleActivity.this;

    private AppCompatButton btnAddSchedule;
    private TextView tvShowAll, tvShowSchedule, tvShowDiary, tvShowHabit;

    private ActivityResultLauncher<Intent> activityResultLauncher;
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
        tvShowSchedule = findViewById(R.id.tvShowSchedule);
        tvShowDiary = findViewById(R.id.tvShowDiary);
        tvShowHabit = findViewById(R.id.tvShowHabit);

    }

    @Override
    public void initLr() {

        btnAddSchedule.setOnClickListener(v->{
            Intent intent = new Intent(mContext, AddScheduleActivity.class);
            startActivity(intent);


        });


    }



    @Override
    public void initSetting() {
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

        clv.findAllScheduleTest(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));

        //clv.findAllSchedule(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));
//        clv.getMdCalenderList().observe((ShowScheduleActivity)mContext, data -> {
//            if (data == null){
//                Log.d(TAG, "initData: data null");
//            } else {
//                tvShowSchedule.setText("");
//                for (int i=0; i<data.size(); i++){
//
//                    tvShowSchedule.append(data.get(i).getTitle() + "\n");
////                    Log.d(TAG, "getDate: " + data.get(i).getCalendarDayList().get(i).getDate());
////                    Log.d(TAG, "getMonth: " + data.get(i).getCalendarDayList().get(i).getMonth());
////                    Log.d(TAG, "getDay: " + data.get(i).getCalendarDayList().get(i).getDay());
//                }
//            }

//        });

    }

    private void findOne(CalendarDay today){ // 사실은 한건이 아님. 몇건일지 모름
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("diary")
                .whereEqualTo("today", today) // 쿼리는 이런식으로 작성하면 될듯
                .get()
                .addOnCompleteListener(runnable -> {
                    if (runnable.getResult().size() == 0){
                        tvShowDiary.setText("일기를 써보세요!");
                    } else {
                        Log.d(TAG, "findAllDiary: success:  " + runnable.getResult().size());
                        Diary newDiary = runnable.getResult().toObjects(Diary.class).get(0);
                        Log.d(TAG, "findAllDiary: success:  " + newDiary.toString());
                        Log.d(TAG, "findAllDiary: success:  " + newDiary.getContent());
                        tvShowDiary.setText(newDiary.getContent());
                    }


                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                    Log.d(TAG, "findOne: 에러");

                });
    }
}