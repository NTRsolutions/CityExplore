package com.ashl7developer.cityexplore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by ASHL7 on 2/15/2017.
 * MainActivity of the app where user selects which city he likes to explore from a list
 */
public class SelectCityActivity extends AppCompatActivity {

    public static final String CLIENT_ID = "1T44UBTLMOEI01RHYUZIOPHBIYMZTWCHVPCML1IPETOM5Y1E";
    public static final String CLIENT_SECRET = "5LVUUOLMPN04VSBE54EN0OUZOEDAFMXAAAKXN1KN52MWUAOC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
    }
}
