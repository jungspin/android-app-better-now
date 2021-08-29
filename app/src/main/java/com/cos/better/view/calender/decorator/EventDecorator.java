package com.cos.better.view.calender.decorator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.cos.better.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class EventDecorator implements DayViewDecorator {
    private static final String TAG = "EventDecorator";

    private Drawable drawable;

    public EventDecorator(Context mContext, CalendarDay day) {
        drawable = mContext.getResources().getDrawable(R.drawable.sample_calender_set_event);


    }



    @Override
    public boolean shouldDecorate(CalendarDay day) {

        if (day.equals(CalendarDay.today())){
            Log.d(TAG, "shouldDecorate: o");
        } else {
            Log.d(TAG, "shouldDecorate: x");
        }
        return day.equals(CalendarDay.today());
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
