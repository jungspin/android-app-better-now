package com.cos.better.view.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cos.better.R;

public class IntentTestActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);

        tvTest = findViewById(R.id.tvTest);
        String diary = getIntent().getStringExtra("diary");
        tvTest.setText(diary);
    }
}