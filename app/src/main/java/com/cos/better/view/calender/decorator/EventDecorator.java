package com.cos.better.view.calender.decorator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.cos.better.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class EventDecorator implements DayViewDecorator {
    private static final String TAG = "EventDecorator";

    private Drawable drawable;
    private HashSet<CalendarDay> dates;

    public EventDecorator(Context mContext, Collection<CalendarDay> dates) {
        drawable = mContext.getResources().getDrawable(R.drawable.sample_calender_set_event);

        this.dates = new HashSet<>(dates);
    }



    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Log.d(TAG, "shouldDecorate: " + dates.size());
        Log.d(TAG, "shouldDecorate: " + dates);

//        if (day.equals(CalendarDay.today())){
//            Log.d(TAG, "shouldDecorate: o");
//        } else {
//            Log.d(TAG, "shouldDecorate: x");
//        }
//        return day.equals(CalendarDay.today());
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
