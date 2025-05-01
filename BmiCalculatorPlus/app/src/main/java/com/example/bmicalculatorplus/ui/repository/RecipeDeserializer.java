package com.example.bmicalculatorplus.ui.repository;

import com.example.bmicalculatorplus.ui.model.Recipe;
import com.example.bmicalculatorplus.ui.model.ShoppingItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Niestandardowy deserializator obiektów {@link Recipe} używany przez Gson.
 * Odpowiada za przekształcenie danych JSON reprezentujących przepis w obiekt {@link Recipe},
 * w tym jego tytuł, opis, nazwę obrazka oraz listę składników typu {@link ShoppingItem}.
 */
public class RecipeDeserializer implements JsonDeserializer<Recipe> {

    /**
     * Deserializuje obiekt JSON do instancji {@link Recipe}.
     *
     * @param json    dane wejściowe w formacie JSON
     * @param typeOfT typ obiektu, do którego ma nastąpić deserializacja
     * @param context kontekst deserializacji, używany do deserializacji zagnieżdżonych obiektów
     * @return obiekt {@link Recipe} utworzony na podstawie danych JSON
     * @throws JsonParseException jeśli JSON nie ma oczekiwanego formatu
     */
    @Override
    public Recipe deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();

        String title = obj.get("title").getAsString();
        String description = obj.get("description").getAsString();
        String imageName = obj.get("imageName").getAsString();

        List<ShoppingItem> ingredients = new ArrayList<>();
        JsonArray array = obj.getAsJsonArray("ingredients");
        for (JsonElement el : array) {
            ingredients.add(new ShoppingItem(el.getAsString()));
        }

        return new Recipe(title, description, imageName, ingredients);
    }
}