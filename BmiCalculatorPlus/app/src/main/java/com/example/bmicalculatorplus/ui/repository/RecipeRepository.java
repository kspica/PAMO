package com.example.bmicalculatorplus.ui.repository;

import android.content.Context;
import android.util.Log;

import com.example.bmicalculatorplus.ui.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repozytorium odpowiedzialne za ładowanie przepisów kulinarnych z lokalnego pliku JSON.
 */
public class RecipeRepository {

    private static final String RECIPES_FILE_NAME = "recipes.json";
    private static final String TAG = RecipeRepository.class.getSimpleName();
    private final Gson gson = new Gson();

    /**
     * Ładuje listę przepisów z pliku JSON znajdującego się w folderze assets.
     *
     * @param context Kontekst aplikacji wymagany do dostępu do pliku assets
     * @return Lista obiektów typu Recipe lub pusta lista w przypadku błędu
     */
    public List<Recipe> loadRecipes(Context context) {
        if (context == null) {
            Log.w(TAG, "Context is null, returning empty recipe list");
            return Collections.emptyList();
        }

        try (InputStream is = context.getAssets().open(RECIPES_FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String recipes = reader.lines().collect(Collectors.joining());

            Type listType = new TypeToken<List<Recipe>>() {}.getType();
            return gson.fromJson(recipes, listType);

        } catch (IOException e) {
            Log.e(TAG, "Error loading recipes", e);
            return Collections.emptyList();
        }
    }
}

