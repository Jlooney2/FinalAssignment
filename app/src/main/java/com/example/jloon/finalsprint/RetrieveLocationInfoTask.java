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
    //private TextView view;
    private ArrayList<GeoLocation> data;
    private RecyclerView.Adapter adapter;
    //private String location;


    public RetrieveLocationInfoTask(ArrayList<GeoLocation> inData, RecyclerView.Adapter inAdapter) {
        super();
        //this.view = view;
        this.data = inData;
        this.adapter = inAdapter;
        //this.location = location;
        
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

            try{
                for  (JsonElement current : infoArray){

                    String stringOne = current.getAsJsonObject().get("title").getAsString();
                    String stringTwo = current.getAsJsonObject().get("summary").getAsString();
                    this.data.add(new GeoLocation(stringOne, stringTwo));

                }
            }catch (RuntimeException re){
                //this.data.clear();
                this.data.add(new GeoLocation("Sorry", "Invalid Input for location. Please input the name of city or state."));
                //adapter.notifyDataSetChanged();
                //his.summary = "Invalid Input for location. Please input the name of city or state.";
            }
            //for  (JsonElement current : infoArray){

            //    try{
            //        String stringOne = current.getAsJsonObject().get("title").getAsString();
            //        String stringTwo = current.getAsJsonObject().get("summary").getAsString();
            //        this.data.add(new GeoLocation(stringOne, stringTwo));
            //    }catch (RuntimeException re){
                    //this.data.clear();
            //        this.data.add(new GeoLocation("Sorry", "Invalid Input for location. Please input the name of city or state."));
                    //adapter.notifyDataSetChanged();
                    //his.summary = "Invalid Input for location. Please input the name of city or state.";
            //    }

            //}

            //try{
                //this.summary = infoArray.get(0).getAsJsonObject().get("summary").getAsString();
            //}catch (RuntimeException re){
              //  this.summary = "Invalid Input for location. Please input the name of city or state.";
            //}


        } catch (IOException e) {
            e.printStackTrace();

            //this.data.clear();
            this.data.add(new GeoLocation("Sorry", "Invalid Input for location. Please input the name of city or state."));
            //adapter.notifyDataSetChanged();
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
        //this.view.setText(this.summary);
        adapter.notifyDataSetChanged();
    }

    public String getSummary() {
        return this.summary;

    }

}
