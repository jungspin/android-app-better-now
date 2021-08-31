package com.cos.better.view.diary;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import com.cos.better.R;
import com.cos.better.config.InitSetting;


import in.nashapp.androidsummernote.Summernote;

public class WriteDiaryActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "WriteDiaryActivity";
    private WriteDiaryActivity mContext = this;

    private ImageView ivSave, ivCancel;
    private Summernote summernote;



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
        summernote = (Summernote) findViewById(R.id.summernote);


    }

    @Override
    public void initLr() {
        ivCancel.setOnClickListener(v->{
            finish();
        });
        ivSave.setOnClickListener(v->{
            Intent intent = new Intent(mContext, IntentTestActivity.class);
            intent.putExtra("diary", summernote.getText());
            startActivity(intent);
        });

    }

    @Override
    public void initSetting() {
        summernote.setRequestCodeforFilepicker(5);//Any Number which is not being used by other OnResultActivity


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        summernote.onActivityResult(requestCode, resultCode, intent);
    }


    @Override
    public void initData() {

    }

}