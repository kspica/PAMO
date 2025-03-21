package com.example.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * BMI Calculator - pozwala użytkownikowi wprowadzić wagę i wzrost,
 * a następnie oblicza BMI i wyświetla odpowiednią kategorię zdrowotną.
 */

public class MainActivity extends AppCompatActivity {

    private EditText editWeight, editHeight, test;
    private Button btnCalculate;
    private TextView textResult;

    /**
     * Metoda wywoływana podczas tworzenia aktywności.
     * Inicjalizuje widoki i ustawia listener do przycisku.
     *
     * @param savedInstanceState Stan zapisany poprzednio (jeśli istnieje).
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        textResult = findViewById(R.id.textResult);

        btnCalculate.setOnClickListener(v -> calculateBMI());
    }


    /**
     * Pobiera dane wejściowe użytkownika (waga i wzrost),
     * oblicza współczynnik BMI i aktualizuje wynik w interfejsie użytkownika.
     */
    private void calculateBMI() {
        String weightStr = editWeight.getText().toString();
        String heightStr = editHeight.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr) / 100;
            double bmi = weight / (height * height);

            String status = getBMIStatus(bmi);
            textResult.setText(String.format("BMI: %.2f - %s", bmi, status));
        } else {
            textResult.setText("Wprowadź poprawne wartości!");
        }
    }


    /**
     * Określa kategorię BMI na podstawie wartości współczynnika.
     *
     * @param bmi Obliczony wskaźnik masy ciała.
     * @return Łańcuch znaków reprezentujący kategorię BMI.
     */
    private String getBMIStatus(double bmi) {
        if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Optimum";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Nadwaga";
        } else {
            return "Otyłość";
        }
    }
}
