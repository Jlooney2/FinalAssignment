package com.example.jloon.finalsprint;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveLocationInfoTask extends AsyncTask<URL, String, String> {

    private String summary;
    private  int count = 0;
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

            summary = String.valueOf(rootobj.size());
            // summary = rootobj.get("lat").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return summary;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public String getSummary() {
        if (this.summary != null) {
            return this.summary;
        } else {
            count++;
            return "Population null" + count;
        }

    }

}
