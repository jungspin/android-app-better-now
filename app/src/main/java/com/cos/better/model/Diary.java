package com.cos.better.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;


import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 임시입니다
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diary implements Serializable {
    private CalendarDay today;
    private String id;
    private String title;
    private String content;
    private String user;
}
