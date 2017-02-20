package JSONmodel.PhotosModel;

import com.google.gson.annotations.SerializedName;
/**
 * Created by ASHL7 on 2/20/2017.
 */
public class PhotosBody {

    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
