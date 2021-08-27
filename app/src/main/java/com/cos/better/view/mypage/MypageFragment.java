package com.cos.better.view.mypage;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.config.MypageFragAdapter;
import com.cos.better.view.HomeActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MypageFragment extends Fragment {

    private static final String TAG = "MypageFragment";
    private MypageFragment mFragment = this;
    //private HomeActivity mContext;

    private MypageFragAdapter mypageFragAdapter;

    private ViewPager2 vpContainer;
    private TabLayout tabMypage;



    public MypageFragment(HomeActivity mContext) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        vpContainer = view.findViewById(R.id.vpContainer);
        tabMypage = view.findViewById(R.id.tabMypage);

        mypageFragAdapter = new MypageFragAdapter(mFragment);

        mypageFragAdapter.addFragment(new MyCalenderFragment(mFragment));
        mypageFragAdapter.addFragment(new MyDiaryFragment(mFragment));
        mypageFragAdapter.addFragment(new MyHabitFragment(mFragment));

        vpContainer.setAdapter(mypageFragAdapter);

        new TabLayoutMediator(tabMypage, vpContainer, (tab, position) -> {}).attach();

        tabMypage.setTabRippleColorResource(R.color.beige);
        tabMypage.setTabTextColors(R.color.dark_navy, R.color.brown);
        tabMypage.getTabAt(0).setIcon(R.drawable.ic_calender);
        tabMypage.getTabAt(0).setText("일정");

        tabMypage.getTabAt(1).setIcon(R.drawable.ic_diary);
        tabMypage.getTabAt(1).setText("일기");

        tabMypage.getTabAt(2).setIcon(R.drawable.ic_habit);
        tabMypage.getTabAt(2).setText("습관");



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();



    }


}