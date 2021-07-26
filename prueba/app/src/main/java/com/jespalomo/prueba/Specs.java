package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Specs extends AppCompatActivity {
    private String city1, city2, restricciones1, restricciones2;
    private int id1, id2;
    TextView trip, restricciones_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specs);
        city1 = getIntent().getStringExtra("city1");
        //id1 = getIntent().getIntExtra("id1");
        restricciones1 = getIntent().getStringExtra("restricciones1");
        city2 = getIntent().getStringExtra("city2");
        //id2 = getIntent().getIntExtra("id2");
        restricciones2 = getIntent().getStringExtra("restricciones2");

        trip = findViewById(R.id.trip);
        trip.setText("Trip from " + city1 + " to " + city2);
        restricciones_ = findViewById(R.id.restrictions_);
        restricciones_.setText(restricciones1+restricciones2);
        }
}