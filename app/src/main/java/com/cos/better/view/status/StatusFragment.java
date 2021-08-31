package com.cos.better.view.status;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.StatusDto;
import com.cos.better.view.habit.AddHabitActivity;
import com.cos.better.view.habit.AddHabitActivity2;
import com.cos.better.view.status.adapter.StatusAdapter;
import com.cos.better.view.status.category.CategoryStatus;
import com.cos.better.view.status.habit.HabitStatus;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class StatusFragment extends Fragment implements InitSetting {

    private static final String TAG = "StatusFragment";
    private Context mContext;
    private Activity activity;
    private View view;
    private RecyclerView rvStatus;
    private RecyclerView.LayoutManager layoutManager;
    private StatusAdapter statusAdapter;
    private FloatingActionButton fabAdd;
    private TextView tvStatusIcon;
    private List<StatusDto> lists = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_status, container, false);
        init();
        initAdapter();
        initData();
        initLr();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;

        if (context instanceof Activity)
            activity = (Activity) context;
    }

    @Override
    public void init() {
        rvStatus = view.findViewById(R.id.rvStatus);
        fabAdd = view.findViewById(R.id.fabAdd);
        tvStatusIcon = view.findViewById(R.id.tvStatusIcon);
    }

    @Override
    public void initLr() {
        fabAdd.setOnClickListener(v->{
            Intent intent = new Intent(mContext, AddHabitActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(intent);
        });
        tvStatusIcon.setOnClickListener(v ->{
            Intent intent = new Intent(mContext,StatusActivity.class);
            mContext.startActivity(intent);
        });

    }

    @Override
    public void initAdapter() {
        layoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rvStatus.setLayoutManager(layoutManager);
        statusAdapter = new StatusAdapter(mContext);
        rvStatus.setAdapter(statusAdapter);

    }

    @Override
    public void initData() {
        //테스트
        statusAdapter.addItem(new StatusDto(R.drawable.ic_health,"매일 운동 15분",4,"운동","매일"));
        //statusAdapter.addItem(new StatusDto(R.drawable.ic_book,"독서 10페이지",2,"주 3회"));

        lists.add(new StatusDto(R.drawable.ic_book,"독서 10페이지",2,"독서","주 3회"));
        lists.add(new StatusDto(R.drawable.ic_health,"걷기 10분",28,"운동","주 5회"));
        statusAdapter.addItems(lists);
    }
}
