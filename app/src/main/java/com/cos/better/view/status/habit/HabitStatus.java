package com.cos.better.view.status.habit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.view.status.category.Category1mFragment;
import com.cos.better.view.status.category.Category3mFragment;
import com.cos.better.view.status.category.Category6mFragment;
import com.cos.better.view.status.category.CategoryAllFragment;
import com.google.android.material.tabs.TabLayout;

public class HabitStatus extends AppCompatActivity implements InitSetting {

    private Habit1mFragment habit1mFragment;
    private Habit3mFragment habit3mFragment;
    private Habit6mFragment habit6mFragment;
    private HabitAllFragment habitAllFragment;
    private TabLayout tabStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_status);
        init();
        initData();
        initLr();
    }

    @Override
    public void init() {
        habit1mFragment = new Habit1mFragment();
        habit3mFragment = new Habit3mFragment();
        habit6mFragment = new Habit6mFragment();
        habitAllFragment = new HabitAllFragment();
        tabStatus = findViewById(R.id.tabStatus);
    }

    @Override
    public void initLr() {

                tabStatus.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();

                        Fragment selected = null;
                        if(position == 0){
                            selected = habit1mFragment;
                        } else if(position == 1){
                            selected = habit3mFragment;
                        } else if(position == 2){
                            selected = habit6mFragment;
                        } else if(position == 3){
                            selected = habitAllFragment;
                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,selected)
                                .commit();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
        });
    }

    @Override
    public void initData() {

    }
}