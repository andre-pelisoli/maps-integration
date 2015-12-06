package br.com.pelisoli.mapsintegration.models;


import java.io.Serializable;

public class Address implements Serializable{

    private String formatted_address;

    private Geometry geometry;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
