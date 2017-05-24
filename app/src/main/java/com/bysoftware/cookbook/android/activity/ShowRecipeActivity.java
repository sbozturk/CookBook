package com.bysoftware.cookbook.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bysoftware.cookbook.android.R;
import com.bysoftware.cookbook.android.model.Recipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class ShowRecipeActivity extends AppCompatActivity {

    public TextView textViewRecipeName, textViewIngredients, textViewDirections, textViewPreparationTime, textViewOrigin;
    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.show_recipe);

        textViewRecipeName = (TextView) findViewById(R.id.textViewRecipeName);
        textViewIngredients = (TextView) findViewById(R.id.textViewIngredients);
        textViewDirections = (TextView) findViewById(R.id.textViewDirections);
        textViewPreparationTime = (TextView) findViewById(R.id.textViewPreparationTime);
        textViewOrigin = (TextView) findViewById(R.id.textViewOrigin);

        reloadFirebase();
    }

    public void reloadFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference("recipes");
        Intent item = getIntent();
        Bundle key = item.getExtras();
        String recipeID = (String) key.get("key");
        mDatabase.child(recipeID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Recipe recipe = dataSnapshot.getValue(Recipe.class);

                textViewRecipeName.setText(recipe.getRecipeName());
                textViewIngredients.setText(recipe.getRecipeIngredients());
                textViewDirections.setText(recipe.getRecipeDirections());
                textViewPreparationTime.setText(recipe.getRecipePreparationTime());
                textViewOrigin.setText(recipe.getRecipeOrigin());

                Log.d(TAG, "User name: " + recipe.getRecipePreparationTime());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}