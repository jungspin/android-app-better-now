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


import com.cos.better.config.InitSetting;
import com.cos.better.view.HomeActivity;
import com.cos.better.R;
import com.cos.better.view.calender.decorator.DefaultDecorator;
import com.cos.better.view.calender.decorator.EventDecorator;
import com.cos.better.view.calender.decorator.TodayDecorator;
import com.cos.better.view.calender.decorator.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;


public class CalenderFragment extends Fragment implements InitSetting {

    private static final String TAG = "CalenderFragment";
    private Context mContext;
    private Activity activity;
    private View view;

    private MaterialCalendarView calendarView;

    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH)+1;
    String output = cal.get(Calendar.YEAR) + "년 " + month + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일";


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
        calendarView = view.findViewById(R.id.calendarView);

    }

    @Override
    public void initLr() {
        // 사용자가 다른 날짜를 클릭
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            cal.set(date.getYear(), date.getMonth(), date.getDay());
            month = cal.get(Calendar.MONTH)+1;
            output = cal.get(Calendar.YEAR)+"년 "+ month + "월 "+cal.get(Calendar.DAY_OF_MONTH)+"일";
            Log.d(TAG, "initLr: selectedDate : " + output);

            Intent intent = new Intent(mContext, ShowScheduleActivity.class);
            intent.putExtra("date", output);
            startActivity(intent);
        });



    }

    @Override
    public void initSetting() {
        ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
        calendarDayList.add(CalendarDay.today());
        calendarDayList.add(CalendarDay.from(2021, 7, 18));
        calendarDayList.add(CalendarDay.from(2021, 7, 15));
        Log.d(TAG, "initSetting:calendarDayList " + calendarDayList.size());
        calendarView.addDecorators(new DefaultDecorator(), new SundayDecorator(),new TodayDecorator(mContext),new EventDecorator(mContext, calendarDayList));

        
        // 처음 앱 실행시 Calender
        Log.d(TAG, "initSetting: 처음 실행 시 :" + cal);
        Log.d(TAG, "initSetting: 처음 실행 시 :" + output);
    }

    @Override
    public void initData() {

    }


}