package com.ashl7developer.cityexplore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import JSONmodel.PhotosModel.PhotoItem;

/**
 * Created by ASHL7 on 2/20/2017.
 */
public class PhotoGridAdapter extends ArrayAdapter<PhotoItem> {

    private List<PhotoItem> photos;
    private Context context;


    public PhotoGridAdapter(Context context, int resource, List<PhotoItem> objects) {
        super(context, resource, objects);
        this.photos = objects;
        this.context = context;
    }


    @Override
    public int getCount() {
        return photos.size();
    }

    // This method indicates how each item or row of listview should look
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_grid_layout, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.venue_imageview);
        PhotoItem photoItem = photos.get(position);
        String url = photoItem.getURLforGrid();
        Picasso.with(this.context)
                .load(url)
                .placeholder(R.drawable.unknown_image) // what to show if no img received
                .error(R.drawable.error_img)           // what to show if error occurd
                .into(imageView);
        return convertView;
    }
}
