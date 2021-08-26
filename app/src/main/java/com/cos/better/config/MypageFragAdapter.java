package com.cos.better.config;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MypageFragAdapter extends FragmentStateAdapter {

    private static final String TAG = "MypageFragAdapter";

    private List<Fragment> mFragmentList = new ArrayList<>();

    public MypageFragAdapter( Fragment fragment) {
        super(fragment);
    }

    public void addFragment(Fragment fragment){
        this.mFragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}
