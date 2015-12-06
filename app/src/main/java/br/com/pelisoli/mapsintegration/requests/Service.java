package br.com.pelisoli.mapsintegration.requests;

import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import br.com.pelisoli.mapsintegration.models.Address;
import br.com.pelisoli.mapsintegration.models.SearchResult;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by pelisoli on 19/11/15.
 */
public class Service implements IService {
    public static final String BASE_URL = "http://maps.googleapis.com";

    public IRequest searchRequest;

    public Service() {
        RestAdapter builder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(new OkHttpClient())).build();

        searchRequest = builder.create(IRequest.class);
    }


    @Override
    public ArrayList<Address> getAddresses(String address){
        ArrayList<Address> addressList = null;

        SearchResult result = searchRequest.getAdresses(address, false);

        if(result != null){
            addressList = result.getAddressList();
        }

        return addressList;
    }

}
