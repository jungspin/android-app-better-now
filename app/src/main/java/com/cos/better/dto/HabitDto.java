package com.cos.better.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitDto {
    private int icon;
    private String title;
    private String checkOut;
}
