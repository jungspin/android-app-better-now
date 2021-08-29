package com.cos.better.view.status;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.view.status.category.CategoryStatus;
import com.cos.better.view.status.habit.HabitStatus;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity implements InitSetting {

    private TextView tvCategoryDetail, tvHabitDetail;
    private PieChart pieChart;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
    }

    @Override
    public void init() {
        tvCategoryDetail = findViewById(R.id.tvCategoryDetail);
        tvHabitDetail = findViewById(R.id.tvHabitDetail);
        pieChart = findViewById(R.id.piechart);
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
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(10,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        //pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(60,"Japan"));
//        yValues.add(new PieEntry(23f,"USA"));
//        yValues.add(new PieEntry(14f,"UK"));
//        yValues.add(new PieEntry(35f,"India"));
//        yValues.add(new PieEntry(40f,"Russia"));
//        yValues.add(new PieEntry(40f,"Korea"));

        Description description = new Description();
        description.setText("세계 국가"); //라벨
        description.setTextSize(15);
        pieChart.setDescription(description);

        //pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
    }
}