package com.bysoftware.cookbook.android.model;

public class Recipe {
    public String recipeName;
    public String recipeIngredients;
    public String recipeDirections;
    public String recipePreparationTime;
    public String recipeOrigin;
    public Double longitude;
    public Double latitude;

    public Recipe() {

    }

    public Recipe(String recipeName, String recipeIngredients, String recipeDirections, String recipePreparationTime, String recipeOrigin, Double longitude, Double latitude) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeDirections = recipeDirections;
        this.recipePreparationTime = recipePreparationTime;
        this.recipeOrigin = recipeOrigin;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeDirections() {
        return recipeDirections;
    }

    public void setRecipeDirections(String recipeDirections) {
        this.recipeDirections = recipeDirections;
    }

    public String getRecipePreparationTime() {
        return recipePreparationTime;
    }

    public void setRecipePreparationTime(String recipePreparationTime) {
        this.recipePreparationTime = recipePreparationTime;
    }

    public String getRecipeOrigin() {
        return recipeOrigin;
    }

    public void setRecipeOrigin(String recipeOrigin) {
        this.recipeOrigin = recipeOrigin;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
