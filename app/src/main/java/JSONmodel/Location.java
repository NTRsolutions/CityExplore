package JSONmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ASHL7 on 2/16/2017.
 */
public class Location {

    @SerializedName("address")
    private String address;

    @SerializedName("postalCode")
    private String postalCode;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
