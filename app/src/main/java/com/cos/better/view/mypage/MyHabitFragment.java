package com.cos.better.view.mypage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.cos.better.R;
import com.cos.better.config.InitSetting;


public class MyHabitFragment extends Fragment implements InitSetting {

    private static final String TAG = "MyHabitFragment";
    private Context mContext;
    private Activity activity;
    private MypageFragment mFragment;

    private  View view;


    public MyHabitFragment(MypageFragment mFragment) {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_my_habit, container, false);
      init();
      initLr();
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
    public void init() {

    }

    @Override
    public void initLr() {
}


    @Override
    public void initData() {

    }
}