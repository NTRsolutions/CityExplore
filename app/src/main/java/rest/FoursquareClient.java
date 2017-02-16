package rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASHL7 on 2/16/2017.
 */
public class FoursquareClient {

    public static final String CLIENT_ID = "1T44UBTLMOEI01RHYUZIOPHBIYMZTWCHVPCML1IPETOM5Y1E";
    public static final String CLIENT_SECRET = "5LVUUOLMPN04VSBE54EN0OUZOEDAFMXAAAKXN1KN52MWUAOC";
    public static final String BASE_URL = "https://api.foursquare.com/v2/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
