package com.ashl7developer.cityexplore;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import JSONmodel.ExploreModel.PhotoItem;
import JSONmodel.PhotosModel.Photos;
import JSONmodel.PhotosModel.PhotosBody;
import JSONmodel.PhotosModel.PhotosPhotoItem;
import JSONmodel.PhotosModel.PhotosResponse;
import foursquareREST.FoursquareClient;
import foursquareREST.FoursquareInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ASHL7 on 2/19/2017.
 * Fragment to dsiplay a grid of 50 pictures for a venue
 */
public class VenuePhotoFragment extends Fragment {

    public static final String VENUE_ID = "0";
    private static final String TAG = VenuePhotoFragment.class.getName();
    private String venueId;
    private ImageView img;

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
            getPhotosFromFoursquare(venueId, 50, FoursquareClient.API_DATE);
        }
        else {
            view = inflater.inflate(R.layout.no_network_layout, container, false);
        }
        return view;
    }


    /**
     * Call to Foursqaure API get pictures for a venue
     *
     * @param  venueId  The target city
     * @param  limit  Max number of returned result
     * @param  date  Number of photos to return
     */
    private void getPhotosFromFoursquare(String venueId, int limit, String date) {

        Log.d(TAG, "Getting pictures for venue ID: " + venueId);

        Call<PhotosBody> call;
        FoursquareInterface apiService =
                FoursquareClient.getClient().create(FoursquareInterface.class);
        call = apiService.getPictures(venueId, FoursquareClient.CLIENT_ID,
                FoursquareClient.CLIENT_SECRET, Integer.toString(limit), date);

        call.enqueue(new Callback<PhotosBody>() {

            List<PhotosPhotoItem> items;
            @Override
            public void onResponse(Call<PhotosBody> call, Response<PhotosBody> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "API call for pictures was successful. ");

                    PhotosBody photosBody = response.body();
                    PhotosResponse photosResponse = photosBody.getResponse();
                    if(photosResponse == null)
                        Log.d(TAG, "photosResponse is null");
                    Photos photos = photosResponse.getPhotos();
                    if(photos == null)
                       Log.d(TAG, "photos is null");
                    items = photos.getItems();
                    Log.d(TAG, "Got " + response.body().getResponse().getPhotos().getCount() + " Photos!");

                }
                else {
                    Log.d(TAG, "API call for pictures was not successful. Error: " + response.errorBody());
                }
                showPicturesOnGrid(items);
            }

            @Override
            public void onFailure(Call<PhotosBody> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                showPicturesOnGrid(items);
            }
        });
    }


    private void showPicturesOnGrid(List<PhotosPhotoItem> photos) {
        View view = getView();
        img = (ImageView) view.findViewById(R.id.my_imageview);
        String url = photos.get(0).getURLforOriginal();
        Log.e(TAG, "URL: " + url);

        img.setImageResource(R.drawable.unknown_image);
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
