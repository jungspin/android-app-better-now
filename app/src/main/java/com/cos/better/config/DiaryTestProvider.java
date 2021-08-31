package com.cos.better.config;

import android.net.Uri;

import com.cos.better.model.Diary;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryTestProvider {

    public List<Diary> findAll(){


            List<Diary> diaryList = new ArrayList<>();
            for(int i=1; i<21; i++){
                diaryList.add(new Diary(
                        "This is the title " + i,
                        "content",
                        "2021년 8월 "+i+"일", "2021-08-30"
                ));

            }
            return diaryList;
    }
}
