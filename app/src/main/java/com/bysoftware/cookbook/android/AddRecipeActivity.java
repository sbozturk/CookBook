package com.bysoftware.cookbook.android;

/**
 * Created by sbozturk on 6.5.2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class AddRecipeActivity extends AppCompatActivity {

    public EditText editTextRecipeName, editTextIngredients, editTextDirections, editTextPreparationTime;
    private PrefUtil prefUtil;
    public DatabaseReference mDatabase;
    public String recipeID;
    private Spinner country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_recipe);

        country = (Spinner) findViewById(R.id.spinnerCountry);

        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countries);
        // set the view for the Drop down list
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set the ArrayAdapter to the spinner
        country.setAdapter(dataAdapter);

        prefUtil = new PrefUtil(this);

        editTextRecipeName = (EditText) findViewById(R.id.editTextRecipeName);
        editTextIngredients = (EditText) findViewById(R.id.editTextIngredients);
        editTextDirections = (EditText) findViewById(R.id.editTextDirections);
        editTextPreparationTime = (EditText) findViewById(R.id.editTextPreparationTime);
        showSavedPreferences();

    }

    @Override
    protected void onPause() {
        super.onPause();
        prefUtil.saveBio(editTextRecipeName.getText().toString(), editTextIngredients.getText().toString(), editTextDirections.getText().toString(), editTextPreparationTime.getText().toString());

    }

    public void showSavedPreferences() {
        editTextRecipeName.setText(prefUtil.getRecipeName());
        editTextIngredients.setText(prefUtil.getRecipeIngredients());
        editTextDirections.setText(prefUtil.getRecipeDirections());
        editTextPreparationTime.setText(prefUtil.getRecipePreparationTime());

    }

    public void addRecipe(View view) {
        saveFirebase();
        editTextRecipeName.setText("");
        editTextIngredients.setText("");
        editTextDirections.setText("");
        editTextPreparationTime.setText("");

        finish();
    }

    public void saveFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        //id düzenlenecek
        recipeID = "123";
        String recipeName = editTextRecipeName.getText().toString();
        String recipeIngredients = editTextIngredients.getText().toString();
        String recipeDirections = editTextDirections.getText().toString();
        String recipePreparationTime = editTextPreparationTime.getText().toString();

        Recipe recipe = new Recipe(recipeName, recipeIngredients, recipeDirections, recipePreparationTime);

        mDatabase.child(recipeID).setValue(recipe);
    }

}
