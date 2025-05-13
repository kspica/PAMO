package com.example.bmicalculatorplus.ui.repository

import com.example.bmicalculatorplus.ui.model.Recipe
import com.example.bmicalculatorplus.ui.model.ShoppingItem
import com.google.gson.*
import java.lang.reflect.Type

/**
 * Niestandardowy deserializator obiektów [Recipe] używany przez Gson.
 * Odpowiada za przekształcenie danych JSON reprezentujących przepis w obiekt [Recipe],
 * w tym jego tytuł, opis, nazwę obrazka oraz listę składników typu [ShoppingItem].
 */
class RecipeDeserializer : JsonDeserializer<Recipe> {

    /**
     * Deserializuje obiekt JSON do instancji [Recipe].
     *
     * @param json    dane wejściowe w formacie JSON
     * @param typeOfT typ obiektu, do którego ma nastąpić deserializacja
     * @param context kontekst deserializacji, używany do deserializacji zagnieżdżonych obiektów
     * @return obiekt [Recipe] utworzony na podstawie danych JSON
     * @throws JsonParseException jeśli JSON nie ma oczekiwanego formatu
     */
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Recipe {
        val obj = json.asJsonObject

        val title = obj["title"].asString
        val description = obj["description"].asString
        val imageName = obj["imageName"].asString

        val ingredients = obj.getAsJsonArray("ingredients")
            .map { ShoppingItem(it.asString) }

        return Recipe(title, description, imageName, ingredients)
    }
}
