package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class CompruebaUbicacion extends AppCompatActivity {
    AutoCompleteTextView c1;
    private String input1="";
    private double latVertical, latHorizontal;
    Pais pais1 = new Pais();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprueba_ubicacion);
        String[] countries = getResources().getStringArray(R.array.countries);
        c1 = (AutoCompleteTextView)findViewById(R.id.pais);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        c1.setAdapter(adapter1);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();

        if(id == R.id.home){
            Intent next = new Intent(this, MainActivity.class);
            startActivity(next);
            return true;
        }return super.onOptionsItemSelected(item);
    }
    public void confirma1(View view){
        input1 = c1.getText().toString();
        if(!input1.isEmpty()){
            consulta("http://192.168.0.44/dev/consultabien.php?nombre="+input1+"",pais1);
            Toast.makeText(getApplicationContext(),"Pais de origen: "+ input1,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Introduce un pais",Toast.LENGTH_SHORT).show();
        }
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
                        p.setNumClinicas(jsonObject.getInt("numClinicas"));
                        p.setLatVertical(jsonObject.getDouble("latVertical"));
                        p.setLatHorizontal(jsonObject.getDouble("latHorizontal"));
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
        Intent next = new Intent(this, Ubicacion.class);
        next.putExtra("idPais", pais1.getId());
        next.putExtra("numClinicas", pais1.getNumClinicas());
        next.putExtra("latVertical", pais1.getLatVertical());
        next.putExtra("latHorizontal", pais1.getLatHorizontal());
        startActivity(next);
    }
}