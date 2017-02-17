package com.ashl7developer.cityexplore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.Category;
import model.Venue;

/**
 * Created by ASHL7 on 2/16/2017.
 * Adapter to populate list of venues on the listview object
 */
public class VenueListAdapter extends ArrayAdapter<Venue> {

    List<Venue> venueList;

    public VenueListAdapter(Context context, int resource, List<Venue> objects) {
        super(context, resource, objects);
        venueList = objects;
    }


    // This method indicates how the listView should look
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Venue venue = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_venue_list, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.name_textview);
        nameTextView.setText(venue.getName().toString());
        /*
        TextView locationTextView = (TextView) convertView.findViewById(R.id.location_textview);
        locationTextView.setText(venue.getLocation().getAddress());

        List<Category> categories = venue.getCategories();
        StringBuffer sb = new StringBuffer();
        for(Category category: categories) sb.append(category.getName() + ", ");
        sb.deleteCharAt(sb.length()-1);
        TextView categoryTextView = (TextView) convertView.findViewById(R.id.category_textview);
        categoryTextView.setText(sb.toString());

        ImageView thumbnailImageView = (ImageView) convertView.findViewById(R.id.thumbnail_imageview);
        thumbnailImageView.setImageResource(R.drawable.unknown_image);
        */
        return convertView;         // Return the completed view to render on screen
    }

}
