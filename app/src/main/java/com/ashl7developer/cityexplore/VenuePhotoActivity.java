package com.ashl7developer.cityexplore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;

import database.BookmarkedVenueDatabase;

/**
 * Created by ASHL7 on 2/19/2017.
 * Activity to hold VenuePhotoFragment
 */
public class VenuePhotoActivity extends AppCompatActivity {

    public static final String VENUE_ID = "0";
    public static final String VENUE_NAME = "1";

    private BookmarkedVenueDatabase database;

    private TextView cityNameTextView;
    private Button bookmarkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_photo);

        Intent intent = getIntent();
        final String venueName = intent.getStringExtra(VENUE_NAME);
        final String venueId = intent.getStringExtra(VENUE_ID);

        database = new BookmarkedVenueDatabase(this);
        database.open();
        HashSet<String> bookmarkedSet = database.getBookmarkedIDs();

        cityNameTextView = (TextView) findViewById(R.id.photoname_textview);
        cityNameTextView.setText("Pictures of " + venueName);

        bookmarkButton = (Button) findViewById(R.id.bookmark_button);
        if(bookmarkedSet.contains(venueId)) {
            bookmarkButton.setEnabled(false);
        }
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.insertVenue(venueId, venueName);
                bookmarkButton.setEnabled(false);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        database.open();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        database.close();
    }

}
