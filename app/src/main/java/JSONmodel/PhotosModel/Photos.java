package JSONmodel.PhotosModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ASHL7 on 2/19/2017.
 */
public class Photos {

    @SerializedName("count")
    private Integer count;

    @SerializedName("items")
    private List<PhotosPhotoItem> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PhotosPhotoItem> getItems() {
        return items;
    }

    public void setItems(List<PhotosPhotoItem> items) {
        this.items = items;
    }
}
