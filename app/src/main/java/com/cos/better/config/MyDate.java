package com.cos.better.config;

import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyDate implements Serializable {

    private int mYear;
    private int mMonth;
    private int mDay;

    // 시간도 필요할까? 필요하면 만들자

    public Calendar getToday(){
       return Calendar.getInstance();
    }

    public MyDate getSelectedDate(Calendar today){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String[] selectedDate = sdf.format(today.getTime()).split("-");
        MyDate myDate = MyDate.builder()
                .mYear(Integer.parseInt(selectedDate[0]))
                .mMonth(Integer.parseInt(selectedDate[1]))
                .mDay(Integer.parseInt(selectedDate[2]))
                .build();
        return myDate;
    }

    public MyDate getSelectedDate(int year, int month, int day){
        MyDate myDate = MyDate.builder()
                .mYear(year)
                .mMonth(month)
                .mDay(day)
                .build();
        return myDate;
    }


}
