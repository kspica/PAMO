package com.example.bmicalculatorplus.ui.notifications;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bmicalculatorplus.R;
import com.example.bmicalculatorplus.databinding.FragmentNotificationsBinding;
import com.example.bmicalculatorplus.ui.model.Recipe;

import java.util.List;

/**
 * Fragment odpowiedzialny za wyświetlanie losowych przepisów kulinarnych.
 * Umożliwia użytkownikowi przeglądanie i rozwijanie szczegółów przepisów.
 */
public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel viewModel;

    /**
     * Tworzy i zwraca hierarchię widoku dla tego fragmentu.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Inicjalizuje widoki i ViewModel po utworzeniu widoku fragmentu.
     */
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        viewModel.getRecipes().observe(getViewLifecycleOwner(), this::showRecipes);
        binding.btnShuffle.setOnClickListener(v -> viewModel.loadRandomRecipes(requireContext()));

        viewModel.loadRandomRecipes(requireContext());
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
     * Wyświetla listę przepisów w widoku.
     * @param recipes Lista przepisów do pokazania
     */
    private void showRecipes(List<Recipe> recipes) {
        binding.recipeList.removeAllViews();
        for (Recipe recipe : recipes) {
            View recipeView = createRecipeView(recipe);
            binding.recipeList.addView(recipeView);
        }
    }

    /**
     * Tworzy widok pojedynczego przepisu i obsługuje jego rozwijanie/zamykanie.
     * @param recipe Obiekt przepisu do wyświetlenia
     * @return Widok pojedynczego przepisu
     */
    private View createRecipeView(Recipe recipe) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.recipe_item, binding.recipeList, false);

        ImageView image = view.findViewById(R.id.recipe_image);
        TextView title = view.findViewById(R.id.recipe_title);
        TextView description = view.findViewById(R.id.recipe_description);

        int imageId = getResources().getIdentifier(recipe.getImageName(), "drawable", requireContext().getPackageName());
        image.setImageResource(imageId);
        title.setText(recipe.getTitle());
        description.setText(recipe.getDescription());

        view.setOnClickListener(v -> {
            boolean expanded = description.getVisibility() == View.VISIBLE;
            TransitionManager.beginDelayedTransition((ViewGroup) view, new AutoTransition());
            description.setVisibility(expanded ? View.GONE : View.VISIBLE);
        });

        return view;
    }
}