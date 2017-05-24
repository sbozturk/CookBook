package com.bysoftware.cookbook.android.activity;

/**
 * Created by sbozturk on 6.5.2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bysoftware.cookbook.android.util.PrefUtil;
import com.bysoftware.cookbook.android.R;
import com.bysoftware.cookbook.android.model.Recipe;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static android.R.attr.value;


public class AddRecipeActivity extends AppCompatActivity {

    public EditText editTextRecipeName, editTextIngredients, editTextDirections, editTextPreparationTime;
    private PrefUtil prefUtil;
    public DatabaseReference mDatabase;
    public String recipeID;
    private Spinner spinnerCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_recipe);

        prefUtil = new PrefUtil(this);

        editTextRecipeName = (EditText) findViewById(R.id.editTextRecipeName);
        editTextIngredients = (EditText) findViewById(R.id.editTextIngredients);
        editTextDirections = (EditText) findViewById(R.id.editTextDirections);
        editTextPreparationTime = (EditText) findViewById(R.id.editTextPreparationTime);
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);

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
        spinnerCountry.setAdapter(dataAdapter);

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
        String recipeName = editTextRecipeName.getText().toString();
        //lowercase to uppercase
        if (recipeName.length() <= 1) {
            recipeName = recipeName.toUpperCase();
        } else {
            recipeName = recipeName.substring(0, 1).toUpperCase() + recipeName.substring(1);
        }
        String recipeIngredients = editTextIngredients.getText().toString();
        String recipeDirections = editTextDirections.getText().toString();
        String recipePreparationTime = editTextPreparationTime.getText().toString();
        String recipeOrigin = spinnerCountry.getSelectedItem().toString();
        if(recipeName.equals("")||recipeDirections.equals("")|| recipeIngredients.equals("")||recipePreparationTime.equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(AddRecipeActivity.this).create();
            String alert = getResources().getString(R.string.alert);
            String alertMessage = getResources().getString(R.string.alert_message);
            alertDialog.setTitle(alert);
            alertDialog.setMessage(alertMessage);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{
            //location value
            TrackGPS trackGPS = new TrackGPS(AddRecipeActivity.this);
            double latitude = trackGPS.getLatitude();
            double longitude = trackGPS.getLongitude();
            mDatabase = FirebaseDatabase.getInstance().getReference("recipes");
            recipeID = mDatabase.push().getKey();


            Recipe recipe = new Recipe(recipeName, recipeIngredients, recipeDirections, recipePreparationTime, recipeOrigin, longitude, latitude);

            mDatabase.child(recipeID).setValue(recipe);
            editTextRecipeName.setText("");
            editTextIngredients.setText("");
            editTextDirections.setText("");
            editTextPreparationTime.setText("");
            Toast.makeText(getApplicationContext(), getString(R.string.toastRecipe), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
