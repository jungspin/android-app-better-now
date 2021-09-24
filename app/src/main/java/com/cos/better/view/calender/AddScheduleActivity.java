package com.cos.better.view.calender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cos.better.R;
import com.cos.better.config.AlarmReceiver;
import com.cos.better.config.CustomDate;
import com.cos.better.config.InitSetting;
import com.cos.better.config.MyDialogFragment;
import com.cos.better.dto.CalenderDTO;
import com.cos.better.viewModel.CalenderViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AddScheduleActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "AddScheduleActivity";
    private Context mContext = AddScheduleActivity.this;


    private SlidingUpPanelLayout splAddSchedule;
    private ImageView ivCancel, ivSave, ivSplCancel, ivSplSave;
    private TextView tvStartDate, tvEndDate, tvLetMeAlert;
    private TextInputEditText tfTitle;

    private MaterialCalendarView mcvSelectDate;
    private TimePicker tpSelectStartTime, tpSelectEndTime;
    TextView tvInputDate, tvInputTime;
    private CheckBox isAllDay, isAlert;

    private CalenderViewModel vm;

    CustomDate startDate = new CustomDate();
    CustomDate endDate = new CustomDate();

    Calendar calendar = Calendar.getInstance();
    private List<CalendarDay> calendarDays = null;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private AlarmManager alarmManager;
    private GregorianCalendar mCalender;

    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;



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
    protected void onResume() {
        super.onResume();
        initData();
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
        tpSelectStartTime = findViewById(R.id.tpSelectStartTime);
        isAllDay = findViewById(R.id.isAllDay);
        tvInputDate = findViewById(R.id.tvInputDate);
        tvInputTime = findViewById(R.id.tvInputTime);
        tpSelectEndTime = findViewById(R.id.tpSelectEndTime);
        isAlert = findViewById(R.id.isAlert);
        tvLetMeAlert = findViewById(R.id.tvLetMeAlert);

    }

    @Override
    public void initLr() {
        // 일정 추가 창 닫기
        ivCancel.setOnClickListener(v->{
            Log.d(TAG, "initLr: 닫기 클릭됨");

            showDialog();

        });
        // 날짜 지정 창 ========================================================
        tvStartDate.setOnClickListener(v->{

            splAddSchedule.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            tvInputDate.setText("시작일을 선택해주세요");

            // 기본값 현재시각
            tpSelectStartTime.setHour(calendar.get(Calendar.HOUR));
            tpSelectStartTime.setMinute(calendar.get(Calendar.MINUTE));

            tpSelectEndTime.setHour(calendar.get(Calendar.HOUR));
            tpSelectEndTime.setMinute(calendar.get(Calendar.MINUTE));

            startDate.setHour(tpSelectStartTime.getHour());
            startDate.setMinute(tpSelectEndTime.getMinute());

            endDate.setHour(tpSelectStartTime.getHour());
            endDate.setMinute(tpSelectEndTime.getMinute());

            // 하루종일 체크한 경우
            if (isAllDay.isChecked()){
                tpSelectStartTime.setEnabled(false);
                tpSelectEndTime.setEnabled(false);
            }

            // 하루짜리 일정일 경우
            mcvSelectDate.setOnDateChangedListener((widget, date, selected) -> {
                startDate.setYear(date.getYear());
                startDate.setMonth(date.getMonth());
                startDate.setDay(date.getDay());

                endDate.setYear(date.getYear());
                endDate.setMonth(date.getMonth());
                endDate.setDay(date.getDay());
            });

            // 범위로 지정할 경우
            mcvSelectDate.setOnRangeSelectedListener((widget, dates) -> {
                calendarDays = dates;
                CalendarDay date = dates.get(dates.size()-1);
                endDate.setYear(date.getYear());
                endDate.setMonth(date.getMonth());
                endDate.setDay(date.getDay());

                Log.d(TAG, "calendarDays: 범위로 지정할 경우: " + calendarDays.size());

            });

            // 시작 시각 지정
            tpSelectStartTime.setOnTimeChangedListener((timePicker, i, i1) -> {
                startDate.setHour(timePicker.getHour());
                startDate.setMinute(timePicker.getMinute());

                // 시작 시간으로 가야함
                endDate.setHour(tpSelectStartTime.getHour());
                endDate.setMinute(tpSelectStartTime.getMinute());
            });

            // 종료 시각 설정
            tpSelectEndTime.setOnTimeChangedListener((timePicker, i, i1) -> {
                endDate.setHour(timePicker.getHour());
                endDate.setMinute(timePicker.getMinute());
            });

        });

        // 시작, 종료일 설정 ============================================================
        ivSplSave.setOnClickListener(v->{
            //Log.d(TAG, "날짜 미선택 시: selectedDate : " + selectedDate);
            if (startDate.getMonth() == 0 || endDate.getMonth() == 0){
                Toast.makeText(mContext, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            //selectedTime =  tpSelectStartTime.getHour() + "시" + tpSelectStartTime.getMinute() + "분";
            Log.d(TAG, "시작일: " + startDate.toString());
            Log.d(TAG, "종료일: " + endDate.toString());

            Log.d(TAG, "tvStartDate: " +
                    startDate.getDateString(startDate.getYear(), (startDate.getMonth()+1), startDate.getDay(), startDate.getHour(), startDate.getMinute()));
            Log.d(TAG, "tvEndDate: " +
                    endDate.getDateString(endDate.getYear(), (endDate.getMonth()+1), endDate.getDay(), endDate.getHour(), endDate.getMinute()));

            tvStartDate.setText(startDate.getDateString(startDate.getYear(), (startDate.getMonth()+1), startDate.getDay(), startDate.getHour(), startDate.getMinute()));
            tvEndDate.setText(endDate.getDateString(endDate.getYear(), (endDate.getMonth()+1), endDate.getDay(), endDate.getHour(), endDate.getMinute()));

            splAddSchedule.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

        });


        // 일정 최종 추가
        ivSave.setOnClickListener(v->{
            // 전체 정보를 가지고 viewModel 한테 넘겨야됨 -> 객체에 담아서
            // 아무것도 안하고 저장하려고 하면 막아야됨
            if (tfTitle.getText() == null || tvStartDate.getText().equals("시작일을 설정해주세요") || tvEndDate.getText().equals("종료일을 설정해주세요")){
                Toast.makeText(mContext, "모든 칸을 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            Date start = startDate.setStartDate(startDate.getYear(), (startDate.getMonth()+1), startDate.getDay(), startDate.getHour(), startDate.getMinute());
            Date end = endDate.setStartDate(endDate.getYear(), (endDate.getMonth()+1), endDate.getDay(), endDate.getHour(), endDate.getMinute());


            if (calendarDays==null){
                Log.d(TAG, "initLr: 범위선택한적없음");
                calendarDays = new ArrayList<>();
                calendarDays.add(CalendarDay.from(start));
                //calendarDays.add(CalendarDay.from(end));

            }
            Log.d(TAG, "initLr: Date ivSave : " + start);
            Log.d(TAG, "initLr:Date ivSave : " + end);

            Log.d(TAG, "initLr:ivSave : " + CalendarDay.from(start).getDate());
            Log.d(TAG, "initLr:ivSave : " + CalendarDay.from(end).getDate());


            CalenderDTO calenderDTO = CalenderDTO.builder()
                    .title(tfTitle.getText().toString())
                    .startDate(start)
                    .calendarDayList(calendarDays)
                    .isAlert(isAlert.isChecked()?true:false)
                    .user(user.getProviderId()+user.getUid())
                    .startCalenderDay(CalendarDay.from(start))
                    .endCalenderDay(CalendarDay.from(end))
                    .endDate(end)
                    .build();

            if(calenderDTO.getIsAlert() == true){
                Log.d(TAG, "calenderDTO.getStartDate(): " + calenderDTO.getStartDate());
                setAlarm(start);
            }

            Log.d(TAG, "ivSave: " + calenderDTO.getIsAlert());
            vm.insertSchedule(mContext, calenderDTO);

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
                tpSelectStartTime.setEnabled(false);
                startDate.setHour(0);
                startDate.setMinute(0);

                tpSelectEndTime.setEnabled(false);
                endDate.setHour(0);
                endDate.setMinute(0);

                isAlert.setVisibility(View.INVISIBLE);
                tvLetMeAlert.setVisibility(View.INVISIBLE);

                // 시간을 임의로 정하나? 아님 아예 안넘기나?
            }
            else {
                tpSelectStartTime.setEnabled(true);
                tpSelectEndTime.setEnabled(true);

                isAlert.setVisibility(View.VISIBLE);
                isAlert.setClickable(true);
                if (isAlert.isChecked()){
                    isAlert.setChecked(false);
                }
            }
        });

        isAlert.setOnClickListener(v->{
            if(isAlert.isChecked()){ // 알림 설정 해줘
                tvLetMeAlert.setVisibility(View.VISIBLE);
            } else { // 필요없어
                tvLetMeAlert.setVisibility(View.INVISIBLE);
            }
        });


    }


    @Override
    public void initSetting() {
       // spl 화면 한번 더 누르면? 내려가지 못하게 막아야함..
        mcvSelectDate.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);
        // 해당 날짜 표시해줄까 말까
        CustomDate date = (CustomDate) getIntent().getSerializableExtra("date");
        mcvSelectDate.setSelectedDate(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()));

        startDate.setYear(date.getYear());
        startDate.setMonth(date.getMonth()-1);
        startDate.setDay(date.getDay());

        endDate.setYear(date.getYear());
        endDate.setMonth(date.getMonth()-1);
        endDate.setDay(date.getDay());



        tpSelectStartTime.setIs24HourView(true);
        tpSelectEndTime.setIs24HourView(true);

        tvLetMeAlert.setVisibility(View.INVISIBLE);

        vm = new ViewModelProvider((AddScheduleActivity)mContext).get(CalenderViewModel.class);




    }

    @Override
    public void initData() {



    }


    private void showDialog(){
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MyDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    private void setAlarm(Date startDate) {
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //AlarmReceiver에 값 전달
        Intent receiverIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, 0);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        Log.d(TAG, "setAlarm: " + calendar.getTimeInMillis());

        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);
    }

}