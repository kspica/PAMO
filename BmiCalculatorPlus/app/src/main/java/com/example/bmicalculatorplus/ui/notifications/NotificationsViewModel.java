package com.example.bmicalculatorplus.ui.notifications;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bmicalculatorplus.ui.model.Recipe;
import com.example.bmicalculatorplus.ui.repository.RecipeRepository;

import java.util.Collections;
import java.util.List;

/**
 * ViewModel odpowiedzialny za pobieranie i losowe wybieranie przepisów kulinarnych.
 * Korzysta z repozytorium przepisów i udostępnia dane w postaci LiveData.
 */
public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();
    private final RecipeRepository repository = new RecipeRepository();

    /**
     * Wczytuje losowe przepisy z lokalnej bazy danych (np.JSON w assets).
     *
     * @param context Kontekst wymagany do dostępu do zasobów aplikacji
     */
    public void loadRandomRecipes(Context context) {
        List<Recipe> all = repository.loadRecipes(context);
        Collections.shuffle(all);
        recipes.setValue(all.subList(0, Math.min(2, all.size())));
    }

    /**
     * Zwraca aktualnie wybrane przepisy jako LiveData.
     */
    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}