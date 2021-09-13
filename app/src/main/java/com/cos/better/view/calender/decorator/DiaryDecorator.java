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

import java.util.Collection;
import java.util.HashSet;

public class DiaryDecorator implements DayViewDecorator {
    private static final String TAG = "DiaryDecorator";

    private Drawable drawable;
    private HashSet<CalendarDay> dates;

    public DiaryDecorator(Context mContext, Collection<CalendarDay> dates) {
      drawable = mContext.getResources().getDrawable(R.color.brown);

        this.dates = new HashSet<>(dates);

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {


        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        //view.setSelectionDrawable(drawable);
        view.addSpan(new ForegroundColorSpan(Color.rgb(44, 107, 178)));
        //view.setSelectionDrawable(drawable);
    }
}
