package com.example.bmicalculatorplus.ui.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmicalculatorplus.R;

/**
 * Fragment wyświetlający listę zakupów dla aktualnie wybranego przepisu.
 * Pobiera dane z {@link ShoppingListViewModel} i wyświetla je w postaci listy
 * składników (RecyclerView) wraz z tytułem przepisu.
 */
public class ShoppingListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ShoppingListViewModel viewModel;
    private TextView titleText;


    /**
     * Tworzy i konfiguruje strukturę widoku fragmentu.
     *
     * @param inflater  obiekt używany do "nadmuchania" layoutu XML
     * @param container kontener, do którego zostanie dołączony widok fragmentu
     * @param savedInstanceState zapisany stan (jeśli dotyczy)
     * @return główny widok fragmentu
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        titleText = view.findViewById(R.id.text_recipe_title);
        recyclerView = view.findViewById(R.id.recycler_view_shopping);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);

        return view;
    }

    /**
     * Metoda wywoływana po utworzeniu widoku fragmentu.
     * Obserwuje dane z ViewModelu i aktualizuje interfejs użytkownika,
     * w tym listę składników oraz tytuł przepisu.
     *
     * @param view               główny widok fragmentu
     * @param savedInstanceState zapisany stan
     */
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getShoppingItems().observe(getViewLifecycleOwner(), items -> recyclerView.setAdapter(new ShoppingListAdapter(items)));

        viewModel.getRecipeTitle().observe(getViewLifecycleOwner(), title -> titleText.setText(getString(R.string.ingredients_for, title)));

        viewModel.loadItemsFromRandomRecipe(requireContext());
    }

    /**
     * Czyszczenie odniesień do widoków w momencie niszczenia widoku fragmentu,
     * aby zapobiec wyciekom pamięci.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView = null;
        titleText = null;
    }
}

