package com.cos.better.view.diary;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.config.MyDate;

import jp.wasabeef.richeditor.RichEditor;

public class WriteDiaryActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "WriteDiaryActivity";
    private WriteDiaryActivity mContext = this;

    private ImageView ivSave, ivCancel;

    private RichEditor mEditor;

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        init();
        initLr();
        initSetting();
        initData();


    }


    @Override
    public void init() {
        ivSave = findViewById(R.id.ivSave);
        ivCancel = findViewById(R.id.ivCancel);

    }

    @Override
    public void initLr() {
        ivCancel.setOnClickListener(v->{
            finish();
        });
        ivSave.setOnClickListener(v->{
            Log.d(TAG, "initLr: " + mEditor.getHtml());
            String diary = mEditor.getHtml();
            Intent intent = new Intent(mContext, IntentTestActivity.class);
            intent.putExtra("diary", diary);
            startActivity(intent);
        });

    }

    @Override
    public void initSetting() {




        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbarDiaryEditor);
        setSupportActionBar(mToolbar);

        mEditor = findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setPadding(10, 10, 10, 10);

        MyDate myDate = (MyDate) getIntent().getSerializableExtra("selectedDate");
        mEditor.setPlaceholder(myDate.getMMonth()+"월 " + myDate.getMDay() + "일의 기록");

        findViewById(R.id.actionHeading2).setOnClickListener(v-> {
            Log.d(TAG, "init: actionHeading2");
            mEditor.setHeading(2);
        });

        findViewById(R.id.actionBlockquote).setOnClickListener(v -> {
            Log.d(TAG, "init: actionBlockquote");
            mEditor.setBlockquote();

        });

        findViewById(R.id.actionInsertBullets).setOnClickListener(v -> {
            Log.d(TAG, "init: actionInsertBullets");
            mEditor.setBullets();
        });

        findViewById(R.id.actionInsertImage).setOnClickListener(v -> {
            Log.d(TAG, "init: actionInsertImage");

        });

    }

    @Override
    public void initData() {

    }
}