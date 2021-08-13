package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ListaVuelos extends AppCompatActivity {

    List<ListElement> elements;
    List<Aeropuerto> aeropuertos1;
    List<Aeropuerto> aeropuertos2;
    Pais pais1;
    Pais pais2;
    Aeropuerto aeropuerto1;
    Aeropuerto aeropuerto2;
    private int dis=0, precio=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vuelos);
        pais1 = (Pais) getIntent().getSerializableExtra("Pais1");
        pais2 = (Pais) getIntent().getSerializableExtra("Pais2");
        //aeropuerto1 = (Aeropuerto) getIntent().getSerializableExtra("Aeropuerto1");
        //aeropuerto2 = (Aeropuerto) getIntent().getSerializableExtra("Aeropuerto2");
        aeropuertos1 = (List<Aeropuerto>) getIntent().getSerializableExtra("Aeropuertos1");
        aeropuertos2 = (List<Aeropuerto>) getIntent().getSerializableExtra("Aeropuertos2");
        init();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();

        if(id == R.id.home){
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
        }return super.onOptionsItemSelected(item);
    }
    public void init(){
        elements = new ArrayList<>();
        for(int i=0; i<aeropuertos1.size(); i++){
            for(int j=0; j<aeropuertos2.size(); j++){
                dis = haversine(aeropuertos1.get(i).getLatHorizontal(), aeropuertos1.get(i).getLatVertical(),
                        aeropuertos2.get(j).getLatHorizontal(), aeropuertos2.get(j).getLatVertical());
                if(dis>1000){
                    precio= (int) Math.round(dis/(aeropuertos1.get(i).getCoeficiente()*aeropuertos2.get(j).getCoeficiente()));
                }else{
                    precio= (int) Math.round(dis/(aeropuertos1.get(i).getCoeficiente()*aeropuertos2.get(j).getCoeficiente())+50);
                }
                elements.add(new ListElement(aeropuertos1.get(i).getCodigo()+" - "+aeropuertos2.get(j).getCodigo(),
                        Integer.toString(dis)+" KM", precio+ " €",aeropuertos1.get(i).getNombre(),aeropuertos2.get(j).getNombre(),
                        aeropuertos1.get(i).getLatVertical(), aeropuertos2.get(j).getLatVertical(),
                        aeropuertos1.get(i).getLatHorizontal(),aeropuertos2.get(j).getLatHorizontal()));
            }
        }
        //dis = haversine(aeropuerto1.getLatHorizontal(), aeropuerto1.getLatVertical(),
          //      aeropuerto2.getLatHorizontal(), aeropuerto2.getLatVertical());
       // precio=Math.round(dis/3);
       // elements.add(new ListElement(aeropuerto1.getCodigo()+" - "+aeropuerto2.getCodigo(), Integer.toString(dis)+" KM",
                //recio+ " €", aeropuerto1.getNombre(),aeropuerto2.getNombre(),
                //aeropuerto1.getLatVertical(), aeropuerto2.getLatVertical(),
                //aeropuerto1.getLatHorizontal(),aeropuerto2.getLatHorizontal()));
        //elements.add(new ListElement("MAD-VIE", "DIRECTO - 2h 30min", "102€"));
        //elements.add(new ListElement("BCN-LJU", "DIRECTO - 1h 55min", "113€"));

        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.lista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void moveToDescription(ListElement item){
        Intent next = new Intent(this, Specs.class);
        next.putExtra("Pais1", pais1);
        next.putExtra("Pais2", pais2);
        next.putExtra("Vuelo", item);
        startActivity(next);
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
}