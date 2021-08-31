package com.cos.better.view.diary;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cos.better.config.InitSetting;
import com.cos.better.config.MyDate;
import com.cos.better.view.HomeActivity;
import com.cos.better.R;

import com.google.android.material.textview.MaterialTextView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DiaryFragment extends Fragment implements InitSetting {
    private static final String TAG = "DiaryFragment";

    private HomeActivity mContext;

    private MaterialCalendarView calendarView;
    private MaterialTextView mtvToday;
    private Button btnLinkWrite;



    private MyDate myDate = new MyDate();
    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH)+1;
    String output = cal.get(Calendar.YEAR) + "년 " + month + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일";


    public DiaryFragment(HomeActivity mContext) {

    }

    // 화면 넘어올 때 해당 데이터 있으면 화면에 뿌려야하고, 없으면 아래와 같이
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 다른 탭 갔다오면 무조건 새로고침 됨 -> 결국 오늘 날짜로 돌아오는 거니 이상하진 않을듯
        init();
        initLr();
        initSetting();
        initData();

    }

    @Override
    public void init() {
        mtvToday = getView().findViewById(R.id.mtvToday);
        calendarView = getView().findViewById(R.id.calendarView);
        btnLinkWrite = getView().findViewById(R.id.btnLinkWrite);
    }

    @Override
    public void initLr() {
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            cal.set(date.getYear(), (date.getMonth()+1), date.getDay());
            mtvToday.setText(month + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일" + "은 어땠나요?");
        });

        btnLinkWrite.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), WriteDiaryActivity.class);
            String initData = month + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일의 기록";
            intent.putExtra("initData", initData);
            getActivity().startActivity(intent);
        });

    }

    @Override
    public void initSetting() {
        calendarView.setSelectedDate(myDate.getToday());
        myDate = myDate.getSelectedDate(myDate.getToday());


        Log.d(TAG, "none selectedDate: " + myDate.toString());
        mtvToday.setText(myDate.getMMonth() + "월 " + myDate.getMDay() + "일" + "은 어땠나요?");

    }

    @Override
    public void initData() {

    }






}