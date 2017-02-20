package JSONmodel;


import com.google.gson.annotations.SerializedName;

/**
 * Created by ASHL7 on 2/19/2017.
 */

public class PicturesResponse {

    @SerializedName("photos")
    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}