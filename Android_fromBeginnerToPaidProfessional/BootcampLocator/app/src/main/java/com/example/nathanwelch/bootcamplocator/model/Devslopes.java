package com.example.nathanwelch.bootcamplocator.model;

/**
 * Created by Nathan Welch on 6/19/2017.
 * its a bootcamp location
 */

public class Devslopes {
    final String DRAWABLE = "drawable/";
    private float longitude;
    private float latitude;
    private String locationTitle;
    private String locationAddress;
    private String locationImgUrl;

    public Devslopes(float latitude, float longitude, String locationTitle, String locationAddress, String locationImgUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationTitle = locationTitle;
        this.locationAddress = locationAddress;
        this.locationImgUrl = locationImgUrl;
    }

    public String getImgURL() {
        return DRAWABLE + locationImgUrl;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationImgUrl() {
        return locationImgUrl;
    }
}
