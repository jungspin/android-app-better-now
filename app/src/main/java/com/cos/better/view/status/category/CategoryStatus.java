package com.cos.better.view.status.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.google.android.material.tabs.TabLayout;

public class CategoryStatus extends AppCompatActivity implements InitSetting {

    private static final String TAG = "CategoryStatus";
    private Category1mFragment category1mFragment;
    private Category3mFragment category3mFragment;
    private Category6mFragment category6mFragment;
    private CategoryAllFragment categoryAllFragment;
    private TabLayout tabStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_status);
            init();
            initData();
            initLr();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,category1mFragment)
                .commit();
    }

    @Override
    public void init() {
        category1mFragment = new Category1mFragment();
        category3mFragment = new Category3mFragment();
        category6mFragment = new Category6mFragment();
        categoryAllFragment = new CategoryAllFragment();
        tabStatus = findViewById(R.id.tabStatus);
    }

    @Override
    public void initLr() {
        tabStatus.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //Log.d(TAG, "onTabSelected: "+position);

                Fragment selected = null;
                if(position == 0){
                    selected = category1mFragment;
                } else if(position == 1){
                    selected = category3mFragment;
                } else if(position == 2){
                    selected = category6mFragment;
                } else if(position == 3){
                    selected = categoryAllFragment;
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
        tabStatus.addTab(tabStatus.newTab().setText("1개월"));
        tabStatus.addTab(tabStatus.newTab().setText("3개월"));
        tabStatus.addTab(tabStatus.newTab().setText("6개월"));
        tabStatus.addTab(tabStatus.newTab().setText("전체"));
    }
}