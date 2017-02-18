package com.ashl7developer.cityexplore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VenuePhotoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_photo);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("name");

        TextView textView = (TextView) findViewById(R.id.photoname_textview);
        textView.setText(cityName);
    }
}
