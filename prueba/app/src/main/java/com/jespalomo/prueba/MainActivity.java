package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    int id,id1,id2;
    double latVertical, latHorizontal, latVertical1, latHorizontal1, latVertical2, latHorizontal2;
    private String input1="", input2="", restricciones, restricciones1, restricciones2, nombre;
    AutoCompleteTextView c1,c2;
    TextView prueba;
    Pais pais1 = new Pais();
    Pais pais2 = new Pais();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prueba = (TextView)findViewById(R.id.prueba);

        String[] countries = getResources().getStringArray(R.array.countries);
        c1 = (AutoCompleteTextView)findViewById(R.id.city1);
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
            Toast.makeText(getApplicationContext(),"Introduce pais de origen",Toast.LENGTH_SHORT).show();
        }
    }
    public void confirma2(View view){
        input2 = c2.getText().toString();
        if(!input1.isEmpty()){
        consulta("http://192.168.0.44/dev/consultabien.php?nombre="+input2+"", pais2);
        Toast.makeText(getApplicationContext(),"Pais de destino: "+ input2,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Introduce pais de destino",Toast.LENGTH_SHORT).show();
        }
    }
    //Metodo para descargar datos de la bdd y transferir los datos a la actividad de detalles
    public void botonSpecs(View view){
        Intent next = new Intent(this, Specs.class);
        next.putExtra("city1", pais1.getNombre());
        next.putExtra("id1", pais1.getId());
        next.putExtra("latVertical1", pais1.getLatVertical());
        next.putExtra("latHorizontal1", pais1.getLatHorizontal());
        next.putExtra("city2", pais2.getNombre());
        next.putExtra("id2", pais2.getId());
        next.putExtra("restricciones2", pais2.getRestricciones());
        next.putExtra("latVertical2", pais2.getLatVertical());
        next.putExtra("latHorizontal2", pais2.getLatHorizontal());
        startActivity(next);
    }
    public void botonUbi(View view){
        Intent next = new Intent(this, Ubicacion.class);
        startActivity(next);
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
                        //p.setLatHorClinica(jsonObject.getDouble("latHorClinica"));
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

}