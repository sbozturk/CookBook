package com.bysoftware.cookbook.android;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *Created by yucel on 9.05.2017.
 */

public class FirebaseDBUtil {

    private String recipeName;
    private String recipeIngredients;
    private String recipeDirections;
    private String recipePreparationTime;

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference("recipes");

    public void addRecipe(String recipeName, String recipeIngredients, String recipeDirections, String recipePreparationTime){
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeDirections = recipeDirections;
        this.recipePreparationTime = recipePreparationTime;

        String recipeId = mDatabaseReference.push().getKey();

        Recipe recipe = new Recipe(recipeName, recipeIngredients, recipeDirections, recipePreparationTime);

        mDatabaseReference.child(recipeId).setValue(recipe);
    }
}
