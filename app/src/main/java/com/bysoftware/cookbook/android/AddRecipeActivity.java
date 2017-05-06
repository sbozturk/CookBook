package com.bysoftware.cookbook.android;

/**
 * Created by sbozturk on 6.5.2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class AddRecipeActivity extends AppCompatActivity {

    public EditText editTextRecipeName, editTextIngredients, editTextDirections, editTextPreparationTime;
    private PrefUtil prefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);

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
}
