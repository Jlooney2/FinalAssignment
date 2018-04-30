package com.example.jloon.finalsprint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String USERNAME = "finalassignment";
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<GeoLocation> data;
    private static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<GeoLocation>();
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);

        Button getInfoButton = findViewById(R.id.btnGetInfo);
        getInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                adapter.notifyDataSetChanged();

                EditText userInput = findViewById(R.id.keyword_input);
                String location = userInput.getText().toString();
                URL url = this.buildSearchQuery(location);

                RetrieveLocationInfoTask task = new RetrieveLocationInfoTask(data, adapter);
                task.execute(url);
            }

            private URL buildSearchQuery(String location) {
                URL query = null;
                try {
                    query = new URL("http://api.geonames.org/wikipediaSearchJSON?q=" + URLEncoder.encode(location, "UTF-8") + "&maxRows=10&username=" + USERNAME);
                } catch (MalformedURLException e ) {
                    e.printStackTrace();
                }catch (UnsupportedEncodingException uee){
                }
                return query;
            }
        });
    }
}
