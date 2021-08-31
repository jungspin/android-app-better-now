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
    private MaterialButton btnPositive,btnNegative;
    private boolean dateIsChecked;

    public MonthDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_dialog);
        init();
        initLr();
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
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
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
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
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
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
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
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
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
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
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
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
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
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[6].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[6].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[7].setBackgroundResource(R.drawable.circle_btn_on);
                } else {
                    toggleBtns[7].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });

        toggleBtns[8].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[8].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[8].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });

        toggleBtns[9].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[9].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[9].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[10].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[10].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[10].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[11].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[11].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[11].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[12].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[12].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[12].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[13].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[13].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[13].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[14].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[14].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[14].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[15].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[15].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[15].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[16].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[16].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[16].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[17].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[17].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[17].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[18].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[18].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[18].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[19].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[19].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[19].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[20].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[20].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[20].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[21].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[21].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[21].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[22].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[22].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[22].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[23].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[23].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[23].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[24].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[24].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[24].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[25].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[25].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[25].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[25].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[25].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[25].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[26].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[26].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[26].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[27].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[27].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[27].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[28].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[28].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[28].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[29].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[29].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[29].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });
        toggleBtns[30].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dateIsChecked =true;
                    btnPositive.setEnabled(true);
                    btnNegative.setEnabled(true);
                    toggleBtns[30].setBackgroundResource(R.drawable.circle_btn_on);

                } else {
                    toggleBtns[30].setBackgroundResource(R.drawable.circle_btn_off);
                }
            }
        });

        btnPositive.setOnClickListener(v->{
            if(dateIsChecked ==true) {
                btnPositive.setEnabled(true);
                Log.d(TAG, "initLr: 확인 선택됨");
                dismiss();
            }else{
                btnPositive.setEnabled(false);
            }
        });
        btnNegative.setOnClickListener(v->{
            if(dateIsChecked ==true) {
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