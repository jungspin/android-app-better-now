package com.cos.better.view.status;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.view.status.category.CategoryStatus;
import com.cos.better.view.status.habit.HabitStatus;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity implements InitSetting {

    private TextView tvCategoryDetail, tvHabitDetail;
    private Context mContext = this;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        init();
        initData();
        initLr();
    }

    public void createProgressBar(int progress){
        CircleProgressBar circleProgressBar = new CircleProgressBar(mContext);
        circleProgressBar.setProgress(progress);
        circleProgressBar.setBackgroundColor(mContext.getResources().getColor(R.color.brown));
        circleProgressBar.setProgressStartColor(mContext.getResources().getColor(R.color.light_navy));
        circleProgressBar.setProgressEndColor(mContext.getResources().getColor(android.R.color.darker_gray));
        circleProgressBar.setCap(Paint.Cap.ROUND);
        circleProgressBar.setProgressStrokeWidth(20);

    }
    @Override
    public void init() {
        tvCategoryDetail = findViewById(R.id.tvCategoryDetail);
        tvHabitDetail = findViewById(R.id.tvHabitDetail);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void initLr() {

        btnBack.setOnClickListener(v ->{
            finish();
        });

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