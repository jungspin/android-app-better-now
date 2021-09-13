package com.cos.better.view.mypage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.dto.HabitDto;
import com.cos.better.model.Habit;
import com.cos.better.view.calender.decorator.SundayDecorator;
import com.cos.better.view.mypage.adapter.MyHabitAdapter;
import com.cos.better.view.status.adapter.StatusAdapter;
import com.cos.better.viewModel.HabitViewModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MyHabitFragment extends Fragment implements InitSetting {

    private static final String TAG = "MyHabitFragment";
    private Context mContext;
    private Activity activity;
    private MypageFragment mFragment;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private  View view;
    private DocumentReference documentReference;
    private MaterialCalendarView mcView;
    private RecyclerView rvHabit;
    private RecyclerView.LayoutManager layoutManager;
    private MyHabitAdapter myHabitAdapter;
    private List<String> weekList =new ArrayList();
    private List<String> monthList = new ArrayList();
    private List<Habit> everyDayList = new ArrayList<>();

    private CalenderDayDTO dayDTO;
    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH)+1;

    public MyHabitFragment(MypageFragment mFragment){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_habit, container, false);

        HabitViewModel habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);
        habitViewModel.findAllHabit();

        habitViewModel.getHabit().observe(getViewLifecycleOwner(),changeHabit ->{
            for(int i =0; i<changeHabit.size(); i++){
                Habit habit= changeHabit.get(i);
                Log.d(TAG, "onCreateView: "+changeHabit.get(i));
                if(habit.getCycleCode()==0){ //매일
                    Log.d(TAG, "onCreateView: 매일"+habit);
                    everyDayList.add(habit);
                }else if(habit.getCycleCode()==1){ //매주
                    weekList = Arrays.asList(habit.getCycle().split(" "));
                    Log.d(TAG, "onCreateView: weekList"+weekList);
                    for(int j = 0; j<weekList.size(); j++){
                        switch (weekList.get(j)){

                            case "일":

                                break;
                            case "월":
                                break;
                            case "화":
                                break;
                            case "수":
                                break;
                            case "목":
                                break;
                            case "금":
                                break;
                            case "토":
                                break;


                        }
                    }

                }else{ //매달
                    monthList = Arrays.asList(habit.getCycle().split(" "));
                    Log.d(TAG, "onCreateView: monthList"+monthList);
                    for(int j = 0; j<monthList.size(); j++){

                    }
                }
            }
        });


        init();
        initData();
        initAdapter();
        initLr();
        addDb();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;

        if(context instanceof Activity)
            activity = (Activity) context;
    }

    @Override
    public void initAdapter() {
        layoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rvHabit.setLayoutManager(layoutManager);
        myHabitAdapter = new MyHabitAdapter(mContext);
        rvHabit.setAdapter(myHabitAdapter);
    }

    @Override
    public void init() {
        mcView = view.findViewById(R.id.mcView);
        rvHabit = view.findViewById(R.id.rvHabit);
    }

    @Override
    public void initLr() {
        mcView.setOnDateChangedListener((widget, date, selected)->{
            cal.set(date.getYear(), date.getMonth(), date.getDay());
            month = cal.get(Calendar.MONTH)+1;
            dayDTO = new CalenderDayDTO(cal.get(Calendar.YEAR),month,cal.get(Calendar.DATE));
            Log.d(TAG, "initLr: "+dayDTO.toString());
            if(everyDayList.size()!=0) {
                for(int i =0; i<everyDayList.size(); i++) {
                    Habit habit = everyDayList.get(i);
                    Log.d(TAG, "initLr: 매일 습관 표시됨");
                    myHabitAdapter.addItem(new HabitDto(habit.getIcon(),habit.getHabitTitle(),"미달성"));
                }
            }
        });
    }


    @Override
    public void initData() {
        mcView.setSelectedDate(cal);
        mcView.setCurrentDate(new Date(System.currentTimeMillis()));
        //mcView.setDateSelected(new Date(System.currentTimeMillis()),false);
        mcView.addDecorators(new SundayDecorator());
    }
    public void addDb(){
        Log.d(TAG, "addDb: 실행됨");
        //documentReference = db.collection("habits").document("habit1");
    }
}