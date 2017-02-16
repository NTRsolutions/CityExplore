package rest;

import model.CityResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ASHL7 on 2/16/2017.
 */
public interface FoursquareInterface {

    @GET("venues/explore")
    Call<CityResponse> getVenues(@Query("near") String city,
                                 @Query("limit") String limit,
                                 @Query("client_id") String clinet_id,
                                 @Query("client_secret") String client_secret,
                                 @Query("v") String date);

    @GET("movie/{id}")
    Call<CityResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
