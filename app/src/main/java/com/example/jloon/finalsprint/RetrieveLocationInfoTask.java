package com.example.jloon.finalsprint;

import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RetrieveLocationInfoTask extends AsyncTask<URL, String, String> {

    private String summary;
    private TextView view;
    private String location;


    public RetrieveLocationInfoTask(TextView view, String location) {
        super();
        this.view = view;
        this.location = location;
    }


    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection request = null;
        summary = "Failed";
        try {
            request = (HttpURLConnection) urls[urls.length - 1].openConnection();
            request.connect();

            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
            JsonArray infoArray = rootobj.getAsJsonArray("geonames");

            this.summary = infoArray.get(0).getAsJsonObject().get("summary").getAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.summary;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.view.setText("Summary of: " + this.location + " is " + this.summary);
    }

    public String getSummary() {
        return this.summary;

    }

}
