package com.example.bmicalculatorplus.ui.model;

import com.example.bmicalculatorplus.ui.enums.BMIStatus;

public class BMIResult {
    private final Double bmiValue;
    private final BMIStatus status;

    public BMIResult(Double bmiValue, BMIStatus status) {
        this.bmiValue = bmiValue;
        this.status = status;
    }

    public Double getBmiValue() {
        return bmiValue;
    }

    public BMIStatus getStatus() {
        return status;
    }
}

