package com.cos.better.view.diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.model.Diary;
import com.cos.better.view.HomeActivity;
import com.cos.better.R;

import com.cos.better.view.calender.ShowScheduleActivity;
import com.cos.better.viewModel.DiaryController;
import com.cos.better.viewModel.DiaryViewModel;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;


public class DiaryFragment extends Fragment implements InitSetting {
    private static final String TAG = "DiaryFragment";

    private Context mContext;
    private Context context;
    private Activity activity;
    private View view;

    private MaterialCalendarView calendarView;
    private MaterialTextView mtvToday, mtvContent;
    private Button btnLinkWrite;
    private ImageView ivPhoto;

    DiaryViewModel vm;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;

        if(context instanceof Activity)
            activity = (Activity) context;
    }

    public DiaryFragment(HomeActivity mContext) {

    }



    private CalenderDayDTO dayDTO;
    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH)+1;





    // 화면 넘어올 때 해당 데이터 있으면 화면에 뿌려야하고, 없으면 아래와 같이
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diary, container, false);




        init();
        initLr();
        initSetting();
        //initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        // 다른 탭 갔다오면 무조건 새로고침 됨 -> 결국 오늘 날짜로 돌아오는 거니 이상하진 않을듯


    }

    @Override
    public void init() {
        mtvToday = view.findViewById(R.id.mtvToday);
        calendarView = view.findViewById(R.id.calendarView);
        btnLinkWrite = view.findViewById(R.id.btnLinkWrite);
        mtvContent = view.findViewById(R.id.mtvToday);
        ivPhoto = view.findViewById(R.id.ivPhoto);
    }

    @Override
    public void initLr() {
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            cal.set(date.getYear(), date.getMonth(), date.getDay());
            month = cal.get(Calendar.MONTH)+1;

            dayDTO = CalenderDayDTO.builder().year(cal.get(Calendar.YEAR)).month(month).day(cal.get(Calendar.DAY_OF_MONTH)).build();
            Log.d(TAG, "initLr: selectedDate dayDTO : " + dayDTO.toString());
            vm.findOne(CalendarDay.from(dayDTO.getYear(), (dayDTO.getMonth()-1), dayDTO.getDay()));

        });

        btnLinkWrite.setOnClickListener(v->{
            Intent intent = new Intent(mContext, WriteDiaryActivity.class);
            intent.putExtra("date", dayDTO);
            startActivity(intent);
        });

    }

    @Override
    public void initSetting() {
        calendarView.setSelectedDate(cal);

        // 처음 앱 실행시 Calender
        dayDTO = CalenderDayDTO.builder().year(cal.get(Calendar.YEAR)).month(month).day(cal.get(Calendar.DAY_OF_MONTH)).build();
        Log.d(TAG, "initSetting: 처음 실행 dayDTO : " + dayDTO.toString());

        vm = new ViewModelProvider((HomeActivity)mContext).get(DiaryViewModel.class);




    }

    @Override
    public void initData() {
        // 얘네 리스너에도 있어야함
        vm.findOne(CalendarDay.from(dayDTO.getYear(), (dayDTO.getMonth()-1), dayDTO.getDay()));
        vm.getDiary().observe((HomeActivity)mContext, data ->{
            if (data == null){
                Log.d(TAG, "initData: null " );
                mtvToday.setText(month + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일" + "은 어땠나요?");
                btnLinkWrite.setVisibility(View.VISIBLE);
            } else {
                Log.d(TAG, "initData: " + data.getToday());
                mtvToday.setText(data.getContent());
                btnLinkWrite.setVisibility(View.INVISIBLE);
            }

        });


    }









}