package com.cos.better.view.habit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.cos.better.R;
import com.google.android.material.button.MaterialButton;

public class MonthDialog extends Dialog {

    private static final String TAG = "MonthDialog";
    private Context mContext;
    private ToggleButton[] toggleBtns = new ToggleButton[31];
    private boolean[] IsClicked = new boolean[31];
    private MaterialButton btnPositive,btnNegative;
    private boolean dateIsChecked;
    private String dayData ="";

    public interface MyDayListener{
        void onReceivedData(String data);
    }
    private MyDayListener myDayListener;



    public MonthDialog(@NonNull Context context, MyDayListener myDayListener) {

        super(context);
        this.myDayListener = myDayListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_dialog);
        init();
        initLr();
        Log.d(TAG, "onCreate: "+dateIsChecked);
    }

    public void init(){
        toggleBtns[0] = findViewById(R.id.toggle1);
        toggleBtns[1] = findViewById(R.id.toggle2);
        toggleBtns[2] = findViewById(R.id.toggle3);
        toggleBtns[3] = findViewById(R.id.toggle4);
        toggleBtns[4] = findViewById(R.id.toggle5);
        toggleBtns[5] = findViewById(R.id.toggle6);
        toggleBtns[6] = findViewById(R.id.toggle7);
        toggleBtns[7] = findViewById(R.id.toggle8);
        toggleBtns[8] = findViewById(R.id.toggle9);
        toggleBtns[9] = findViewById(R.id.toggle10);
        toggleBtns[10] = findViewById(R.id.toggle11);
        toggleBtns[11] = findViewById(R.id.toggle12);
        toggleBtns[12] = findViewById(R.id.toggle13);
        toggleBtns[13] = findViewById(R.id.toggle14);
        toggleBtns[14] = findViewById(R.id.toggle15);
        toggleBtns[15] = findViewById(R.id.toggle16);
        toggleBtns[16] = findViewById(R.id.toggle17);
        toggleBtns[17] = findViewById(R.id.toggle18);
        toggleBtns[18] = findViewById(R.id.toggle19);
        toggleBtns[19] = findViewById(R.id.toggle20);
        toggleBtns[20] = findViewById(R.id.toggle21);
        toggleBtns[21] = findViewById(R.id.toggle22);
        toggleBtns[22] = findViewById(R.id.toggle23);
        toggleBtns[23] = findViewById(R.id.toggle24);
        toggleBtns[24] = findViewById(R.id.toggle25);
        toggleBtns[25] = findViewById(R.id.toggle26);
        toggleBtns[26] = findViewById(R.id.toggle27);
        toggleBtns[27] = findViewById(R.id.toggle28);
        toggleBtns[28] = findViewById(R.id.toggle29);
        toggleBtns[29] = findViewById(R.id.toggle30);
        toggleBtns[30] = findViewById(R.id.toggle31);

        btnPositive = findViewById(R.id.btnPositive);
        btnNegative = findViewById(R.id.btnNegative);
        btnPositive.setEnabled(false);
        btnNegative.setEnabled(false);
    }
    public void initLr(){
        toggleBtns[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[0] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[0].setBackgroundResource(R.drawable.circle_btn_on);
                } else {
                    IsClicked[0] = false;
                    toggleBtns[0].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[0] ==true){
                    dayData +=" " + toggleBtns[0].getText();
                }
            }
        });

        toggleBtns[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[1] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[1].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[1] = false;
                    toggleBtns[1].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[1] ==true){
                    dayData +=" " + toggleBtns[1].getText();
                }
            }
        });

        toggleBtns[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[2] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[2].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[2] = false;
                    toggleBtns[2].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[2] ==true){
                    dayData +=" " + toggleBtns[2].getText();
                }
            }
        });
        toggleBtns[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[3] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[3].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[3] = false;
                    toggleBtns[3].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[3] ==true){
                    dayData +=" " + toggleBtns[3].getText();
                }
            }
        });
        toggleBtns[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[4] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[4].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[4] = false;
                    toggleBtns[4].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[4] ==true){
                    dayData +=" " + toggleBtns[4].getText();
                }
            }
        });
        toggleBtns[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[5] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[5].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[5] = false;
                    toggleBtns[5].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[5] ==true){
                    dayData +=" " + toggleBtns[5].getText();
                }
            }
        });
        toggleBtns[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[6] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[6].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[6] = false;
                    toggleBtns[6].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[6] ==true){
                    dayData +=" " + toggleBtns[6].getText();
                }
            }
        });
        toggleBtns[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[7] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[7].setBackgroundResource(R.drawable.circle_btn_on);
                } else {
                    IsClicked[7] = false;
                    toggleBtns[7].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[7] ==true){
                    dayData +=" " + toggleBtns[7].getText();
                }
            }
        });

        toggleBtns[8].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[8] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[8].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[8] = false;
                    toggleBtns[8].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[8] ==true){
                    dayData +=" " + toggleBtns[8].getText();
                }
            }
        });

        toggleBtns[9].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[9] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[9].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[9] = false;
                    toggleBtns[9].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[9] ==true){
                    dayData +=" " + toggleBtns[9].getText();
                }
            }
        });
        toggleBtns[10].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[10] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[10].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[10] = false;
                    toggleBtns[10].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[10] ==true){
                    dayData +=" " + toggleBtns[10].getText();
                }
            }
        });
        toggleBtns[11].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[11] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[11].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[11] = false;
                    toggleBtns[11].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[11] ==true){
                    dayData +=" " + toggleBtns[11].getText();
                }
            }
        });
        toggleBtns[12].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[12] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[12].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[12] = false;
                    toggleBtns[12].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[12] ==true){
                    dayData +=" " + toggleBtns[12].getText();
                }

            }
        });
        toggleBtns[13].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[13] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[13].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[13] = false;
                    toggleBtns[13].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[13] ==true){
                    dayData +=" " + toggleBtns[13].getText();
                }
            }
        });
        toggleBtns[14].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[14] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[14].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[14] = false;
                    toggleBtns[14].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[14] ==true){
                    dayData +=" " + toggleBtns[14].getText();
                }
            }
        });
        toggleBtns[15].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[15] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[15].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[15] = false;
                    toggleBtns[15].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[15] ==true){
                    dayData +=" " + toggleBtns[15].getText();
                }
            }
        });
        toggleBtns[16].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[16] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[16].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[16] = false;
                    toggleBtns[16].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[16] ==true){
                    dayData +=" " + toggleBtns[16].getText();
                }
            }
        });
        toggleBtns[17].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[17] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[17].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[17] = false;
                    toggleBtns[17].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[17] ==true){
                    dayData +=" " + toggleBtns[17].getText();
                }
            }
        });
        toggleBtns[18].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[18] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[18].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[18] = false;
                    toggleBtns[18].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[18 ] ==true){
                    dayData +=" " + toggleBtns[18].getText();
                }
            }
        });
        toggleBtns[19].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[19] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[19].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[19] = false;
                    toggleBtns[19].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[19] ==true){
                    dayData +=" " + toggleBtns[19].getText();
                }
            }
        });
        toggleBtns[20].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[20] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[20].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[20] = false;
                    toggleBtns[20].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[20] ==true){
                    dayData +=" " + toggleBtns[20].getText();
                }
            }
        });
        toggleBtns[21].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[21] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[21].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[21] = false;
                    toggleBtns[21].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[21] ==true){
                    dayData +=" " + toggleBtns[21].getText();
                }
            }
        });
        toggleBtns[22].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[22] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[22].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[22] = false;
                    toggleBtns[22].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[22] ==true){
                    dayData +=" " + toggleBtns[22].getText();
                }
            }
        });
        toggleBtns[23].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[23] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[23].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[23] = false;
                    toggleBtns[23].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[23] ==true){
                    dayData +=" " + toggleBtns[23].getText();
                }
            }
        });
        toggleBtns[24].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[24] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[24].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[24] = false;
                    toggleBtns[24].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[24] ==true){
                    dayData +=" " + toggleBtns[24].getText();
                }
            }
        });
        toggleBtns[25].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[25] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[25].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[25] = false;
                    toggleBtns[25].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[25] ==true){
                    dayData +=" " + toggleBtns[25].getText();
                }
            }
        });

        toggleBtns[26].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[26] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[26].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[26] = false;
                    toggleBtns[26].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[26] ==true){
                    dayData +=" " + toggleBtns[26].getText();
                }
            }
        });
        toggleBtns[27].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[27] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[27].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[27] = false;
                    toggleBtns[27].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[27] ==true){
                    dayData +=" " + toggleBtns[27].getText();
                }
            }
        });
        toggleBtns[28].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[28] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[28].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[28] = false;
                    toggleBtns[28].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[28] ==true){
                    dayData +=" " + toggleBtns[28].getText();
                }
            }
        });
        toggleBtns[29].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[29] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[29].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[29] = false;
                    toggleBtns[29].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[29] ==true){
                    dayData +=" " + toggleBtns[29].getText();
                }
            }
        });
        toggleBtns[30].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsClicked[30] = true;
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[30].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    IsClicked[30] = false;
                    toggleBtns[30].setBackgroundResource(R.drawable.circle_btn_off);
                }
                if (IsClicked[30] ==true){
                    dayData +=" " + toggleBtns[30].getText();
                }
            }
        });

        btnPositive.setOnClickListener(v->{
            Log.d(TAG, "btnPositive: "+dateIsChecked);
            if(dateIsChecked ==true) { //하나라도 체크돼있으면 버튼 활성화
               // dateIsChecked = false;
                btnPositive.setEnabled(true);
                Log.d(TAG, "initLr: 확인 선택됨");
                myDayListener.onReceivedData(dayData);
                dismiss();
            }else{
                btnPositive.setEnabled(false);
            }
        });
        btnNegative.setOnClickListener(v->{
            if(dateIsChecked ==true) { //하나라도 체크돼있으면 버튼 활성화
                dayData ="";
                btnNegative.setEnabled(true);
                Log.d(TAG, "initLr: 취소 선택됨");
                for (int i = 0; i < toggleBtns.length; i++) {
                    toggleBtns[i].setBackgroundResource(R.drawable.circle_btn_off);
                    btnPositive.setEnabled(false);
                }
                btnNegative.setEnabled(false);
            }else{
                btnNegative.setEnabled(false);
            }
        });
    }

}