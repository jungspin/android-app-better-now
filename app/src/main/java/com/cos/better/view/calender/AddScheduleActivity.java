package com.cos.better.view.calender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.config.MyDialogFragment;
import com.cos.better.dto.CalenderTestDTO;
import com.google.android.material.textfield.TextInputEditText;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddScheduleActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "AddScheduleActivity";
    private Context mContext = AddScheduleActivity.this;


    private SlidingUpPanelLayout splAddSchedule;
    private ImageView ivCancel, ivSave, ivSplCancel, ivSplSave;
    private TextView tvStartDate, tvEndDate;
    private TextInputEditText tfTitle;

    private MaterialCalendarView mcvSelectDate;
    private TimePicker tpSelectTime;
    TextView tvInputDate, tvInputTime;
    private CheckBox isAllDay;

    CalendarDay startDate = null;

    String selectedDate = null;
    String selectedTime = null;

    Boolean isSelect = false; // 시작일을 선택했는지 안했는지 여부
    Calendar calendar = Calendar.getInstance();
    private List<CalendarDay> calendarDays;



    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);



        init();
        initLr();
        initSetting();

    }

    @Override
    public void init() {
        splAddSchedule = findViewById(R.id.splAddSchedule);
        ivCancel = findViewById(R.id.ivCancel);
        ivSave = findViewById(R.id.ivSave);
        tfTitle = findViewById(R.id.tfTitle);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        ivSplCancel = findViewById(R.id.ivSplCancel);
        ivSplSave = findViewById(R.id.ivSplSave);
        mcvSelectDate = findViewById(R.id.mcvSelectDate);
        tpSelectTime = findViewById(R.id.tpSelectTime);
        isAllDay = findViewById(R.id.isAllDay);
        tvInputDate = findViewById(R.id.tvInputDate);
        tvInputTime = findViewById(R.id.tvInputTime);

    }

    @Override
    public void initLr() {
        // 일정 추가 창 닫기
        ivCancel.setOnClickListener(v->{
            showDialog();
        });


        // 시작일 설정 창
        tvStartDate.setOnClickListener(v->{
            isSelect = false;
            Log.d(TAG, "initLr: tvStartDate clicked");
            splAddSchedule.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            tvInputDate.setText("시작일을 선택해주세요");
            mcvSelectDate.state().edit().commit();

            tpSelectTime.setHour(calendar.get(Calendar.HOUR));
            tpSelectTime.setMinute(calendar.get(Calendar.MINUTE));

            if (isAllDay.isChecked()){
                tpSelectTime.setEnabled(false);
            }

            mcvSelectDate.setOnDateChangedListener((widget, date, selected) -> {
                startDate = date;
                selectedDate = date.getYear() + "년 " + (date.getMonth()+1) + "월 " + date.getDay() + "일";

                Log.d(TAG, "mcvSelectDate: " + selectedDate);

            });

        });

        // 시작, 종료일 설정
        ivSplSave.setOnClickListener(v->{
            //Log.d(TAG, "날짜 미선택 시: selectedDate : " + selectedDate);
            if (selectedDate == null){
                Toast.makeText(mContext, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            selectedTime =  tpSelectTime.getHour() + "시" + tpSelectTime.getMinute() + "분";

            if (!isSelect){ // 시작일 선택+하루종일선택
                tvStartDate.setText(selectedDate);

                if (!isAllDay.isChecked()){ // 하루종일을 체크 해제
                    tvStartDate.append(" " +selectedTime);
                }
            } else { // 종료일 선택+하루종일선택
                tvEndDate.setText(selectedDate);
                if (!isAllDay.isChecked()){ // 하루종일을 체크 해제
                    tvEndDate.append(" " +selectedTime);
                }
            }
//            Log.d(TAG, "tpSelectDate: getHour : " + tpSelectTime.getHour());
//            Log.d(TAG, "tpSelectDate: getMinute : " + tpSelectTime.getMinute());
            isSelect = true;
            splAddSchedule.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        });


        // 종료일 설정 창
        tvEndDate.setOnClickListener(v->{
            if (!isSelect){
                Toast.makeText(mContext, "시작일을 먼저 선택해주세요", Toast.LENGTH_SHORT).show();
                splAddSchedule.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            } else {
                Log.d(TAG, "initLr: tvEndDate clicked");
                splAddSchedule.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

                if (startDate != null) { // 시작일에서 설정한 날짜 이전은 클릭할 수 없도록
                    mcvSelectDate.state().edit().setMinimumDate(startDate).commit();
                    //mcvSelectDate.addDecorator(new BlockSelectDecorator(startDate));
                }

                mcvSelectDate.setOnRangeSelectedListener((widget, dates) -> {
                    calendarDays = dates;
                    CalendarDay date = dates.get(dates.size()-1);
                    selectedDate = date.getYear() + "년 " + (date.getMonth()+1) + "월 " + date.getDay() + "일";
                });

                tpSelectTime.setHour(calendar.get(Calendar.HOUR));
                tpSelectTime.setMinute(calendar.get(Calendar.MINUTE));

                if (isAllDay.isChecked()){ // 시작일에서 하루종일을 체크 했을 경우
                    tpSelectTime.setEnabled(false);
                }
            }


        });



        // 일정 최종 추가
        ivSave.setOnClickListener(v->{
            // 전체 정보를 가지고 viewModel 한테 넘겨야됨 -> 객체에 담아서
            // 아무것도 안하고 저장하려고 하면 막아야됨
            if (tfTitle.getText() == null || tvStartDate.getText().equals("시작일을 설정해주세요") || tvEndDate.getText().equals("종료일을 설정해주세요")){
                Toast.makeText(mContext, "모든 칸을 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            // 시간을 시간타입으로 저장할지, 스트링으로 저장할지에 대한 논의 여부 -> 데이터 만지기 시작하면 그때..?
            // 여기서부터는 테스트 입니다
            CalenderTestDTO calenderTestDTO = CalenderTestDTO.builder()
                    .title(tfTitle.getText().toString())
                    .startDate(tvStartDate.getText().toString())
                    .endDate(tvEndDate.getText().toString())
                    .build();
            Intent intent = new Intent(mContext, ShowScheduleActivity.class);
            //intent.putExtra("calendarDays" , (Parcelable) calendarDays); // 넘기면 오류가 남
            intent.putExtra("schedule", calenderTestDTO);
            setResult(1000, intent);
            finish();

        });


        // 시작, 종료일 설정 창 닫기
        ivSplCancel.setOnClickListener(v->{

            splAddSchedule.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        });

        // 하루종일 체크 하면 시간 설정 못하게
        isAllDay.setOnClickListener(view -> {
            if (isAllDay.isChecked()){
                Log.d(TAG, "initLr: isAllDay checked");
                tpSelectTime.setEnabled(false);
                // 시간을 임의로 정하나? 아님 아예 안넘기나?
            }
            else {
                tpSelectTime.setEnabled(true);
            }
        });


    }


    @Override
    public void initSetting() {
       // spl 화면 한번 더 누르면? 내려가지 못하게 막아야함..
        mcvSelectDate.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);
        // 해당 날짜 표시해줄까 말까
        //Calendar date = (Calendar) getIntent().getSerializableExtra("date");
        //mcvSelectDate.setSelectedDate(CalendarDay.from(date));



    }

    @Override
    public void initData() {

    }

    private void showDialog(){
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MyDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

}