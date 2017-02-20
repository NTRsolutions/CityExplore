package com.ashl7developer.cityexplore;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import JSONmodel.ExploreModel.ExploreResponse;
import JSONmodel.ExploreModel.Venue;
import JSONmodel.ExploreModel.Item;
import foursquareREST.FoursquareClient;
import foursquareREST.FoursquareInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ASHL7 on 2/16/2017.
 * Fragment to show the list of venues for a city
 */
public class VenueListFragment extends Fragment{

    private static final String TAG = VenueListFragment.class.getName();
    private ListView venueListView;
    private String cityName;


    public VenueListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view;
        if (isNetworkAvailable()) {
            view = inflater.inflate(R.layout.fragment_venue_list, container, false);
            Intent intent = getActivity().getIntent();
            cityName = intent.getStringExtra(SelectCityFragment.EXTRA_CITY_NAME);
            getVenuesFromFoursquare(cityName, null, 50, 1, FoursquareClient.API_DATE);
        }
        else {
            view = inflater.inflate(R.layout.no_network_layout, container, false);
        }
        return view;
    }


    //TODO: cache the result of listview so when configuration changes, we don't make another call to API
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    /**
     * Call to Foursqaure API get a list of all the venues
     *
     * @param  city  The target city
     * @param  category  The target category
     * @param  limit  Max number of returned result
     * @param  numPhotos  The date of the api
     * @param  date  Number of photos to return
     */
    private void getVenuesFromFoursquare(String city, String category, int limit, int numPhotos,
                                         String date) {

        Call<ExploreResponse> call;
        FoursquareInterface apiService =
                FoursquareClient.getClient().create(FoursquareInterface.class);

        if(category == null) {  //if category is not specified
            call = apiService.getVenues(FoursquareClient.CLIENT_ID,
                    FoursquareClient.CLIENT_SECRET,
                    city,
                    Integer.toString(limit),
                    Integer.toString(numPhotos),
                    date);
        }
        else {
            call = apiService.getVenuesWithCategory(FoursquareClient.CLIENT_ID,
                    FoursquareClient.CLIENT_SECRET,
                    city,
                    category,
                    Integer.toString(limit),
                    Integer.toString(numPhotos),
                    date);
        }

        call.enqueue(new Callback<ExploreResponse>() {
            private List<Venue> venues = null;

            @Override
            public void onResponse(Call<ExploreResponse> call, Response<ExploreResponse> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "API call was successful. ");
                    List<Item> items = response.body().getResponse().getGroups().get(0).getItems();
                    venues = Item.ItemsToVenues(items);
                }
                else {
                    Log.d(TAG, "API call was not successful. Error: " + response.errorBody());
                }
                showVenuesOnListview(venues);
            }

            @Override
            public void onFailure(Call<ExploreResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                showVenuesOnListview(venues);
            }
        });
    }


    /**
     * Populates fragment's listview with the data received from foursquare client api call
     *
     * @param  venues  The list of items which contains venues
     * @return  void
     */
    private void showVenuesOnListview(final List<Venue> venues) {
        if(venues == null || venues.isEmpty()) return;

        // Get the listview
        View view = getView();
        if(view != null)
            venueListView = (ListView) view.findViewById(R.id.venue_listview);

        // connecting the adapter to listview
        final VenueListAdapter venueListAdapter =
                new VenueListAdapter(getActivity(), R.layout.item_venue_list, venues);
        venueListView.setAdapter(venueListAdapter);

        // Setting up listener for items that are clicked on the list
        venueListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position != 0 && position%10 == 0) { //if clicked on every 10th items
                           onCategoryRowClick(view);
                        }
                        else {
                            Venue venue = venues.get(position);
                            onVenueRowClick(venue);
                        }
                    }
                });
    }


    /**
     * What to do when the item_venue_list.xml is clicked on listview
     *
     * @param  venue  venue item clicked
     * @return  void
     */
    private void onVenueRowClick(Venue venue) {
        if(venue == null) return;
        Intent intent = new Intent(getActivity(), VenuePhotoActivity.class);
        intent.putExtra(VenuePhotoActivity.VENUE_NAME, venue.getName());
        intent.putExtra(VenuePhotoFragment.VENUE_ID, venue.getId());
        startActivity(intent);
    }


    /**
     * What to do when the item_categories_list.xml is clicked on listview
     * get which category is clicked, then call api with the specified category
     *
     * @param  view  A reference to the item(row) View clicked
     * @return  void
     */
    private void onCategoryRowClick(View view) {

        final TextView foodTextView = (TextView) view.findViewById(R.id.food_textview);
        foodTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVenuesFromFoursquare(cityName,
                        foodTextView.getText().toString(),
                        50, 1, FoursquareClient.API_DATE);
            }
        });

        final TextView outdoorsTextView = (TextView) view.findViewById(R.id.outdoors_textview);
        outdoorsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVenuesFromFoursquare(cityName,
                        outdoorsTextView.getText().toString(),
                        50, 1, FoursquareClient.API_DATE);
            }
        });

        final TextView artsTextView = (TextView) view.findViewById(R.id.arts_textview);
        artsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVenuesFromFoursquare(cityName,
                        artsTextView.getText().toString(),
                        50, 1, FoursquareClient.API_DATE);
            }
        });
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
