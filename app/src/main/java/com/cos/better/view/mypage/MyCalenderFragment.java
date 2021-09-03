package com.cos.better.view.mypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cos.better.R;
import com.cos.better.view.HomeActivity;

import java.util.HashMap;
import java.util.Map;

public class MyCalenderFragment extends Fragment {

    private static final String TAG = "MyCalenderFragment";
    private HomeActivity mContext;
    private MypageFragment mFragment;

    public MyCalenderFragment(MypageFragment mFragment) {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_calender, container, false);
        return view;
    }
}