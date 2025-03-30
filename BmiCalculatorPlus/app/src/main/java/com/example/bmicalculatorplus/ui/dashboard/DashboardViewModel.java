package com.example.bmicalculatorplus.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bmicalculatorplus.ui.enums.BMRStatus;
import com.example.bmicalculatorplus.ui.enums.Gender;
import com.example.bmicalculatorplus.ui.model.BMRResult;


/**
 * ViewModel odpowiedzialny za logikę obliczeń zapotrzebowania kalorycznego (BMR).
 * Przetwarza dane wejściowe użytkownika i udostępnia wynik w postaci LiveData.
 */
public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<BMRResult> bmrResult = new MutableLiveData<>();

    /**
     * Zwraca wynik obliczeń BMR jako LiveData.
     */
    public LiveData<BMRResult> getBmrResult() {
        return bmrResult;
    }

    /**
     * Oblicza BMR na podstawie płci, wagi, wzrostu i wieku użytkownika.
     * W przypadku błędnych danych wejściowych ustawia odpowiedni status błędu.
     *
     * @param gender Płeć użytkownika (MALE lub FEMALE)
     * @param weightStr Waga jako tekst
     * @param heightStr Wzrost jako tekst
     * @param ageStr Wiek jako tekst
     */
    public void calculateBMR(Gender gender, String weightStr, String heightStr, String ageStr) {
        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
            bmrResult.setValue(new BMRResult(null, BMRStatus.INVALID_INPUT));
            return;
        }

        try {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);
            int age = Integer.parseInt(ageStr);
            double neededCalories;

            if (gender == Gender.MALE) {
                neededCalories = 66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
                bmrResult.setValue(new BMRResult(neededCalories, BMRStatus.MALE_RESULT));
            } else {
                neededCalories = 655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age);
                bmrResult.setValue(new BMRResult(neededCalories, BMRStatus.FEMALE_RESULT));
            }


        } catch (NumberFormatException e) {
            bmrResult.setValue(new BMRResult(null, BMRStatus.PARSE_ERROR));
        }
    }
}