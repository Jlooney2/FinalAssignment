package com.example.jloon.finalsprint;

/**
 * Created by David on 4/30/2018.
 */

public class GeoLocation {
    private String summary;

    private int elevation;

    private int geoNameID;

    private String feature;

    private double lng;

    private String countryCode;

    private int rank;

    private String lang;

    private String title;

    private double lat;

    private String url;

    public GeoLocation(String title, String summary)
    {
        // , int elevation, String feature, double lat, double lng

        this.title = title;
        this.summary = summary;
        //this.elevation = elevation;
        //this.feature = feature;
        //this.lat = lat;
        //this.lng = lng;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getSummary()
    {
        return this.summary;
    }

}
