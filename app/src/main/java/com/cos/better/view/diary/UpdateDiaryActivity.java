package com.cos.better.view.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.model.Diary;
import com.cos.better.viewModel.DiaryViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.InputStream;
import java.util.Calendar;

import jp.wasabeef.richeditor.RichEditor;

public class UpdateDiaryActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "UpdateDiaryActivity";
    private UpdateDiaryActivity mContext = this;

    private ImageView ivSave, ivCancel, ivHeading, ivHeading1, ivBullet, ivQuote, ivUndo, ivRedo;
    private RichEditor mEditor;
    private TextInputEditText tfTitle;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DiaryViewModel dvm;
    Diary diary;

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diary);



        init();
        initLr();
        initSetting();
        initData();

    }


    @Override
    public void init() {
        ivSave = findViewById(R.id.ivSave);
        ivCancel = findViewById(R.id.ivCancel);
        mEditor = findViewById(R.id.editor);
        ivHeading = findViewById(R.id.ivHeading);
        ivHeading1 = findViewById(R.id.ivHeading1);
        tfTitle = findViewById(R.id.tfTitle);
        ivBullet = findViewById(R.id.ivBullet);
        ivQuote = findViewById(R.id.ivQuote);
        ivUndo = findViewById(R.id.ivUndo);
        ivRedo = findViewById(R.id.ivRedo);
    }

    @Override
    public void initLr() {
        ivCancel.setOnClickListener(v->{
            finish();
        });
        ivSave.setOnClickListener(v->{
            diary = Diary.builder()
                    .id(diary.getId())
                    .today(diary.getToday())
                    .title(tfTitle.getText().toString())
                    .content(mEditor.getHtml())
                    .user(diary.getUser())
                    //.bitmap(BitmapConverter.BitmapToString(bitmap))
                    .build();
            Log.d(TAG, "ivSave: " + diary.getId());
            Log.d(TAG, "ivSave: " + diary.getTitle());
            dvm.updateDiary(mContext, diary);

            finish();

        });




    }

    @Override
    public void initSetting() {

        ivHeading1.setOnClickListener(v->{
            mEditor.setHeading(1);
        });
        ivHeading.setOnClickListener(v->{
            mEditor.setHeading(6);
        });
        ivBullet.setOnClickListener(v->{
            mEditor.setBullets();
        });
        ivQuote.setOnClickListener(v->{
            mEditor.setBlockquote();
        });
        ivUndo.setOnClickListener(v->{
            mEditor.undo();
        });
        ivRedo.setOnClickListener(v->{
            mEditor.redo();
        });

        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Insert text here...");


        Toolbar myToolbar = findViewById(R.id.writeDiaryToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");

        dvm = new ViewModelProvider(mContext).get(DiaryViewModel.class);

    }

    @Override
    public void initData() {
        CalendarDay today = getIntent().getParcelableExtra("today");
        Log.d(TAG, "today: " + today.getDay());
        dvm.findOne(today);
        dvm.getDiary().observe(mContext, data ->{
            if (data == null){ // 데이터 없음
                Log.d(TAG, "initData: null " );
            } else {
                Log.d(TAG, "initData: " + data.getToday());
                diary = data;
                Log.d(TAG, "findOne: " + diary.getId());
                Log.d(TAG, "findOne: " + diary.getTitle());
                tfTitle.setText(data.getTitle());
                mEditor.setHtml(data.getContent());
            }
        });



    }



}