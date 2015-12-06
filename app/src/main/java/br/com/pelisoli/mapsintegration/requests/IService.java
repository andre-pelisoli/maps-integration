package br.com.pelisoli.mapsintegration.requests;

import java.util.ArrayList;

import br.com.pelisoli.mapsintegration.models.Address;

/**
 * Created by pelisoli on 25/11/15.
 */
public interface IService {

    public ArrayList<Address> getAddresses(String address);

}
