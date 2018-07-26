package com.example.toni.gpssimulator;

/**
 * Created by toni on 25.12.17..
 */

public class Location {
    private double longitude;
    private double latitude;

    public Location(){}
    public Location(double longitude,double latitude){
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }


}
