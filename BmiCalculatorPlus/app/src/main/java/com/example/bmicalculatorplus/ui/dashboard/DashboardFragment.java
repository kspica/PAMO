package com.example.bmicalculatorplus.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bmicalculatorplus.R;
import com.example.bmicalculatorplus.databinding.FragmentDashboardBinding;
import com.example.bmicalculatorplus.ui.enums.Gender;


/**
 * Fragment odpowiedzialny za wyświetlanie kalkulatora BMR (zapotrzebowania kalorycznego).
 * Użytkownik może wprowadzić wagę, wzrost, wiek oraz płeć i otrzymać wynik.
 */
public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel viewModel;

    /**
     * Tworzy i zwraca hierarchię widoku powiązaną z fragmentem.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Inicjalizuje komponenty UI i ViewModel po utworzeniu widoku.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);


        ArrayAdapter<Gender> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                Gender.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.genderSpinner.setAdapter(adapter);
        binding.genderSpinner.setSelection(0);

        binding.btnCalculate.setOnClickListener(v -> {
            Gender selectedGender = (Gender) binding.genderSpinner.getSelectedItem();
            String weight = binding.editWeight.getText().toString();
            String height = binding.editHeight.getText().toString();
            String age = binding.age.getText().toString();

            viewModel.calculateBMR(selectedGender, weight, height, age);
        });

        viewModel.getBmrResult().observe(getViewLifecycleOwner(), result -> {
            if (result == null) return;

            String message;
            switch (result.getStatus()) {
                case MALE_RESULT:
                case FEMALE_RESULT:
                    message = getString(R.string.bmr_result, result.getCalories());
                    break;
                case INVALID_INPUT:
                    message = getString(R.string.bmr_input_error);
                    break;
                case PARSE_ERROR:
                    message = getString(R.string.bmr_parse_error);
                    break;
                default:
                    message = "";
            }
            binding.textResult.setText(message);
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
}