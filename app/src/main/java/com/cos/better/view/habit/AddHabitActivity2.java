package com.cos.better.view.habit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.cos.better.R;
import com.cos.better.config.InitSetting;

public class AddHabitActivity2 extends AppCompatActivity implements InitSetting {

    private AppCompatButton btnHealth, btnReading, btnLife, btnStudy, btnMoney, btnOther;
    private Button btnBack;
    private Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit2);
        init();
        initLr();
    }

    @Override
    public void init() {
        btnHealth = findViewById(R.id.btnHealth);
        btnReading = findViewById(R.id.btnReading);
        btnLife = findViewById(R.id.btnLife);
        btnStudy = findViewById(R.id.btnStudy);
        btnMoney = findViewById(R.id.btnMoney);
        btnOther = findViewById(R.id.btnOther);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void initLr() {
        btnHealth.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("btnHealth",btnHealth.getText());
            intent.putExtra("categoryName","btnHealth");
            mContext.startActivity(intent);
        });

        btnReading.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("btnReading",btnReading.getText());
            intent.putExtra("categoryName","btnReading");
            mContext.startActivity(intent);
        });

        btnLife.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("btnLife",btnLife.getText());
            intent.putExtra("categoryName","btnLife");
            mContext.startActivity(intent);
        });

        btnStudy.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("btnStudy",btnStudy.getText());
            intent.putExtra("categoryName","btnStudy");
            mContext.startActivity(intent);
        });

        btnMoney.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("btnMoney",btnMoney.getText());
            intent.putExtra("categoryName","btnMoney");
            mContext.startActivity(intent);
        });

        btnOther.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,AddHabitActivity.class);
            intent.putExtra("btnOther",btnOther.getText());
            intent.putExtra("categoryName","btnOther");
            mContext.startActivity(intent);
        });

        btnBack.setOnClickListener(v->{
            finish();
        });

    }

    @Override
    public void initData() {

    }
}