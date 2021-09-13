package com.cos.better.view.diary;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.cos.better.R;
import com.cos.better.config.BitmapConverter;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.model.Diary;
import com.cos.better.viewModel.DiaryController;
import com.cos.better.viewModel.DiaryViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.prolificinteractive.materialcalendarview.CalendarDay;


import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class WriteDiaryActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "WriteDiaryActivity";
    private WriteDiaryActivity mContext = this;

    private ImageView ivSave, ivCancel, ivHeading, ivHeading1, ivBullet, ivQuote, ivUndo, ivRedo;
    private RichEditor mEditor;
    private TextInputEditText tfTitle;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DiaryViewModel dvm;
    Bitmap bitmap;

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

            CalenderDayDTO date = (CalenderDayDTO) getIntent().getSerializableExtra("date");

            Diary diary = Diary.builder()
                    .today(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()))
                    .title(tfTitle.getText().toString())
                    .content(mEditor.getHtml())
                    .user(user.getEmail())
                    .build();
            dvm.insertDiary(mContext, diary);
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


    }







}