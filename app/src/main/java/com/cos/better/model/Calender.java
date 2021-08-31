package com.cos.better.model;


import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 임시 입니다.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Calender {
    private String title; // 일정이름
    private List<SimpleDateFormat> calendarDayList;


}
