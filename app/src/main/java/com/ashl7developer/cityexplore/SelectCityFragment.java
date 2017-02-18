package com.ashl7developer.cityexplore;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Fragment to show a list of cities to select from
 */
public class SelectCityFragment extends Fragment {

    private static final String TAG = SelectCityFragment.class.getName();
    public static final String EXTRA_CITY_NAME = "0";
    private ListView cityListView;


    public SelectCityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_city, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                String cityName = ((TextView)(itemView.findViewById(android.R.id.text1))).getText().toString();
                Intent intent = new Intent(getActivity(), VenueListActivity.class);
                intent.putExtra(EXTRA_CITY_NAME, cityName);
                startActivity(intent);
            }
        };

        View view = getView();
        cityListView = (ListView) view.findViewById(R.id.city_listview);
        cityListView.setOnItemClickListener(itemClickListener);
    }
}
