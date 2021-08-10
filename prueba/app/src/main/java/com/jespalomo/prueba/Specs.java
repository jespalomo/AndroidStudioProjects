package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Specs extends AppCompatActivity {
    private int dis;
    TextView trip, restricciones_, distancia;
    Pais pais1;
    Pais pais2;
    ListElement vuelo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specs);
        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");
        pais1 = (Pais) getIntent().getSerializableExtra("Pais1");
        pais2 = (Pais) getIntent().getSerializableExtra("Pais2");
        vuelo = (ListElement) getIntent().getSerializableExtra("Vuelo");

        trip = findViewById(R.id.pregunta);
        distancia= findViewById(R.id.distancia_);
        restricciones_ = findViewById(R.id.restrictions_);
        trip.setText("Viaje desde " + vuelo.getRuta() + " hasta " + pais2.getNombre());
        distancia.setText(vuelo.getDuracion());
        restricciones_.setText("Frontera de "+pais2.getNombre()+":\n "+pais2.getRestricciones());
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
        next.putExtra("Pais1", pais1);
        next.putExtra("Pais2", pais2);
        next.putExtra("Vuelo", vuelo);
        startActivity(next);
    }
}