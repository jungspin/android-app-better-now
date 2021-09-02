package com.cos.better.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Habit {
    private String habitTitle;
    private int cycleCode;
    private String category;
    private String cycle;
    private String notification;
}
