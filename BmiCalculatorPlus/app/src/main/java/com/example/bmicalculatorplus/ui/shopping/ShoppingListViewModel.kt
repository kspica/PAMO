package com.example.bmicalculatorplus.ui.shopping

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmicalculatorplus.ui.model.ShoppingItem
import com.example.bmicalculatorplus.ui.repository.RecipeRepository

/**
 * ViewModel odpowiedzialny za logikę i dane dla listy zakupów.
 * Ładuje składniki z losowego przepisu i udostępnia je widokowi poprzez obiekty LiveData.
 */
class ShoppingListViewModel : ViewModel() {

    val shoppingItems = MutableLiveData<List<ShoppingItem>>()
    val recipeTitle = MutableLiveData<String>()
    private val repository = RecipeRepository()

    /**
     * Ładuje składniki z losowo wybranego przepisu z repozytorium.
     * Jeśli brak przepisów, ustawia pustą listę i komunikat "Brak przepisu".
     */
    fun loadItemsFromRandomRecipe(context: Context) {
        val all = repository.loadRecipes(context)
        if (all.isNullOrEmpty()) {
            shoppingItems.value = emptyList()
            recipeTitle.value = "Brak przepisu"
            return
        }

        all.shuffle()
        val random = all[0]
        shoppingItems.value = random.ingredients
        recipeTitle.value = random.title
    }

    /**
     * Zwraca obserwowalną listę składników zakupowych.
     */
    fun getShoppingItems(): LiveData<List<ShoppingItem>> = shoppingItems

    /**
     * Zwraca obserwowalny tytuł aktualnego przepisu.
     */
    fun getRecipeTitle(): LiveData<String> = recipeTitle
}
