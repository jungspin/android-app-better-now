package com.cos.better.view.calender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cos.better.config.InitSetting;
import com.cos.better.config.MyCalender;
import com.cos.better.config.MyDate;
import com.cos.better.view.HomeActivity;
import com.cos.better.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Calendar;
import java.util.Date;


public class CalenderFragment extends Fragment implements InitSetting {

    private static final String TAG = "CalenderFragment";
    private Context mContext;
    private Activity activity;
    private View view;

    private SlidingUpPanelLayout splAddSchedule;
    private MaterialCalendarView calendarView, splCalendarView;
    private TextView tvAddSchedule;
    private FloatingActionButton fabAddSchedule;

    MyDate myDate = new MyDate();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;

        if(context instanceof Activity)
            activity = (Activity) context;
    }


    public CalenderFragment(HomeActivity mContext) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_calender, container, false);

        init();
        initLr();
        initSetting();
        initData();
        //calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);
        return view;
    }


    @Override
    public void init() {
        splAddSchedule = view.findViewById(R.id.splAddSchedule);
        tvAddSchedule = view.findViewById(R.id.tvAddSchedule);
        calendarView = view.findViewById(R.id.calendarView);
        splCalendarView = view.findViewById(R.id.splCalendarView);
        fabAddSchedule = view.findViewById(R.id.fabAddSchedule);

    }

    @Override
    public void initLr() {
        // 사용자가 다른 날짜를 클릭했을 때
        calendarView.setOnDateChangedListener((widget, date, selected) -> {

//            calendarView.setOnTitleClickListener(v->{
//                // 여기서 원하는 년/월 로 갈 수 있겠음
//                Log.d(TAG, "onCreateView: 쿨릭");
//            });

            String selectedDate =  date.getYear()+"년 "+(date.getMonth()+1)+"월 "+date.getDay()+"일";
            tvAddSchedule.setText(selectedDate);
        });


        tvAddSchedule.setOnClickListener(v->{
            splAddSchedule.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);


            //splCalendarView.setSelectedDate(date);
        });
        
        fabAddSchedule.setOnClickListener(v->{
            Log.d(TAG, "initLr: 일정 추가 버튼 클릭");
        });

    }

    @Override
    public void initSetting() {


        calendarView.addDecorator(new MyCalender());
        //calendarView.setSelectedDate(myDate.getToday()); // 날짜 선택시 둥글게? 되게함
        myDate = myDate.getSelectedDate(myDate.getToday());
        tvAddSchedule.setText(myDate.getMYear() + "년 " + myDate.getMMonth()+"월 " + myDate.getMDay()+"일");





    }

    @Override
    public void initData() {

    }


}