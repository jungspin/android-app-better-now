package com.cos.better.view.calender.decorator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.cos.better.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class DefaultDecorator implements DayViewDecorator {
    private static final String TAG = "DefaultDecorator";

    private Calendar calendar = Calendar.getInstance();




    @Override
    public boolean shouldDecorate(CalendarDay day) {


        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new RelativeSizeSpan(1.4f));
    }
}
