package com.ashl7developer.cityexplore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import JSONmodel.Category;
import JSONmodel.Venue;

/**
 * Created by ASHL7 on 2/17/2017.
 * Adapter to populate list of venues on the listview object
 * TODO: Use ViewHolder pattern or do it with Recycler view
 */
public class VenueListAdapter extends ArrayAdapter<Venue> {

    private static final String TAG = VenueListAdapter.class.getName();
    List<Venue> venueList;
    // row layout types
    private static final int REGULAR_ITEM_TYPE = 0;
    private static final int CATEGORY_ITEM_TYPE = 1;
    private Context context;


    public VenueListAdapter(Context context, int resource, List<Venue> objects) {
        super(context, resource, objects);
        venueList = objects;
        this.context = context;
    }


    @Override
    public int getCount() {
        return venueList.size();
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
        return (position != 0 && position % 10 == 0) ? CATEGORY_ITEM_TYPE : REGULAR_ITEM_TYPE;
    }


    // This method indicates how each item or row of listview should look
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        int layoutType = getItemViewType(position);
        switch (layoutType) {
            case REGULAR_ITEM_TYPE:
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(
                            R.layout.item_venue_list, parent, false);
                }

                if (venueList == null || venueList.isEmpty())
                    return convertView;
                Venue venue = getItem(position);

                TextView nameTextView = (TextView) convertView.findViewById(R.id.name_textview);
                TextView locationTextView = (TextView) convertView.findViewById(R.id.location_textview);
                TextView categoryTextView = (TextView) convertView.findViewById(R.id.category_textview);
                ImageView thumbnailImageView = (ImageView) convertView
                        .findViewById(R.id.thumbnail_imageview);

                nameTextView.setText(venue.getName().toString());
                locationTextView.setText(venue.getLocation().getAddress());
                categoryTextView.setText(Category.categoryListToString(venue.getCategories()));

                // get image url and load it
                // for venues, they don't come with any image (which is weird since I specifiy
                // in foursquare API that all venues have 1 image), so get(0) here
                // throws an exception. I'm handling it.
                String url = null;
                try {
                    url = venue.getPhotos().getGroups().get(0).getItems().get(0).getURLforThumbnail();
                }
                catch (Exception e){
                    Log.d(TAG, "Could not get picture URL for item " + position
                           + " , name: " + venue.getName().toString());
                }
                // if there is a url, load it with defualt, otherwise use Picassa to get it
                if(url == null) {
                    thumbnailImageView.setImageResource(R.drawable.unknown_image);
                }
                else {
                    Picasso.with(this.context)
                            .load(url)
                            .placeholder(R.drawable.unknown_image) // what to show if no img received
                            .error(R.drawable.error_img)           // what to show if error occurd
                            .into(thumbnailImageView);
                }
                return convertView;         // Return the completed view to render on screen

            case CATEGORY_ITEM_TYPE:
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.item_categories_list, parent, false);
                return convertView;
        }
        return convertView;
    }



    // TODO
    private static class ViewHolder{

    }

}
