package foursquareREST;

import JSONmodel.CityResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ASHL7 on 2/16/2017.
 * Defining the end points using Retrofit annotations
 */
public interface FoursquareInterface {

    // Get venues
    @GET("venues/explore")
    Call<CityResponse> getVenues(@Query("client_id") String client_id,
                                 @Query("client_secret") String client_secret,
                                 @Query("near") String city,
                                 @Query("limit") String limit,
                                 @Query("venuePhotos") String count,
                                 @Query("v") String date);

    // Get venues given the category
    @GET("venues/explore")
    Call<CityResponse> getVenuesWithCategory(@Query("client_id") String clinet_id,
                                             @Query("client_secret") String client_secret,
                                             @Query("near") String city,
                                             @Query("section") String category,
                                             @Query("limit") String limit,
                                             @Query("venuePhotos") String count,
                                             @Query("v") String date);
}
