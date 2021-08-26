package com.cos.better.view.diary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cos.better.config.InitSetting;
import com.cos.better.view.HomeActivity;
import com.cos.better.R;
import com.cos.better.view.calender.TempActivity;
import com.github.irshulx.Editor;
import com.github.irshulx.models.EditorTextStyle;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.textview.MaterialTextView;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import in.nashapp.androidsummernote.Summernote;
import jp.wasabeef.richeditor.RichEditor;


public class DiaryFragment extends Fragment  {
    private static final String TAG = "DiaryFragment";

    private HomeActivity mContext;

    private MaterialCalendarView calendarView;
    private MaterialTextView mtvToday;
    private Button btnLinkWrite;

    private  String[] selectedDate;
    Calendar today = Calendar.getInstance();


    public DiaryFragment(HomeActivity mContext) {
        // Required empty public constructor
    }

    // 화면 넘어올 때 해당 데이터 있으면 화면에 뿌려야하고, 없으면 아래와 같이
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        mtvToday = view.findViewById(R.id.mtvToday);
        btnLinkWrite = view.findViewById(R.id.btnLinkWrite);

        calendarView.setSelectedDate(today);
        selectedDate = getSelectedDate();

        Log.d(TAG, "none selectedDate: " + selectedDate[2]);
        mtvToday.setText(selectedDate[1] + "월 " + selectedDate[2] + "일" + "은 어땠나요?");

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            selectedDate = (date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDay()).split("-");
            Log.d(TAG, "selectedDate: " + selectedDate[2]);
            mtvToday.setText(selectedDate[1] + "월 " + selectedDate[2] + "일" + "은 어땠나요?");
        });

        btnLinkWrite.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), TestActivity.class);
            intent.putExtra("selectedDate", selectedDate);
            getActivity().startActivity(intent);
        });
        return view;
    }

    private String[] getSelectedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        selectedDate = sdf.format(today.getTime()).split("-");
        return selectedDate;
    }

}