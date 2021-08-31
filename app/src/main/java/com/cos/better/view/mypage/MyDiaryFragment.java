package com.cos.better.view.mypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cos.better.R;
import com.cos.better.config.DiaryTestProvider;
import com.cos.better.config.InitSetting;
import com.cos.better.config.MypageDiaryAdapter;
import com.cos.better.view.HomeActivity;


public class MyDiaryFragment extends Fragment implements InitSetting {

    private static final String TAG = "MyDiaryFragment";
    private HomeActivity mContext;
    private MypageFragment mFragment;

    private RecyclerView rvDiaries;
    private MypageDiaryAdapter mypageDiaryAdapter;




    public MyDiaryFragment(MypageFragment mFragment) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_my_diary, container, false);

        rvDiaries = (RecyclerView) view.findViewById(R.id.rvDiaries);

        initAdapter();
        initData();

        return view;
    }

    @Override
    public void init() {

    }

    @Override
    public void initLr() {

    }

    @Override
    public void initAdapter() {
        Log.d(TAG, "initAdapter: ");

        rvDiaries.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        mypageDiaryAdapter = new MypageDiaryAdapter();
        rvDiaries.setAdapter(mypageDiaryAdapter);

    }

    @Override
    public void initSetting() {

    }

    @Override
    public void initData() {
        DiaryTestProvider diaryTestProvider = new DiaryTestProvider();
        mypageDiaryAdapter.addItems(diaryTestProvider.findAll());

    }
    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}