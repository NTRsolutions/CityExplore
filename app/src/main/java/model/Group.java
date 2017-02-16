package model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by ASHL7 on 2/16/2017.
 */
public class Group {

    @SerializedName("items")
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
