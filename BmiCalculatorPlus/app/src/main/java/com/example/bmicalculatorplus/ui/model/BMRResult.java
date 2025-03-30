package com.example.bmicalculatorplus.ui.model;

import com.example.bmicalculatorplus.ui.enums.BMRStatus;

public class BMRResult {
    private final Double calories;
    private final BMRStatus status;

    public BMRResult(Double calories, BMRStatus status) {
        this.calories = calories;
        this.status = status;
    }

    public Double getCalories() {
        return calories;
    }

    public BMRStatus getStatus() {
        return status;
    }
}

