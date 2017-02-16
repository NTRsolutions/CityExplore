package com.ashl7developer.cityexplore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import model.CityResponse;
import model.Group;
import model.Venue;
import model.Item;
import rest.FoursquareClient;
import rest.FoursquareInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ASHL7 on 2/15/2017.
 * Fragment to show the list of venues for a city
 */
public class VenueListFragment extends Fragment{

    private static final String TAG = "VenueListFragment.java";
    private ListView venueListView;

    public VenueListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_venue_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getActivity().getIntent();
        String cityName = intent.getStringExtra("city");


        FoursquareInterface apiService =
                FoursquareClient.getClient().create(FoursquareInterface.class);

        Call<CityResponse> call = apiService.getVenues(cityName,
                "50",
                FoursquareClient.CLIENT_ID,
                FoursquareClient.CLIENT_SECRET,
                "20170115");

        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                if(response.isSuccessful()) {
                    CityResponse cityResponse = response.body();
                    model.Response modelResponse = cityResponse.getResponse();
                    List<Group> groups = modelResponse.getGroups();
                    List<Item> items = groups.get(0).getItems();
                    Venue venue = items.get(0).getVenue();
                    Log.d(TAG, "API call was not successful. " + venue.getName());
                    showVenuesOnListview(items);
                }
                else {
                    Log.d(TAG, "API call was not successful. Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }


    /**
     * Populates the listview with the data received from foursquare client api call
     *
     * @param  items  The list of items which contains venues
     * @return      void
     */
    private void showVenuesOnListview(List<Item> items) {
        View view = getView();
        venueListView = (ListView) view.findViewById(R.id.venue_listview);

        int numRecords = items.size();
        Venue[] venues = new Venue[numRecords];
        String[] ven = new String[numRecords];

        for(int i = 0; i < numRecords; i++) {
            venues[i] = items.get(i).getVenue();
            ven[i] =  items.get(i).getVenue().getName();

        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                ven);

        venueListView.setAdapter(listAdapter);
    }


}
