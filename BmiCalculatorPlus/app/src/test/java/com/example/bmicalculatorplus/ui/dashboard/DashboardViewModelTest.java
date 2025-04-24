package com.example.bmicalculatorplus.ui.dashboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.bmicalculatorplus.ui.enums.BMRStatus;
import com.example.bmicalculatorplus.ui.enums.Gender;
import com.example.bmicalculatorplus.ui.model.BMRResult;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Testy jednostkowe dla {@link DashboardViewModel}.
 */
public class DashboardViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DashboardViewModel viewModel;

    /**
     * Metoda wywoływana przed każdym testem. Inicjalizuje instancję {@link DashboardViewModel}.
     */
    @Before
    public void setUp() {
        viewModel = new DashboardViewModel();
    }

    /**
     * Testuje metodę {@link DashboardViewModel#calculateBMR(Gender, String, String, String)}
     * dla mężczyzny, sprawdzając, czy zwracany wynik jest poprawny.
     */
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

    /**
     * Testuje metodę {@link DashboardViewModel#calculateBMR(Gender, String, String, String)}
     * dla kobiety, sprawdzając, czy zwracany wynik jest poprawny.
     */
    @Test
    public void calculateBMR_shouldReturnCorrectFemaleResult() {
        viewModel.calculateBMR(Gender.FEMALE, "60", "165", "30");
        BMRResult result = viewModel.getBmrResult().getValue();

        assertNotNull(result);
        assertEquals(BMRStatus.FEMALE_RESULT, result.getStatus());

        double expectedCalories = 655.1 + (9.563 * 60) + (1.850 * 165) - (4.676 * 30);
        assertEquals(expectedCalories, result.getCalories(), 0.01);
    }


    /**
     * Testuje metodę {@link DashboardViewModel#calculateBMR(Gender, String, String, String)}
     * w przypadku podania pustej wartości dla jednego z pól, sprawdzając, czy zwracany jest
     * status {@link BMRStatus#INVALID_INPUT}.
     */
    @Test
    public void calculateBMR_shouldReturnInvalidInputForEmpty() {
        viewModel.calculateBMR(Gender.MALE, "", "175", "25");
        BMRResult result = viewModel.getBmrResult().getValue();

        assertNotNull(result);
        assertEquals(BMRStatus.INVALID_INPUT, result.getStatus());
        assertNull(result.getCalories());
    }


    /**
     * Testuje metodę {@link DashboardViewModel#calculateBMR(Gender, String, String, String)}
     * w przypadku podania nienumerycznej wartości dla jednego z pól, sprawdzając, czy zwracany jest
     * status {@link BMRStatus#PARSE_ERROR}.
     */
    @Test
    public void calculateBMR_shouldReturnParseErrorForNonNumeric() {
        viewModel.calculateBMR(Gender.FEMALE, "abc", "170", "30");
        BMRResult result = viewModel.getBmrResult().getValue();

        assertNotNull(result);
        assertEquals(BMRStatus.PARSE_ERROR, result.getStatus());
        assertNull(result.getCalories());
    }
}