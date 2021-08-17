package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ListaVuelos extends AppCompatActivity {
    List<ListElement> elements;
    List<Aeropuerto> aeropuertos1 = new ArrayList<>();
    List<Aeropuerto> aeropuertos2 = new ArrayList<>();
    RequestQueue requestQueue;
    Pais pais1=new Pais();
    Pais pais2=new Pais();
    Pais pais3=new Pais();
    Pais pais4=new Pais();
    Aeropuerto aeropuerto1=new Aeropuerto();
    Aeropuerto aeropuerto2=new Aeropuerto();
    Switch switch_;
    private int dis=0, precio=0, a1, a2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vuelos);
        pais1 = (Pais) getIntent().getSerializableExtra("Pais1");
        pais2 = (Pais) getIntent().getSerializableExtra("Pais2");
        a1=pais2.getId()-1;
        a2=pais2.getId()+1;


        if(a1!=0 && a1!=pais1.getId()){
            consulta("http://192.168.0.43/dev/consultapaises2.php?id="+a1+"",pais3);
            consultaAeropuertos("http://192.168.0.43/dev/consultaaeropuertos2.php?idPais="+a1+"",aeropuerto1, pais3);
        }
        if(a2!=34 && a2!=pais1.getId()){
            consulta("http://192.168.0.43/dev/consultapaises2.php?id="+a2+"",pais4);
            consultaAeropuertos("http://192.168.0.43/dev/consultaaeropuertos2.php?idPais="+a2+"",aeropuerto2, pais4);
        }
        switch_ = (Switch) findViewById(R.id.switch1);
        aeropuertos1 = (List<Aeropuerto>) getIntent().getSerializableExtra("Aeropuertos1");
        aeropuertos2 = (List<Aeropuerto>) getIntent().getSerializableExtra("Aeropuertos2");
        init();
    }

    public void bot(View v){
        if(v.getId()==R.id.switch1){
            if(switch_.isChecked()){
                if(a1!=0 && a1!=pais1.getId()){
                    aeropuertos2.add(aeropuerto1);
                }
                if(a2!=34 && a2!=pais1.getId()){
                    aeropuertos2.add(aeropuerto2);
                }
                init();
            }else{
                if(a1!=0 && a1!=pais1.getId()){
                    aeropuertos2.remove(aeropuertos2.size()-1);
                }
                if(a2!=42 && a2!=pais1.getId()){
                    aeropuertos2.remove(aeropuertos2.size()-1);
                }
                init();
            }
        }
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
                        aeropuertos1.get(i).getLatHorizontal(),aeropuertos2.get(j).getLatHorizontal(), aeropuertos2.get(j).getP()));
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
    private void consultaAeropuertos(String URL, Aeropuerto a, Pais p){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        a.setId(jsonObject.getInt("id"));
                        a.setNombre(codificar(jsonObject.getString("nombre")));
                        a.setLatVertical(jsonObject.getDouble("latVertical"));
                        a.setLatHorizontal(jsonObject.getDouble("latHorizontal"));
                        a.setCodigo(jsonObject.getString("codigo"));
                        a.setCoeficiente(jsonObject.getInt("coeficiente"));
                        a.setIdPais(jsonObject.getInt("idPais"));
                        a.setP(p);

                        Toast.makeText(getApplicationContext(),a.getCodigo(),Toast.LENGTH_SHORT).show();
                        /*a = new Aeropuerto(jsonObject.getInt("id"),codificar(jsonObject.getString("nombre")),
                                jsonObject.getDouble("latVertical"), jsonObject.getDouble("latHorizontal"),
                                jsonObject.getString("pais"),jsonObject.getString("codigo"), jsonObject.getInt("coeficiente"),
                                jsonObject.getInt("idPais"),jsonObject.getInt("alerta"));

                         */

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private void consulta(String URL, Pais p){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        p.setId(jsonObject.getInt("id"));
                        p.setNombre(codificar(jsonObject.getString("nombre")));
                        p.setRestricciones(jsonObject.getString("restricciones"));
                        p.setLatVertical(jsonObject.getDouble("latVertical"));
                        p.setLatHorizontal(jsonObject.getDouble("latHorizontal"));
                        //p.setLatVertClinica(jsonObject.getDouble("latVertClinica"));
                        // p.setLatHorClinica(jsonObject.getDouble("latHorClinica"));
                        p.setAlerta(jsonObject.getInt("alerta"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private String codificar(String cadena){
        String str = "";
        try {
            str = new String(cadena.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        return Html.fromHtml(str).toString();
    }
}