package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Aeropuerto> aeropuertos1;
    List<Aeropuerto> aeropuertos2;
    private String input1="", input2="";
    AutoCompleteTextView c1,c2;
    Pais pais1 = new Pais();
    Pais pais2 = new Pais();
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] countries = getResources().getStringArray(R.array.countries);
        c1 = (AutoCompleteTextView)findViewById(R.id.pais);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        c1.setAdapter(adapter1);
        c2 = (AutoCompleteTextView)findViewById(R.id.city2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        c2.setAdapter(adapter2);


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
        }return super.onOptionsItemSelected(item);
    }
    public void confirma(View view){
        confirma1();
        confirma2();
        confirmaAeropuerto();

    }
    public void confirma1(){
        input1 = c1.getText().toString();
        if(!input1.isEmpty()){
            consulta("http://192.168.0.44/dev/consultabien.php?nombre="+input1+"",pais1);
            Toast.makeText(getApplicationContext(),"Pais de origen: "+ input1,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Introduce pais de origen",Toast.LENGTH_SHORT).show();
        }
    }
    public void confirma2(){
        input2 = c2.getText().toString();
        if(!input2.isEmpty()){
        consulta("http://192.168.0.44/dev/consultabien.php?nombre="+input2+"", pais2);
        Toast.makeText(getApplicationContext(),"Pais de destino: "+ input2,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Introduce pais de destino",Toast.LENGTH_SHORT).show();
        }
    }
    public void confirmaAeropuerto1(List<Aeropuerto> aeropuertos){
        input1 = c1.getText().toString();
        if(!input1.isEmpty()) {
            consultaAeropuertos("http://192.168.0.44/dev/consultaaeropuertos.php?pais=" + input1 + "", aeropuertos);
        }
    }
    public void confirmaAeropuerto2(List<Aeropuerto> aeropuertos){
        input2 = c2.getText().toString();
        if(!input2.isEmpty()){
            consultaAeropuertos("http://192.168.0.44/dev/consultaaeropuertos.php?pais="+input2+ "", aeropuertos);
        }
    }
    public void confirmaAeropuerto(){
        aeropuertos1=new ArrayList<>();
        aeropuertos2=new ArrayList<>();

        confirmaAeropuerto1(aeropuertos1);
        confirmaAeropuerto2(aeropuertos2);
    }
    //Metodo para descargar datos de la bdd y transferir los datos a la actividad de detalles
    public void botonSpecs(View view){
        if(!pais1.getNombre().isEmpty() && !pais2.getNombre().isEmpty() ){
            Intent next = new Intent(this, ListaVuelos.class);
            next.putExtra("Pais1", pais1);
            next.putExtra("Pais2", pais2);
            next.putExtra("Aeropuertos1", (Serializable) aeropuertos1);
            next.putExtra("Aeropuertos2", (Serializable) aeropuertos2);
            startActivity(next);
        }else if(pais1.getNombre().isEmpty()){
            Toast.makeText(getApplicationContext(),"Confirma pais de origen",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Confirma pais de destino",Toast.LENGTH_SHORT).show();
        }
    }
    //Metodo para hacer la consulta a la base de datos de mySQL
    private void consulta(String URL, Pais p){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        p.setId(jsonObject.getInt("id"));
                        p.setNombre(jsonObject.getString("nombre"));
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
    private void consultaAeropuertos(String URL, List<Aeropuerto> aeropuertos){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                       jsonObject = response.getJSONObject(i);
                        aeropuertos.add(new Aeropuerto(jsonObject.getInt("id"),jsonObject.getString("nombre"),
                                jsonObject.getDouble("latVertical"), jsonObject.getDouble("latHorizontal"),
                                jsonObject.getString("pais"),jsonObject.getString("codigo")));
                        //Toast.makeText(getApplicationContext(),aeropuertos.get(i).getCodigo(),Toast.LENGTH_SHORT).show();

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
}