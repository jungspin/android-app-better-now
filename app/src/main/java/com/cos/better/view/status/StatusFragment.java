package com.cos.better.view.status;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.view.status.category.CategoryStatus;
import com.cos.better.view.status.habit.HabitStatus;


public class StatusFragment extends Fragment implements InitSetting {

    private static final String TAG = "StatusFragment";
    private Context mContext;
    private Activity activity;
    private TextView tvCategoryDetail, tvHabitDetail;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_status, container, false);
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
        tvCategoryDetail = view.findViewById(R.id.tvCategoryDetail);
        tvHabitDetail = view.findViewById(R.id.tvHabitDetail);
    }

    @Override
    public void initLr() {
        tvCategoryDetail.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, CategoryStatus.class);
            mContext.startActivity(intent);
        });
        tvHabitDetail.setOnClickListener(v->{
            Intent intent = new Intent(mContext, HabitStatus.class);
            mContext.startActivity(intent);
        });
    }

    @Override
    public void initData() {

    }
}