package com.cos.better.view.mypage;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.config.MypageFragAdapter;
import com.cos.better.view.HomeActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MypageFragment extends Fragment implements InitSetting{

    private static final String TAG = "MypageFragment";
    private MypageFragment mFragment = this;
    //private HomeActivity mContext;

    private MypageFragAdapter mypageFragAdapter;

    private ViewPager2 vpContainer;
    private TabLayout tabMypage;

    private ImageView ivUserPhoto;
    private TextView tvUsername, tvUserEmail;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



    public MypageFragment(HomeActivity mContext) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        init();
        initLr();
        initSetting();
        initAdapter();
        initData();


    }


    @Override
    public void init() {
        vpContainer = getView().findViewById(R.id.vpContainer);
        tabMypage = getView().findViewById(R.id.tabMypage);
        ivUserPhoto = getView().findViewById(R.id.ivUserPhoto);
        tvUsername = getView().findViewById(R.id.tvUsername);
        tvUserEmail = getView().findViewById(R.id.tvUserEmail);
    }

    @Override
    public void initLr() {

    }

    @Override
    public void initSetting() {

    }

    @Override
    public void initAdapter() {
        mypageFragAdapter = new MypageFragAdapter(mFragment);

        //mypageFragAdapter.addFragment(new MyCalenderFragment(mFragment));
        mypageFragAdapter.addFragment(new MyDiaryFragment(mFragment));
        mypageFragAdapter.addFragment(new MyHabitFragment(mFragment));

        vpContainer.setAdapter(mypageFragAdapter);

        new TabLayoutMediator(tabMypage, vpContainer, (tab, position) -> {}).attach();

        tabMypage.setTabRippleColorResource(R.color.beige);
        tabMypage.setTabTextColors(R.color.dark_navy, R.color.brown);
//        tabMypage.getTabAt(0).setIcon(R.drawable.ic_calender);
//        tabMypage.getTabAt(0).setText("일정");

        tabMypage.getTabAt(0).setIcon(R.drawable.ic_diary);
        tabMypage.getTabAt(0).setText("일기");

        tabMypage.getTabAt(1).setIcon(R.drawable.sample_habit_ex);
        tabMypage.getTabAt(1).setText("습관");
    }

    @Override
    public void initData() {
        Glide
            .with(mFragment)
            .load(user.getPhotoUrl())
            .centerCrop()
            .placeholder(R.drawable.img_default_user) // 사진 없을 때
            .into(ivUserPhoto);
        tvUsername.setText(user.getDisplayName());
        tvUserEmail.setText(user.getEmail());
    }
}