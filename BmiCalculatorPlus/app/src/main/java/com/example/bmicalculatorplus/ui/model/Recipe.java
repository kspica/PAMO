package com.example.bmicalculatorplus.ui.model;

import java.util.List;

public class Recipe {
    private final String title;
    private final String description;
    private final String imageName;
    private final List<ShoppingItem> ingredients;

    public Recipe(String title, String description, String imageName, List<ShoppingItem> shoppingList) {
        this.title = title;
        this.description = description;
        this.imageName = imageName;
        this.ingredients = shoppingList;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageName() {
        return imageName;
    }

    public List<ShoppingItem> getIngredients() {
        return ingredients;
    }
}
