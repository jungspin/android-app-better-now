package com.cos.better.model;


import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 자바 캘린더랑 이름이 겹쳐서..
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalenderObj {
    private String title; // 일정이름
    private List<CalendarDay> calendarDayList;
    private String startTime;
    private String endTime;
    private String user;

}
