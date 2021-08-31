package com.cos.better.model;

import android.net.Uri;


import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 임시입니다
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diary {
    private String title;
    private String content;
    //private URL imgUrl;
    private String created;
    private String updated;
}
