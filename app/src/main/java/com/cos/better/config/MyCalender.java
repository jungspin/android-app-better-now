package com.cos.better.config;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.cos.better.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class MyCalender implements DayViewDecorator {

    private CalendarDay date = CalendarDay.today();
    private static final String TAG = "MyCalender";
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return (day.equals(date));
    }

    @Override
    public void decorate(DayViewFacade view) {
        Log.d(TAG, "decorate: " + date.getDay());
        view.addSpan(Typeface.BOLD);
    }
}
