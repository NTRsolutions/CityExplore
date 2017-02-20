package com.ashl7developer.cityexplore;


import android.content.Context;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.List;

import JSONmodel.CityResponse;
import JSONmodel.Item;
import JSONmodel.PhotoItem;
import JSONmodel.PicturesResponse;
import JSONmodel.Venue;
import foursquareREST.FoursquareClient;
import foursquareREST.FoursquareInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ASHL7 on 2/19/2017.
 * Fragment to show a grid of pictures for a venue
 */
public class VenuePhotoFragment extends Fragment {

    public static final String VENUE_ID = "0";
    private static final String TAG = VenuePhotoFragment.class.getName();
    private String venueId;
    ImageView img;

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
            venueId = getActivity().getIntent().getStringExtra(VENUE_ID);
            getVenuesFromFoursquare(venueId, 50, FoursquareClient.API_DATE);
        }
        else {
            view = inflater.inflate(R.layout.no_network_layout, container, false);
        }
        return view;
    }


    /**
     * Call to Foursqaure API get a list of all the venues
     *
     * @param  venueId  The target city
     * @param  limit  Max number of returned result
     * @param  date  Number of photos to return
     */
    private void getVenuesFromFoursquare(String venueId, int limit, String date) {

        Call<PicturesResponse> call;
        FoursquareInterface apiService =
                FoursquareClient.getClient().create(FoursquareInterface.class);
        call = apiService.getPictures(venueId, Integer.toString(limit), date);

        call.enqueue(new Callback<PicturesResponse>() {
            private List<PhotoItem> items = null;

            @Override
            public void onResponse(Call<PicturesResponse> call, Response<PicturesResponse> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "API call for pictures was successful. ");
                    items = response.body().getPhotos().getItems();
                }
                else {
                    Log.d(TAG, "API call for pictures was not successful. Error: " + response.errorBody());
                }
                showPicturesOnGrid(items);
            }

            @Override
            public void onFailure(Call<PicturesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                showPicturesOnGrid(items);
            }
        });
    }


    private void showPicturesOnGrid(List<PhotoItem> photos) {
        View view = getView();
        img = (ImageView) view.findViewById(R.id.imageview);
        String url = photos.get(0).getURLforOriginal();
        Picasso.with(getActivity())
                .load(url)
                .placeholder(R.drawable.unknown_image) // what to show if no img received
                .error(R.drawable.error_img)           // what to show if error occurd
                .into(img);
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
