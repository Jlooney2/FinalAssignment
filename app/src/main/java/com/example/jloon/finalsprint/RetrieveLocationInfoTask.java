package com.example.jloon.finalsprint;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
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
    private ArrayList<GeoLocation> data;
    private RecyclerView.Adapter adapter;


    public RetrieveLocationInfoTask(ArrayList<GeoLocation> inData, RecyclerView.Adapter inAdapter) {
        super();
        this.data = inData;
        this.adapter = inAdapter;

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


            for  (JsonElement current : infoArray){

                String stringOne = current.getAsJsonObject().get("title").getAsString();
                String stringTwo = current.getAsJsonObject().get("summary").getAsString();
                this.data.add(new GeoLocation(stringOne, stringTwo));

            }

        } catch (IOException e) {
            e.printStackTrace();
            this.data.add(new GeoLocation("Sorry", "Invalid Input for location. Please input the name of city or state."));
        }

        if(this.data.size() == 0){
            this.data.add(new GeoLocation("Sorry", "Invalid Input for location. Please input the name of city or state."));
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
        adapter.notifyDataSetChanged();
    }


}
