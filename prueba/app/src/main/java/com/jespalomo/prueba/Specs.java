package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Specs extends AppCompatActivity {
    private String city1, city2, restricciones1, restricciones2;
    private int id1, id2, dis;
    private double latVertical1,latHorizontal1,latVertical2,latHorizontal2;
    TextView trip, restricciones_, distancia;
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
        dis = haversine(latHorizontal1,latVertical1,latHorizontal2,latVertical2);

        trip = findViewById(R.id.trip);
        distancia= findViewById(R.id.distancia_);
        restricciones_ = findViewById(R.id.restrictions_);
        trip.setText("Viaje desde " + city1 + " hasta " + city2);
        distancia.setText(Integer.toString(dis)+ " KM");
        restricciones_.setText("Frontera de "+city2+":\n "+restricciones2);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent next = new Intent(this, MainActivity.class);
            startActivity(next);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private static int haversine(double lon1, double lat1, double lon2, double lat2) {

        double radioTierra = 6371;

        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double dlon = (lon2 - lon1);
        double dlat = (lat2 - lat1);

        double sinlat = Math.sin(dlat / 2);
        double sinlon = Math.sin(dlon / 2);

        double a = (sinlat * sinlat) + Math.cos(lat1)*Math.cos(lat2)*(sinlon*sinlon);
        double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

        double distancia = radioTierra * c;

        return (int)distancia;

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