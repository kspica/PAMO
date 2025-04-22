package com.example.bmicalculatorplus.ui.dashboard;

import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.bmicalculatorplus.ui.enums.BMRStatus;
import com.example.bmicalculatorplus.ui.enums.Gender;
import com.example.bmicalculatorplus.ui.model.BMRResult;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DashboardViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DashboardViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new DashboardViewModel();
    }

    @Test
    public void calculateBMR_shouldReturnCorrectMaleResult() {
        String weight = "70";
        String height = "175";
        String age = "25";

        viewModel.calculateBMR(Gender.MALE, weight, height, age);
        BMRResult result = viewModel.getBmrResult().getValue();

        assertNotNull(result);
        assertEquals(BMRStatus.MALE_RESULT, result.getStatus());

        double expectedCalories = 66.5 + (13.75 * 70) + (5.003 * 175) - (6.755 * 25);
        assertEquals(expectedCalories, result.getCalories(), 0.01);
    }

    @Test
    public void calculateBMR_shouldReturnCorrectFemaleResult() {
        viewModel.calculateBMR(Gender.FEMALE, "60", "165", "30");
        BMRResult result = viewModel.getBmrResult().getValue();

        assertNotNull(result);
        assertEquals(BMRStatus.FEMALE_RESULT, result.getStatus());

        double expectedCalories = 655.1 + (9.563 * 60) + (1.850 * 165) - (4.676 * 30);
        assertEquals(expectedCalories, result.getCalories(), 0.01);
    }

    @Test
    public void calculateBMR_shouldReturnInvalidInputForEmpty() {
        viewModel.calculateBMR(Gender.MALE, "", "175", "25");
        BMRResult result = viewModel.getBmrResult().getValue();

        assertNotNull(result);
        assertEquals(BMRStatus.INVALID_INPUT, result.getStatus());
        assertNull(result.getCalories());
    }

    @Test
    public void calculateBMR_shouldReturnParseErrorForNonNumeric() {
        viewModel.calculateBMR(Gender.FEMALE, "abc", "170", "30");
        BMRResult result = viewModel.getBmrResult().getValue();

        assertNotNull(result);
        assertEquals(BMRStatus.PARSE_ERROR, result.getStatus());
        assertNull(result.getCalories());
    }
}