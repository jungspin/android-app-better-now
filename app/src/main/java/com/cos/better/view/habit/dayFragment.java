package com.cos.better.view.habit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.cos.better.R;
import com.cos.better.config.InitSetting;

public class dayFragment extends Fragment implements InitSetting {
    private static final String TAG = "dayFragment";
    private View view;
    private ToggleButton[] toggleBtns = new ToggleButton[7];
    private Context mContext;
    private Activity activity;

    public dayFragment(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_day, container, false);
        init();
        initLr();
        return view;
    }
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        mContext = context;
//
//        if(context instanceof Activity)
//            activity = (Activity) context;
//    }

    @Override
    public void init() {
        toggleBtns[0] = view.findViewById(R.id.toggleBtnMonday);
        toggleBtns[1] = view.findViewById(R.id.toggleBtnTuesday);
        toggleBtns[2] = view.findViewById(R.id.toggleBtnWednesday);
        toggleBtns[3] = view.findViewById(R.id.toggleBtnThursday);
        toggleBtns[4] = view.findViewById(R.id.toggleBtnFriday);
        toggleBtns[5] = view.findViewById(R.id.toggleBtnSaturday);
        toggleBtns[6] = view.findViewById(R.id.toggleBtnSunday);
    }

    @Override
    public void initLr() {
        toggleBtns[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // toggleBtnMonday.setBackgroundColor(mContext.getResources().getColor(R.color.brown));
                    toggleBtns[0].setBackgroundResource(R.drawable.circle_btn_on);
                } else {
                    toggleBtns[0].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });

        toggleBtns[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // toggleBtnMonday.setBackgroundColor(mContext.getResources().getColor(R.color.brown));
                    toggleBtns[1].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[1].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });

        toggleBtns[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // toggleBtnMonday.setBackgroundColor(mContext.getResources().getColor(R.color.brown));
                    toggleBtns[2].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[2].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // toggleBtnMonday.setBackgroundColor(mContext.getResources().getColor(R.color.brown));
                    toggleBtns[3].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[3].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // toggleBtnMonday.setBackgroundColor(mContext.getResources().getColor(R.color.brown));
                    toggleBtns[4].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[4].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // toggleBtnMonday.setBackgroundColor(mContext.getResources().getColor(R.color.brown));
                    toggleBtns[5].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[5].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // toggleBtnMonday.setBackgroundColor(mContext.getResources().getColor(R.color.brown));
                    toggleBtns[6].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[6].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
    }

    @Override
    public void initData() {

    }
}