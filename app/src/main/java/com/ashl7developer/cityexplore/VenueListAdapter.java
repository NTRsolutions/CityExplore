package com.ashl7developer.cityexplore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import JSONmodel.Category;
import JSONmodel.Venue;

/**
 * Created by ASHL7 on 2/16/2017.
 * Adapter to populate list of venues on the listview object
 */
public class VenueListAdapter extends ArrayAdapter<Venue> {

    List<Venue> venueList;
    // row layout types
    private static final int REGULAR_ITEM_TYPE = 0;
    private static final int CATEGORY_ITEM_TYPE = 1;


    public VenueListAdapter(Context context, int resource, List<Venue> objects) {
        super(context, resource, objects);
        venueList = objects;
    }


    // returns number of different layouts for row items
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    // returns type of the layout based on the position of the item on the list
    // TODO: first time is every 10 items, but then goes to every 9th item, fix it!
    @Override
    public int getItemViewType(int position) {
        return (position != 0 && position%10 == 0) ? CATEGORY_ITEM_TYPE : REGULAR_ITEM_TYPE;
    }

    // This method indicates how the listView should look
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // edge case
        if(venueList == null || venueList.isEmpty()) {
            // TODO what to show when there is no element
            return convertView;
        }
        int layoutType = getItemViewType(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            switch (layoutType) {
                case REGULAR_ITEM_TYPE:
                    Venue venue = getItem(position);

                    convertView = LayoutInflater.from(getContext()).inflate(
                            R.layout.item_venue_list, parent, false);

                    TextView nameTextView = (TextView) convertView.findViewById(R.id.name_textview);
                    nameTextView.setText(venue.getName().toString());

                    TextView locationTextView = (TextView) convertView.findViewById(R.id.location_textview);
                    locationTextView.setText(venue.getLocation().getAddress());

                    List<Category> categories = venue.getCategories();
                    StringBuffer sb = new StringBuffer();
                    for(Category category: categories) sb.append(category.getName() + ", ");
                    sb.delete(sb.length()-2, sb.length());
                    TextView categoryTextView = (TextView) convertView.findViewById(R.id.category_textview);
                    categoryTextView.setText(sb.toString());

                    ImageView thumbnailImageView = (ImageView) convertView.findViewById(R.id.thumbnail_imageview);
                    thumbnailImageView.setImageResource(R.drawable.unknown_image);

                    break;
                case CATEGORY_ITEM_TYPE:
                    convertView = LayoutInflater.from(getContext()).inflate(
                            R.layout.item_categories_list, parent, false);
                    break;
            }

        }
        return convertView;         // Return the completed view to render on screen
    }

}
