package model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by ASHL7 on 2/16/2017.
 */
public class Item {

    @SerializedName("venue")
    private Venue venue;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
