package com.bysoftware.cookbook.android;

/**
 * Created by bugra on 7.5.2017.
 */

public class Recipe {
    public String recipeName;
    public String recipeIngredients;
    public String recipeDirections;
    public String recipePreparationTime;

    public Recipe() {

    }

    public Recipe(String recipeName, String recipeIngredients, String recipeDirections, String recipePreparationTime) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeDirections = recipeDirections;
        this.recipePreparationTime = recipePreparationTime;

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
}
