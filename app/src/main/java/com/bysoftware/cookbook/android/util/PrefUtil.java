package com.bysoftware.cookbook.android.util;

/**
 * Created by sbozturk on 6.5.2017.
 */

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtil {

    private Activity activity;

    public PrefUtil(Activity activity) {
        this.activity = activity;
    }

    public void saveBio(String recipeName, String recipeIngredients, String recipeDirections, String recipePreparationTime) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("recipeName", recipeName);
        editor.putString("recipeIngredients", recipeIngredients);
        editor.putString("recipeDirections", recipeDirections);
        editor.putString("recipePreparationTime", recipePreparationTime);
        editor.apply();
    }

    public String getRecipeName() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("recipeName", null);
    }

    public String getRecipeIngredients() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("recipeIngredients", null);
    }

    public String getRecipeDirections() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("recipeDirections", null);
    }

    public String getRecipePreparationTime() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("recipePreparationTime", null);
    }

}
