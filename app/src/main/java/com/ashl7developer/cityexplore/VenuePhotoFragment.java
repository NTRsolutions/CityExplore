package com.ashl7developer.cityexplore;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by ASHL7 on 2/19/2017.
 * Fragment to show a grid of pictures for a venue
 */
public class VenuePhotoFragment extends Fragment {

    public static final String VENUE_ID = "0";

    public VenuePhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        if(isNetworkAvailable()) {
            view = inflater.inflate(R.layout.fragment_venue_photo, container, false);
            String venueId = getActivity().getIntent().getStringExtra(VENUE_ID);
        }
        else {
            view = inflater.inflate(R.layout.no_network_layout, container, false);
        }
        return view;
    }



    /**
     * Check if there is internet connection
     * @return  boolean
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
