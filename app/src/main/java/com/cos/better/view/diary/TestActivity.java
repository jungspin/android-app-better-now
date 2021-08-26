package com.cos.better.view.diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.cos.better.R;
import com.cos.better.config.InitSetting;

import in.nashapp.androidsummernote.Summernote;

public class TestActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "TestActivity";
    private TestActivity mContext = this;

    private  Summernote summernote;
    private ImageView ivSave, ivCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        init();
        initLr();
        initSetting();
        initData();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        summernote.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void init() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.summerToolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("");

        summernote = findViewById(R.id.summernote);
        summernote.setRequestCodeforFilepicker(5);//Any Number which is not being used by other OnResultActivity

        ivSave = findViewById(R.id.ivSave);
        ivCancel = findViewById(R.id.ivCancel);

    }

    @Override
    public void initLr() {
        ivCancel.setOnClickListener(v->{
            finish();
        });
        ivSave.setOnClickListener(v->{
            Log.d(TAG, "initLr: ivSave: " + summernote.getText());
        });

    }

    @Override
    public void initSetting() {

    }

    @Override
    public void initData() {

    }
}