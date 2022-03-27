package com.cos.better.view.mypage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.adapter.MypageFragAdapter;
import com.cos.better.view.HomeActivity;
import com.cos.better.view.LoginActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MypageFragment extends Fragment implements InitSetting{

    private static final String TAG = "MypageFragment";
    private MypageFragment mFragment = this;

    private Context mContext;
    private Context context;
    private Activity activity;
    private View view;

    private MypageFragAdapter mypageFragAdapter;

    private ViewPager2 vpContainer;
    private TabLayout tabMypage;

    private ImageView ivUserPhoto;
    private TextView tvUsername, tvUserEmail;
    private FloatingActionButton fabLogout;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;

        if(context instanceof Activity)
            activity = (Activity) context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, container, false);
        init();
        initLr();
        initSetting();
        initAdapter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        initData();


    }


    @Override
    public void init() {
        vpContainer = view.findViewById(R.id.vpContainer);
        tabMypage = view.findViewById(R.id.tabMypage);
        ivUserPhoto = view.findViewById(R.id.ivUserPhoto);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);
        fabLogout = view.findViewById(R.id.fabLogout);
    }

    @Override
    public void initLr() {
        fabLogout.setOnClickListener(v->{
            AuthUI.getInstance().signOut(mContext)
                    .addOnSuccessListener(task -> {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);

                    })
                    .addOnFailureListener(task ->{
                        Toast.makeText(mContext, "로그아웃에 실패했습니다", Toast.LENGTH_SHORT).show();
                        task.printStackTrace();
                    });

        });

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