package com.cos.better.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.view.calender.CalenderFragment;
import com.cos.better.view.diary.DiaryFragment;
import com.cos.better.view.habit.HabitFragment;
import com.cos.better.view.mypage.MypageFragment;
import com.cos.better.view.status.StatusFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "HomeActivity";
    private HomeActivity mContext = this;

    private BottomNavigationView bottomNavigation;

    //DiaryViewModel vm = new ViewModelProvider(mContext).get(DiaryViewModel.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        isLogin();

        init();
        initLr();
        initNavigation();


    }

    @Override
    public void init() {
        FrameLayout fragmentContainer = findViewById(R.id.fragmentContainer);
        bottomNavigation = findViewById(R.id.bottomNavigation);

    }

    @Override
    public void initLr() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initNavigation() {
        // 초기 화면 셋팅
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new CalenderFragment(mContext), null)
                .commit();

        bottomNavigation.setOnItemSelectedListener(item -> {

            // 내가 현재 어디 있는지 알고 싶으면 얘를 전역변수로 빼서 힙이 관리하게 해야함
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.navCalender:
                    selectedFragment = new CalenderFragment(mContext);
                    break;
                case R.id.navDiary:
                    selectedFragment = new DiaryFragment(mContext);
                    break;
                case R.id.navHabit:
                    selectedFragment = new HabitFragment();
                    break;
                case R.id.navStatus:
                    selectedFragment = new StatusFragment();
                    break;
                case R.id.navMypage:
                    selectedFragment = new MypageFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, selectedFragment)
                    .commit();

            return true;
        });

    }

    @Override
    public void initSetting() {

    }

    @Override
    public void initData() {

    }

    private void isLogin(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user==null){
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.d(TAG, "isLogin: " + user.getDisplayName());
        }
    }





}