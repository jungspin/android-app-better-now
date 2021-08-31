package com.cos.better.dto;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto implements Serializable {
    private int icon; //아이콘
    private String habitTitle; //제목
    private int count; //횟수
    private String category;
    private String cycle; //주기

}
