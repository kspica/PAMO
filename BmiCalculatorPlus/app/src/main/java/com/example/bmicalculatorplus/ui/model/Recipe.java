package com.example.bmicalculatorplus.ui.model;

public class Recipe {
    private final String title;
    private final String description;
    private final String imageName;

    public Recipe(String title, String fullDescription, String imageResId) {
        this.title = title;
        this.description = fullDescription;
        this.imageName = imageResId;
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
}
