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
import com.cos.better.model.CalenderObj;
import com.cos.better.view.calender.decorator.SundayDecorator;
import com.cos.better.view.mypage.adapter.MyHabitAdapter;
import com.cos.better.view.status.adapter.StatusAdapter;
import com.cos.better.viewModel.HabitViewModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;


public class MyHabitFragment extends Fragment implements InitSetting {

    private static final String TAG = "MyHabitFragment";
    private Context mContext;
    private Activity activity;
    private MypageFragment mFragment;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private  View view;
    private DocumentReference documentReference;
    private MaterialCalendarView mcView;
    private Calendar cal = Calendar.getInstance();
    private RecyclerView rvHabit;
    private RecyclerView.LayoutManager layoutManager;
    private MyHabitAdapter myHabitAdapter;


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
                Log.d(TAG, "onCreateView: "+changeHabit.get(i));
               if(changeHabit.get(i).getCycleCode()==0){ //매일
                   //changeHabit.get(i).getCycle()
               }else if(changeHabit.get(i).getCycleCode()==1){ //매주

               }else{

               }
            }
        });


        init();
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
}


    @Override
    public void initData() {
        mcView.setCurrentDate(new Date(System.currentTimeMillis()));
        mcView.setDateSelected(new Date(System.currentTimeMillis()),false);
        mcView.addDecorators(new SundayDecorator());
    }
    public void addDb(){
        Log.d(TAG, "addDb: 실행됨");
        documentReference = db.collection("habits").document("habit1");
    }
}