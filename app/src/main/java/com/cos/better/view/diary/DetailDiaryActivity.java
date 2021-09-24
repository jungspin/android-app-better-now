package com.cos.better.view.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.better.R;
import com.cos.better.config.CustomDate;
import com.cos.better.config.InitSetting;
import com.cos.better.model.Diary;

import com.cos.better.viewModel.DiaryViewModel;

import com.prolificinteractive.materialcalendarview.CalendarDay;


public class DetailDiaryActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "DetailDiaryActivity";

    private Context mContext = DetailDiaryActivity.this;
    private TextView tvTitle, tvContent;
    private ImageView ivCancel, ivDelete, ivLinkUpdate;
    private DiaryViewModel vm;
    private Diary diary;

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diary);

        init();
        initLr();
        initSetting();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void init() {
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        ivCancel = findViewById(R.id.ivCancel);
        ivDelete = findViewById(R.id.ivDelete);
        ivLinkUpdate = findViewById(R.id.ivLinkUpdate);

    }

    @Override
    public void initLr() {

        ivCancel.setOnClickListener(v->{
            finish();
        });
        ivLinkUpdate.setOnClickListener(v->{
            CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
            //Log.d(TAG, "ivLinkUpdate: " + CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));
            Intent intent = new Intent(mContext, UpdateDiaryActivity.class);
            intent.putExtra("today", CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));
            startActivity(intent);
        });

        ivDelete.setOnClickListener(v->{
            vm.deleteDiary(mContext, diary.getId());
            finish();
        });

    }

    @Override
    public void initSetting() {
        vm = new ViewModelProvider((DetailDiaryActivity)mContext).get(DiaryViewModel.class);
        CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
        Log.d(TAG, "initSetting 넘어온 데이터 : " + date.toString());
        //findOne(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));
        Log.d(TAG, "initSetting: " + CalendarDay.from(date.getYear(), date.getMonth(), date.getDay()));

    }

    @Override
    public void initData() {
        CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
        vm.findOne(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));
        vm.getDiary().observe((DetailDiaryActivity)mContext, data ->{
            if (data == null){
                Log.d(TAG, "initData: null " );
            } else {
                diary = data;
                Log.d(TAG, "initData: " + data.getToday());
                tvTitle.setText(data.getTitle());
                tvContent.setText(Html.fromHtml(data.getContent()));
            }

        });

    }

}