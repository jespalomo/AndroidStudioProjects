package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CompruebaUbicacion extends AppCompatActivity {
    AutoCompleteTextView c1;
    private String input1="";
    List<Clinica> clinicas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprueba_ubicacion);
        String[] countries = getResources().getStringArray(R.array.countries);
        c1 = (AutoCompleteTextView)findViewById(R.id.pais);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        c1.setAdapter(adapter1);
        c1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                input1 = c1.getText().toString();
                confirma1();
            }
        });
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
    public void confirma1(){
        if(!input1.isEmpty()){
            consulta("http://192.168.0.44/dev/consultaubi.php?pais="+input1+"");
            Toast.makeText(getApplicationContext(),"Pais de origen: "+ input1,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Introduce un pais",Toast.LENGTH_SHORT).show();
        }
    }
    private void consulta(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        clinicas.add(new Clinica(jsonObject.getInt("id"), codificar(jsonObject.getString("pais")),
                                jsonObject.getDouble("latVertical"),jsonObject.getDouble("latHorizontal"),
                                jsonObject.getString("nombre"),codificar(jsonObject.getString("telefono")),
                                codificar(jsonObject.getString("direccion"))));
                        Toast.makeText(getApplicationContext(), clinicas.get(i).getNombre(), Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    public void botonUbi(View view){
        if(!input1.isEmpty()){
            Intent next = new Intent(this, Ubicacion.class);
            next.putExtra("Clinicas", (Serializable) clinicas);
            startActivity(next);
        }else {
            Toast.makeText(getApplicationContext(),"Introduce un pais",Toast.LENGTH_SHORT).show();
        }
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