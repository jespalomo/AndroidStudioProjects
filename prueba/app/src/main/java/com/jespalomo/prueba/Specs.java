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
    TextView trip, restricciones_, distancia, borders, borders_;
    Pais pais1;
    Pais pais2;
    ListElement vuelo;
    private String riesgo[]= {"Pais con riesgo bajo",
            "Pais con riesgo intermedio",
            "Pais con alto riesgo"};
    private String mascarillas[]= {"Mascarilla no obligatoria al aire libre",
            "Mascarilla no obligatoria al aire libre",
            "Mascarilla obligatoria al aire libre"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specs);
        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");
        pais1 = (Pais) getIntent().getSerializableExtra("Pais1");
        pais2 = (Pais) getIntent().getSerializableExtra("Pais2");
        vuelo = (ListElement) getIntent().getSerializableExtra("Vuelo");

        trip = findViewById(R.id.pregunta);
        borders = findViewById(R.id.borders);
        borders_ = findViewById(R.id.borders_);
        distancia= findViewById(R.id.distancia_);
        restricciones_ = findViewById(R.id.restrictions_);
        trip.setText("Viaje desde " + pais1.getNombre() + " hasta " + vuelo.getP().getNombre());
        distancia.setText(vuelo.getDuracion());
        borders.setText("Situación actual en "+ vuelo.getP().getNombre());
        borders_.setText(riesgo[vuelo.getP().getAlerta()]+"\n"+mascarillas[vuelo.getP().getAlerta()]);
        restricciones_.setText("Frontera de "+vuelo.getP().getNombre()+":\n "+vuelo.getP().getRestricciones());
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent next = new Intent(this, Seleccion.class);
            startActivity(next);
            return true;
        }else if(id == R.id.clinicas){
            Intent next = new Intent(this, CompruebaUbicacion.class);
            startActivity(next);
            return true;
        }else if(id == R.id.planearviaje){
            Intent next = new Intent(this, MainActivity.class);
            startActivity(next);
            return true;
        }else if(id == R.id.about){
            Intent next = new Intent(this, SobreMi.class);
            startActivity(next);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void botonMapa(View view){
        Intent next = new Intent(this, MapsActivity.class);
        next.putExtra("Pais1", pais1);
        next.putExtra("Vuelo", vuelo);
        startActivity(next);
    }
}