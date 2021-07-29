package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Specs extends AppCompatActivity {
    private String city1, city2, restricciones1, restricciones2;
    private int id1, id2;
    private double latVertical1,latHorizontal1,latVertical2,latHorizontal2;
    TextView trip, restricciones_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specs);
        city1 = getIntent().getStringExtra("city1");
        id1 = getIntent().getIntExtra("id1",0);
        latVertical1 = getIntent().getDoubleExtra("latVertical1", 0.00);
        latHorizontal1 = getIntent().getDoubleExtra("latHorizontal1", 0.00);
        city2 = getIntent().getStringExtra("city2");
        id2 = getIntent().getIntExtra("id2",0);
        restricciones2 = getIntent().getStringExtra("restricciones2");
        latVertical2 = getIntent().getDoubleExtra("latVertical2", 0.00);
        latHorizontal2 = getIntent().getDoubleExtra("latHorizontal2", 0.00);

        trip = findViewById(R.id.trip);
        trip.setText("Trip from " + city1 + " to " + city2);
        restricciones_ = findViewById(R.id.restrictions_);
        restricciones_.setText(city2+": "+restricciones2);
        }
    public void botonMapa(View view){
        Intent next = new Intent(this, MapsActivity.class);
        next.putExtra("city1", city1);
        next.putExtra("latHorizontal1", latHorizontal1);
        next.putExtra("latVertical1", latVertical1);
        next.putExtra("city2", city2);
        next.putExtra("latHorizontal2", latHorizontal2);
        next.putExtra("latVertical2", latVertical2);
        startActivity(next);
    }
}