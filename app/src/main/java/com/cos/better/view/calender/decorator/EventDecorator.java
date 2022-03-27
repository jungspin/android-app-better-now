package com.cos.better.view.calender.decorator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.cos.better.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {
    private static final String TAG = "EventDecorator";

    private Drawable drawable;
    private HashSet<CalendarDay> dates;

    public EventDecorator(Context mContext, Collection<CalendarDay> dates) {
        drawable = mContext.getResources().getDrawable(R.drawable.calender_set_schedule);

        this.dates = new HashSet<>(dates);
    }



    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
