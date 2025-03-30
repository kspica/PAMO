package com.example.bmicalculatorplus.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bmicalculatorplus.R;
import com.example.bmicalculatorplus.databinding.FragmentHomeBinding;
import com.example.bmicalculatorplus.ui.enums.BMIStatus;


/**
 * Fragment odpowiedzialny za wyświetlanie kalkulatora BMI.
 * Użytkownik wprowadza wagę i wzrost, a aplikacja oblicza i wyświetla wynik BMI.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    /**
     * Tworzy i zwraca widok fragmentu.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Inicjalizuje UI oraz obserwacje LiveData po utworzeniu widoku.
     */
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding.btnCalculate.setOnClickListener(v -> {
            String weight = binding.editWeight.getText().toString();
            String height = binding.editHeight.getText().toString();
            viewModel.calculateBMI(weight, height);
        });

        viewModel.getBmiResult().observe(getViewLifecycleOwner(), result -> {
            if (result == null || result.getStatus() == BMIStatus.INVALID_INPUT) {
                binding.textResult.setText(getString(R.string.bmi_bmr_invalid_input));
            } else {
                String statusText = getStatusText(result.getStatus());
                binding.textResult.setText(
                        getString(R.string.bmi_result_format, result.getBmiValue(), statusText)
                );
            }
        });
    }

    /**
     * Czyści referencje do widoków po zniszczeniu widoku fragmentu.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Zwraca tekstowy opis statusu BMI na podstawie wyliczonej kategorii.
     * @param status Status BMI (np. niedowaga, otyłość)
     * @return Opis statusu z zasobów string.xml
     */
    private String getStatusText(BMIStatus status) {
        switch (status) {
            case UNDERWEIGHT:
                return getString(R.string.bmi_underweight);
            case OPTIMUM:
                return getString(R.string.bmi_optimum);
            case OVERWEIGHT:
                return getString(R.string.bmi_overweight);
            case OBESE:
                return getString(R.string.bmi_obese);
            default:
                return "";
        }
    }
}