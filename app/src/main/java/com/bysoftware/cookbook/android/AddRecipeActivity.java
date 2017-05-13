package com.bysoftware.cookbook.android;

/**
 * Created by sbozturk on 6.5.2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddRecipeActivity extends AppCompatActivity {

    private EditText editTextRecipeName, editTextIngredients, editTextDirections, editTextPreparationTime;
    private PrefUtil prefUtil;
    FirebaseDBUtil firebaseDBUtil;

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_recipe);

        prefUtil = new PrefUtil(this);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextRecipeName = (EditText) findViewById(R.id.editTextRecipeName);
        editTextIngredients = (EditText) findViewById(R.id.editTextIngredients);
        editTextDirections = (EditText) findViewById(R.id.editTextDirections);
        editTextPreparationTime = (EditText) findViewById(R.id.editTextPreparationTime);
        showSavedPreferences();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDBUtil.addRecipe(editTextRecipeName.getText().toString(),
                        editTextIngredients.getText().toString(),
                        editTextDirections.getText().toString(),
                        editTextPreparationTime.getText().toString());
            }
        });
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
