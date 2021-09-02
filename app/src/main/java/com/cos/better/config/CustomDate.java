package com.cos.better.config;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomDate implements Serializable {

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;

    public Date setStartDate(int year, int month, int day, int hour, int minute){
        String selectedStartDate = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = sdf.parse(selectedStartDate);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date setEndDate(int year, int month, int day, int hour, int minute){
        String selectedStartDate = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = sdf.parse(selectedStartDate);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDateString(int year, int month, int day, int hour, int minute){
        return year + "년 " + month + "월 " + day + "일 " + hour + "시" + minute + "분";

    }

}
