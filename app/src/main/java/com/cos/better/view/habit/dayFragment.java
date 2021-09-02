package com.cos.better.view.habit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private boolean[] isClicked = new boolean[7];
    private Context mContext;
    private Activity activity;
    private String weekData ="";

    public interface MyWeekListener{
        void onReceivedData(String data);
    }
    private MyWeekListener myWeekListener;

    public dayFragment(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_day, container, false);
        Log.d(TAG, "onCreateView: 화면에 뜸");
        init();
        initLr();
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(TAG, "onAttach: 액티비티에 붙음");
        super.onAttach(context);
        mContext = context;
        if(context instanceof MyWeekListener)
            myWeekListener = (MyWeekListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: weekData "+weekData);
        myWeekListener.onReceivedData(weekData);
        myWeekListener = null;
        weekData="";
    }

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
                    isClicked[0]= true;
                } else {
                    toggleBtns[0].setBackgroundResource(R.drawable.circle_btn_off);
                    isClicked[0] = false;
                }
                if(isClicked[0]){
                    weekData +=" "+toggleBtns[0].getText();
                }
            }
        });

        toggleBtns[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleBtns[1].setBackgroundResource(R.drawable.circle_btn_on);
                    isClicked[1]= true;

                } else {
                    toggleBtns[1].setBackgroundResource(R.drawable.circle_btn_off);
                    isClicked[1] = false;
                }
                if(isClicked[1]){
                    weekData +=" "+ toggleBtns[1].getText();
                }
            }
        });

        toggleBtns[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleBtns[2].setBackgroundResource(R.drawable.circle_btn_on);
                    isClicked[2]= true;

                } else {
                    toggleBtns[2].setBackgroundResource(R.drawable.circle_btn_off);
                    isClicked[2] = false;
                }
                if(isClicked[2]){
                    weekData +=" "+  toggleBtns[2].getText();
                }
            }
        });
        toggleBtns[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleBtns[3].setBackgroundResource(R.drawable.circle_btn_on);
                    isClicked[3]= true;

                } else {
                    toggleBtns[3].setBackgroundResource(R.drawable.circle_btn_off);
                    isClicked[3] = false;
                }
                if(isClicked[3]){
                    weekData +=" "+ toggleBtns[3].getText();
                }
            }
        });
        toggleBtns[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleBtns[4].setBackgroundResource(R.drawable.circle_btn_on);
                    isClicked[4]= true;

                } else {
                    toggleBtns[4].setBackgroundResource(R.drawable.circle_btn_off);
                    isClicked[4] = false;
                }
                if(isClicked[4]){
                    weekData += " "+toggleBtns[4].getText();
                }
            }
        });
        toggleBtns[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleBtns[5].setBackgroundResource(R.drawable.circle_btn_on);
                    isClicked[5]= true;
                } else {
                    toggleBtns[5].setBackgroundResource(R.drawable.circle_btn_off);
                    isClicked[5] = false;
                }
                if(isClicked[5]){
                    weekData += " "+ toggleBtns[5].getText();
                }
            }
        });
        toggleBtns[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleBtns[6].setBackgroundResource(R.drawable.circle_btn_on);
                    isClicked[6]= true;
                } else {
                    toggleBtns[6].setBackgroundResource(R.drawable.circle_btn_off);
                    isClicked[6] = false;
                }
                if(isClicked[6]){
                    weekData +=" "+ toggleBtns[6].getText();

                }
            }
        });
    }


    @Override
    public void initData() {

    }


}