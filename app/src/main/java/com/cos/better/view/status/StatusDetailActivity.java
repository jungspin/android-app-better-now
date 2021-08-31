package com.cos.better.view.status;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.StatusDto;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.google.android.material.textview.MaterialTextView;

public class StatusDetailActivity extends AppCompatActivity implements InitSetting {

    private Button btnBack;
    private MaterialTextView tvTitle;
    private CircleProgressBar circleProgressBar;
    private MaterialTextView tvCount;

    private Context mContext = this;
    private static final String TAG = "StatusDetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_detail);
            init();
            initData();
            initLr();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.status_detail_menu,menu);
        return true;
    }



    @Override
    public void init() {
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        circleProgressBar = findViewById(R.id.circleBar);
        tvCount = findViewById(R.id.tvCount);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch
        (item.getItemId()){
            case R.id.nav_update:
                //Intent intent = new Intent(mContext,);
                Log.d(TAG, "onOptionsItemSelected: update");
                break;
            case R.id.nav_delete:
                Log.d(TAG, "onOptionsItemSelected: delete");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void initLr() {


        btnBack.setOnClickListener(v ->{
            finish();
        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        StatusDto status = (StatusDto) intent.getSerializableExtra("statusDto");
        int progress = (int)((float)status.getCount()/30*100);
        Log.d(TAG, "initData: "+status.toString());
        tvTitle.setText("["+status.getCategory()+"] "+status.getHabitTitle());
        circleProgressBar.setProgress(progress);
        tvCount.setText(status.getCount()+"회/"+30+"회"); //변수로 변경
    }
}