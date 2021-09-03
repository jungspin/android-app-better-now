package com.cos.better.view.calender.decorator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.cos.better.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class TodayDecorator implements DayViewDecorator {
    private static final String TAG = "TodayDecorator";

    private Drawable drawable;

    public TodayDecorator(Context mContext) {
       // drawable = mContext.getResources().getDrawable(R.drawable.calender_set_today);

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {


        return day.equals(CalendarDay.today());
    }

    @Override
    public void decorate(DayViewFacade view) {
        //view.setSelectionDrawable(drawable);
        //view.addSpan(new ForegroundColorSpan(Color.BLACK));
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.2f));
    }
}
