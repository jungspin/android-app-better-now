package com.cos.better.dto;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalenderTestDTO implements Serializable {
    private String title;
    private String startDate;
    private String endDate;
    //private List<CalendarDay> calendarDays;

}
