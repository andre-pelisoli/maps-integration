package br.com.pelisoli.mapsintegration.requests;

import br.com.pelisoli.mapsintegration.models.SearchResult;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by pelisoli on 19/11/15.
 */
public interface IRequest {

    @GET("/maps/api/geocode/json")
    SearchResult getAdresses(@Query("address") String address, @Query("sensor") boolean sensor);
}
