package com.cos.better.dto;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalenderDTO implements Serializable {
    private String title; // 일정 이름
    private CalendarDay startCalenderDay;
    private CalendarDay endCalenderDay;
    private Date startDate;
    private Date endDate;
    private List<CalendarDay> calendarDayList;
    private Boolean isAlert;
    private String user;
    //private List<CalendarDay> calendarDays;

}
