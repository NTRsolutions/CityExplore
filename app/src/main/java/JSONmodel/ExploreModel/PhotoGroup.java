package JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ASHL7 on 2/18/2017.
 */
public class PhotoGroup {

    @SerializedName("items")
    private List<PhotoItem> items = null;

    public List<PhotoItem> getItems() {
        return items;
    }

    public void setItems(List<PhotoItem> items) {
        this.items = items;
    }


}
