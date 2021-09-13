package com.cos.better.view.calender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.cos.better.config.CustomDate;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.view.HomeActivity;
import com.cos.better.R;
import com.cos.better.view.calender.decorator.DefaultDecorator;
import com.cos.better.view.calender.decorator.DiaryDecorator;
import com.cos.better.view.calender.decorator.EventDecorator;
import com.cos.better.view.calender.decorator.TestDecorator;
import com.cos.better.view.calender.decorator.TodayDecorator;
import com.cos.better.view.calender.decorator.SundayDecorator;
import com.cos.better.viewModel.CalenderDayListViewModel;
import com.cos.better.viewModel.CalenderListViewModel;
import com.cos.better.viewModel.DiaryListViewModel;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


public class CalenderFragment extends Fragment implements InitSetting {

    private static final String TAG = "CalenderFragment";
    private Context mContext;
    private Activity activity;
    private View view;

    private MaterialCalendarView calendarView;


    CustomDate customDate = new CustomDate();
    //private CalenderDayDTO dayDTO;
    private ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
    private DiaryListViewModel vm;
    private CalenderDayListViewModel cdvm;

    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH)+1;
    CalendarDay today;


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

    public CalenderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_calender, container, false);

        init();
        initLr();
        initSetting();

        //calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();

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

            //dayDTO = CalenderDayDTO.builder().year(cal.get(Calendar.YEAR)).month(month).day(cal.get(Calendar.DAY_OF_MONTH)).build();
            customDate.setYear(cal.get(Calendar.YEAR));
            customDate.setMonth(month);
            customDate.setDay(cal.get(Calendar.DAY_OF_MONTH));

            today = CalendarDay.from(customDate.getYear(), customDate.getMonth(), customDate.getDay());
            Log.d(TAG, "initLr: today : " + today);

            //CalendarDay.from(2021, 8, 25);

            Intent intent = new Intent(mContext, ShowScheduleActivity.class);
            intent.putExtra("date", customDate);
            Log.d(TAG, "initLr: date : " + customDate.toString());
            startActivity(intent);
        });



    }

    @Override
    public void initSetting() {


        vm = new ViewModelProvider((HomeActivity)mContext).get(DiaryListViewModel.class);
        cdvm = new ViewModelProvider((HomeActivity)mContext).get(CalenderDayListViewModel.class);
        calendarView.addDecorators(new DefaultDecorator(), new SundayDecorator(),new TodayDecorator(mContext)/*,new EventDecorator(mContext, calendarDayList)*/);

        
        // 처음 앱 실행시 Calender
        //dayDTO = CalenderDayDTO.builder().year(cal.get(Calendar.YEAR)).month(month).day(cal.get(Calendar.DAY_OF_MONTH)).build();

        customDate.setYear(cal.get(Calendar.YEAR));
        customDate.setMonth(month);
        customDate.setDay(cal.get(Calendar.DAY_OF_MONTH));

        Log.d(TAG, "initSetting: 처음 실행 customDate : " + customDate.toString());

    }

    @Override
    public void initData() {
        vm.findAllDiary();
        vm.getDiaryList().observe((HomeActivity)mContext, data ->{
            if (data!=null){
                for (int i=0; i<data.size();i++){
                    calendarDayList.add(data.get(i).getToday());
                }
                List<CalendarDay> newList = deleteDup(calendarDayList);
                Log.d(TAG, "initData getDiaryList: " + newList.size());
                calendarView.addDecorators(new DiaryDecorator(mContext, newList));
            } else {
                Log.d(TAG, "initData: 데이터 없음");
            }
          
        });
        today = CalendarDay.from(customDate.getYear(), customDate.getMonth(), customDate.getDay());
        Log.d(TAG, "initData customdate: " + today);

        cdvm.findAllCalendar();
        cdvm.getMdCldList().observe((HomeActivity)mContext, data ->{
            if (data != null){
                List<CalendarDay> newList = deleteDup(data);
                Log.d(TAG, "findAllCalendar: " + newList.size());
                calendarView.addDecorators(new EventDecorator(mContext, newList));
            } else {
                Log.d(TAG, "findAllCalendar: 데이터 없음");
            }
            
        });

    }

    private List<CalendarDay> deleteDup(List<CalendarDay> originalList){
        List<CalendarDay> newList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            newList = originalList.stream().distinct().collect(Collectors.toList());
        }
        return newList;
    }


}
