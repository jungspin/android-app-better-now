package com.cos.better.view.calender;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.cos.better.view.HomeActivity;
import com.cos.better.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


public class CalenderFragment extends Fragment {

    private static final String TAG = "CalenderFragment";
    private HomeActivity mContext;

    private MaterialCalendarView calendarView;





    public CalenderFragment(HomeActivity mContext) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calender, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setSelectedDate(CalendarDay.today()); // 날짜 선택시 둥글게? 되게함

        //calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);

        calendarView.setOnDateChangedListener((widget, date, selected) -> {

            //Log.d(TAG, "onCreateView: " + date.getDate() + " 클릭됨 ");
            Log.d(TAG, "onCreateView: month : " + (date.getMonth()+1));
            String selectedDate =  date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDay();
            Log.d(TAG, "onCreateView: selectedDate : " + selectedDate);
            Intent intent = new Intent(getActivity(), TempActivity.class);
            intent.putExtra("date", selectedDate); // getDate 는 date 타입
            getActivity().startActivity(intent);

        });






        return view;
    }


}