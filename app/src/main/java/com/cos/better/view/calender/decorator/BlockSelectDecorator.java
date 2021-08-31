package com.cos.better.view.calender.decorator;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class BlockSelectDecorator implements DayViewDecorator {
    private static final String TAG = "BlockSelectDecorator";

    private CalendarDay startDate;

    public BlockSelectDecorator(CalendarDay startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Log.d(TAG, "day: " + day);
        Log.d(TAG, "startDate: " + startDate);
        return day == startDate;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.parseColor("#d2d2d2")));
        view.setDaysDisabled(true);
    }
}
