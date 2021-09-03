package com.cos.better.view.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.better.R;
import com.cos.better.config.CustomDate;
import com.cos.better.config.InitSetting;
import com.cos.better.model.Diary;
import com.cos.better.viewModel.DiaryViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class DetailDiaryActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "DetailDiaryActivity";

    private Context mContext = DetailDiaryActivity.this;
    private TextView tvTitle, tvContent;
    private ImageView ivPhoto, ivCancel, ivDelete;
    private DiaryViewModel vm;

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
        initData();
    }

    @Override
    public void init() {
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        ivPhoto = findViewById(R.id.ivPhoto);
        ivCancel = findViewById(R.id.ivCancel);
        ivDelete = findViewById(R.id.ivDelete);

    }

    @Override
    public void initLr() {

        ivCancel.setOnClickListener(v->{
            finish();
        });

    }

    @Override
    public void initSetting() {
        vm = new ViewModelProvider((DetailDiaryActivity)mContext).get(DiaryViewModel.class);
        CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
        Log.d(TAG, "initSetting: " + date.toString());
        findOne(CalendarDay.from(date.getYear(), (date.getMonth()), date.getDay()));
        Log.d(TAG, "initSetting: " + CalendarDay.from(date.getYear(), date.getMonth(), date.getDay()));

    }

    @Override
    public void initData() {

    }

    private void findOne(CalendarDay today){ // 사실은 한건이 아님. 몇건일지 모름
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        db.collection("diary")
                .whereEqualTo("today", today).whereEqualTo("user", user.getEmail()) // 쿼리는 이런식으로 작성하면 될듯
                .get()
                .addOnCompleteListener(runnable -> {
                    if (runnable.getResult().size() == 0){
                        Log.d(TAG, "findOne: 데이터 없음");
                        //card.setVisibility(View.INVISIBLE);
                    } else {
                        Log.d(TAG, "findAllDiary: success:  " + runnable.getResult().size());
                        Diary newDiary = runnable.getResult().toObjects(Diary.class).get(0);
                        Log.d(TAG, "findAllDiary: success:  " + newDiary.toString());
                        Log.d(TAG, "findAllDiary: success:  " + newDiary.getContent());
                        tvTitle.setText(newDiary.getTitle());
                        tvContent.setText(newDiary.getContent());


                    }


                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                    Log.d(TAG, "findOne: 에러");

                });
    }
}