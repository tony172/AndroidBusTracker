package com.example.toni.gpssimulator;

/**
 * Created by toni on 25.12.17..
 */

public class Bus {

    private String key;
    private Location location;
    private int lineNumber;
    private int speed;

    public Bus() {
    }

    public Bus(String key,Location geoLocation,int lineNumber, int speed){
        this.key=key;
        this.location=geoLocation;
        this.lineNumber=lineNumber;
        this.speed=speed;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
