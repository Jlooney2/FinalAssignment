package com.example.jloon.finalsprint;

/**
 * Created by David on 4/30/2018.
 */

public class GeoLocation {
    private String summary;
    private String title;
    public GeoLocation(String title, String summary)
    {
        this.title = title;
        this.summary = summary;
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
