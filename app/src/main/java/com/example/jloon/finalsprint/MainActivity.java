package com.example.jloon.finalsprint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String USERNAME = "finalassignment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getInfoButton = findViewById(R.id.btnGetInfo);
        getInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInput = findViewById(R.id.keyword_input);
                TextView locationFacts = findViewById(R.id.location_info);



                String location = userInput.getText().toString();

                URL url = this.buildSearchQuery(location);

                RetrieveLocationInfoTask task = new RetrieveLocationInfoTask();
                task.execute(url);

                String summary = task.getSummary();
                locationFacts.setText("Summary of: " + location + " is " + summary);
            }

            private URL buildSearchQuery(String location) {
                URL query = null;
                try {
                    query = new URL("http://api.geonames.org/wikipediaSearchJSON?q=" + location + "&maxRows=10&username=" + USERNAME);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                return query;
            }
        });


    }


}
