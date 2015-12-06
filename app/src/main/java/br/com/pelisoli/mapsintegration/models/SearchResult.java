package br.com.pelisoli.mapsintegration.models;

import java.io.Serializable;
import java.util.ArrayList;


public class SearchResult implements Serializable{

    private ArrayList<Address> results;

    public ArrayList<Address> getAddressList() {
        return results;
    }

    public void setAddressList(ArrayList<Address> results) {
        this.results = results;
    }
}
