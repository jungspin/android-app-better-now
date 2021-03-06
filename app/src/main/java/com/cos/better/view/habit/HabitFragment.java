package com.cos.better.view.habit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cos.better.R;
import com.cos.better.config.InitSetting;


public class HabitFragment extends Fragment implements InitSetting{
    private View view;
    private AppCompatButton btnHealth, btnReading, btnLife, btnStudy, btnMoney, btnOther;
    private static final String TAG = "habitFragment";
    private Activity activity;
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_habit, container, false);

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
        btnHealth = view.findViewById(R.id.btnHealth);
        btnReading = view.findViewById(R.id.btnReading);
        btnLife = view.findViewById(R.id.btnLife);
        btnStudy = view.findViewById(R.id.btnStudy);
        btnMoney = view.findViewById(R.id.btnMoney);
        btnOther = view.findViewById(R.id.btnOther);
    }

    @Override
    public void initLr() {
        btnHealth.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("categoryName","건강");
            mContext.startActivity(intent);
        });

        btnReading.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("categoryName","독서");
            mContext.startActivity(intent);
        });

        btnLife.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("categoryName","생활습관");
            mContext.startActivity(intent);
        });

        btnStudy.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("categoryName","공부");
            mContext.startActivity(intent);
        });

        btnMoney.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("categoryName","자산관리");
            mContext.startActivity(intent);
        });

        btnOther.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("categoryName","기타");
            mContext.startActivity(intent);
        });
    }

    @Override
    public void initData() {

    }
}