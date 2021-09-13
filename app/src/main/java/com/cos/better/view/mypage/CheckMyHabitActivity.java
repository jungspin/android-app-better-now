package com.cos.better.view.mypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CheckMyHabitActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "CheckMyHabitActivity";
    private ImageView ivCancel;
    private MaterialButton btnComplete;
    private TextInputEditText etHabitMemo;
    private FirebaseFirestore db;
    private String habitMemo,title;
    private MaterialAutoCompleteTextView tvTitle;
    private boolean isChecked;

    private Map<String, Object> checkHabit = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_habit);
        init();
        initData();
        initLr();
        addDB();

    }

    @Override
    public void init() {
        ivCancel = findViewById(R.id.ivCancel);
        btnComplete = findViewById(R.id.btnComplete);
        etHabitMemo = findViewById(R.id.etHabitMemo);
        tvTitle = findViewById(R.id.tvTitle);
    }

    @Override
    public void initLr() {
        ivCancel.setOnClickListener(v -> {
            finish();
        });
        btnComplete.setOnClickListener(v ->{
            isChecked = true;
            // Intent intent = new Intent()

            finish();

        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("habitTitle");
        tvTitle.setText(title);
    }
    public void addDB(){
        db = FirebaseFirestore.getInstance();

        checkHabit.put("isChecked",isChecked);
        checkHabit.put("habitMemo",habitMemo);

        db.collection("habits").document().collection("checkHabit")
                .add(checkHabit)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+e);
                    }
                });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(isChecked)
//
//
//    }
}