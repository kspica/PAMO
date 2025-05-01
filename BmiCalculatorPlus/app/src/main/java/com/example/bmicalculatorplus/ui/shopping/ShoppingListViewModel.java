package com.example.bmicalculatorplus.ui.shopping;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bmicalculatorplus.ui.model.Recipe;
import com.example.bmicalculatorplus.ui.model.ShoppingItem;
import com.example.bmicalculatorplus.ui.repository.RecipeRepository;

import java.util.Collections;
import java.util.List;

/**
 * ViewModel odpowiedzialny za logikę i dane dla listy zakupów.
 * Ładuje składniki z losowego przepisu i udostępnia je widokowi poprzez obiekty LiveData.
 */
public class ShoppingListViewModel extends ViewModel {

    private final MutableLiveData<List<ShoppingItem>> shoppingItems = new MutableLiveData<>();
    private final MutableLiveData<String> recipeTitle = new MutableLiveData<>();
    private final RecipeRepository repository = new RecipeRepository();

    /**
     * Ładuje składniki z losowo wybranego przepisu z repozytorium.
     * Jeśli brak przepisów, ustawia pustą listę i komunikat "Brak przepisu".
     *
     * @param context kontekst aplikacji wymagany do odczytu danych
     */
    public void loadItemsFromRandomRecipe(Context context) {
        List<Recipe> all = repository.loadRecipes(context);
        if (all == null || all.isEmpty()) {
            shoppingItems.setValue(List.of());
            recipeTitle.setValue("Brak przepisu");
            return;
        }

        Collections.shuffle(all);
        Recipe random = all.get(0);
        shoppingItems.setValue(random.getIngredients());
        recipeTitle.setValue(random.getTitle());
    }

    /**
     * Zwraca obserwowalną listę składników zakupowych.
     *
     * @return obiekt LiveData z listą {@link ShoppingItem}
     */
    public LiveData<List<ShoppingItem>> getShoppingItems() {
        return shoppingItems;
    }

    /**
     * Zwraca obserwowalny tytuł aktualnego przepisu.
     *
     * @return obiekt LiveData z tytułem przepisu
     */
    public LiveData<String> getRecipeTitle() {
        return recipeTitle;
    }
}

