package com.cos.better.view.diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.model.Diary;
import com.cos.better.view.HomeActivity;
import com.cos.better.R;


import com.cos.better.viewModel.DiaryViewModel;
import com.google.android.material.textview.MaterialTextView;
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
    private MaterialTextView mtvToday, mtvTitle;
    private Button btnLinkWrite, btnLinkUpdate, btnDelete;


    private DiaryViewModel vm;
    private Diary diary;


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
        mtvTitle = view.findViewById(R.id.mtvTitle);

        btnLinkUpdate = view.findViewById(R.id.btnLinkUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);
    }

    @Override
    public void initLr() {
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            cal.set(date.getYear(), date.getMonth(), date.getDay());
            month = cal.get(Calendar.MONTH)+1;

            dayDTO = CalenderDayDTO.builder().year(cal.get(Calendar.YEAR)).month(month).day(cal.get(Calendar.DAY_OF_MONTH)).build();
            Log.d(TAG, "initLr: selectedDate dayDTO : " + dayDTO.toString());
            //vm.findOne(CalendarDay.from(dayDTO.getYear(), (dayDTO.getMonth()-1), dayDTO.getDay()));
            CalendarDay calendarDay = CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay());
            vm.findOne(calendarDay);

        });

        btnLinkWrite.setOnClickListener(v->{
            Intent intent = new Intent(mContext, WriteDiaryActivity.class);
            intent.putExtra("date", dayDTO);
            startActivity(intent);
        });

        btnLinkUpdate.setOnClickListener(v->{
            //Log.d(TAG, "btnLinkUpdate: " + diary.getTitle());
            Intent intent = new Intent(mContext, UpdateDiaryActivity.class);
            intent.putExtra("today", CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));
            //Log.d(TAG, "btnLinkUpdate: " + CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v->{
            vm.deleteDiary(mContext, diary.getId());
            btnLinkUpdate.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);

            CalendarDay calendarDay = CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            vm.findOne(calendarDay);
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
        CalendarDay today = CalendarDay.from(dayDTO.getYear(), (dayDTO.getMonth()-1), dayDTO.getDay());
        vm.findOne(today);
        vm.getDiary().observe((HomeActivity)mContext, data ->{
            if (data == null){ // 데이터 없음
                Log.d(TAG, "initData: null " );
                mtvToday.setText(month + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일" + "은 어땠나요?");
                //ivPhoto.setVisibility(View.INVISIBLE);
                btnLinkWrite.setVisibility(View.VISIBLE);
                mtvTitle.setVisibility(View.INVISIBLE);
                btnLinkUpdate.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);

            } else {
                diary = data;
                mtvTitle.setText(data.getTitle());
                mtvToday.setText(Html.fromHtml(data.getContent()));

                mtvTitle.setVisibility(View.VISIBLE);
                btnLinkWrite.setVisibility(View.INVISIBLE);
                btnLinkUpdate.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);
            }

        });


    }









}