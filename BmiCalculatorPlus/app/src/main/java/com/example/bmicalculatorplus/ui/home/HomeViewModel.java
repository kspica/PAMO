package com.example.bmicalculatorplus.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bmicalculatorplus.ui.enums.BMIStatus;
import com.example.bmicalculatorplus.ui.model.BMIResult;


/**
 * ViewModel odpowiedzialny za logikę obliczania współczynnika BMI.
 * Przetwarza dane wejściowe użytkownika i udostępnia wynik w postaci LiveData.
 */
public class HomeViewModel extends ViewModel {

    private final MutableLiveData<BMIResult> bmiResult = new MutableLiveData<>();

    /**
     * Zwraca wynik obliczeń BMI jako LiveData.
     */
    public LiveData<BMIResult> getBmiResult() {
        return bmiResult;
    }

    /**
     * Oblicza BMI na podstawie wagi i wzrostu użytkownika.
     * W przypadku błędnych danych wejściowych ustawia odpowiedni status błędu.
     *
     * @param weightStr Waga jako tekst
     * @param heightStr Wzrost jako tekst
     */
    public void calculateBMI(String weightStr, String heightStr) {
        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            try {
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr) / 100;
                double bmi = weight / (height * height);

                bmiResult.setValue(new BMIResult(bmi, getBMIStatus(bmi)));
            } catch (NumberFormatException e) {
                bmiResult.setValue(new BMIResult(null, BMIStatus.INVALID_INPUT));
            }
        } else {
            bmiResult.setValue(new BMIResult(null, BMIStatus.INVALID_INPUT));
        }
    }


    /**
     * Określa kategorię BMI na podstawie wartości wskaźnika masy ciała.
     *
     * @param bmi Obliczony wskaźnik BMI
     * @return Kategoria BMI jako status
     */
    private BMIStatus getBMIStatus(double bmi) {
        if (bmi < 18.5) return BMIStatus.UNDERWEIGHT;
        else if (bmi < 24.9) return BMIStatus.OPTIMUM;
        else if (bmi < 29.9) return BMIStatus.OVERWEIGHT;
        else return BMIStatus.OBESE;
    }

}