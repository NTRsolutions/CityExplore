package com.ashl7developer.cityexplore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by ASHL7 on 2/19/2017.
 * Activity to hold VenuePhotoFragment
 */
public class VenuePhotoActivity extends AppCompatActivity {

    public static final String VENUE_NAME = "1";
    private TextView cityNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_photo);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra(VENUE_NAME);

        cityNameTextView = (TextView) findViewById(R.id.photoname_textview);
        cityNameTextView.setText("Pictures of " + cityName);
    }
}
