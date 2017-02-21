package com.ashl7developer.cityexplore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by ASHL7 on 2/15/2017.
 * MainActivity of the app where user selects which city he likes to explore from a list
 */
public class SelectCityActivity extends AppCompatActivity {

    public static final String IS_FIRST_TIME= "isFirstTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        // if first time using the app, open Welcome Screen
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.password),
                Context.MODE_PRIVATE);
        boolean isFirstTime = sharedPref.getBoolean(SelectCityActivity.IS_FIRST_TIME, true);
        if(isFirstTime) {
            Intent intent = new Intent(this, WelcomeScreenActivity.class);
            startActivity(intent);
        }
    }
}
