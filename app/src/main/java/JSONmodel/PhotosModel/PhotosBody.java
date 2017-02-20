package JSONmodel.PhotosModel;

import com.google.gson.annotations.SerializedName;
/**
 * Created by ASHL7 on 2/20/2017.
 */
public class PhotosBody {

    @SerializedName("response")
    private PhotosResponse response;

    public PhotosResponse getResponse() {
        return response;
    }

    public void setResponse(PhotosResponse response) {
        this.response = response;
    }
}
