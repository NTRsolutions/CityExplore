package JSONmodel.ExploreModel;


import com.google.gson.annotations.SerializedName;

/**
 * Created by ASHL7 on 2/15/2017.
 * Class representing a call to Foursquare API
 */
public class ExploreResponse {

    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
