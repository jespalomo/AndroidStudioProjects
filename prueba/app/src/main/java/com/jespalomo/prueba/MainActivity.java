package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

<<<<<<< Updated upstream
    }
    //Metodo para descargar datos de la bdd y transferir los datos a la actividad del mapa
    public void botonMapa(View view){
        Intent next = new Intent(this, MapsActivity.class);
        next.putExtra("city1", input1);
        next.putExtra("latHorizontal1", latHorizontal1);
        next.putExtra("latVertical1", latVertical1);
        next.putExtra("city2", input2);
        next.putExtra("latHorizontal2", latHorizontal2);
        next.putExtra("latVertical2", latVertical2);
        startActivity(next);
=======
>>>>>>> Stashed changes
    }
    //Metodo para descargar datos de la bdd y transferir los datos a la actividad del mapa
    public void confirma1(View view){
        input1 = c1.getText().toString();
        consulta("http://192.168.0.44/dev/consultabien.php?nombre="+input1+"");
<<<<<<< Updated upstream
        restricciones1=restricciones;
=======
>>>>>>> Stashed changes
        latVertical1=latVertical;
        latHorizontal1=latHorizontal;
        prueba.setText(input1);
    }
    public void confirma2(View view){
        input2 = c2.getText().toString();
        consulta("http://192.168.0.44/dev/consultabien.php?nombre="+input2+"");
        restricciones2=restricciones;
        latVertical2=latVertical;
        latHorizontal2=latHorizontal;
        prueba.setText(input2);
    }
    //Metodo para descargar datos de la bdd y transferir los datos a la actividad de detalles
    public void botonSpecs(View view){
<<<<<<< Updated upstream

            Intent next = new Intent(this, Specs.class);
            next.putExtra("city1", input1);
            //next.putExtra("id1", id1);
            next.putExtra("restricciones1", restricciones1);
            next.putExtra("city2", input2);
            //next.putExtra("id2", id2);
            next.putExtra("restricciones2", restricciones2);
            startActivity(next);
=======
        Intent next = new Intent(this, Specs.class);
        next.putExtra("city1", input1);
        next.putExtra("id1", id1);
        next.putExtra("latVertical1", latVertical1);
        next.putExtra("latHorizontal1", latHorizontal1);
        next.putExtra("city2", input2);
        next.putExtra("id2", id2);
        next.putExtra("restricciones2", restricciones2);
        next.putExtra("latVertical2", latVertical2);
        next.putExtra("latHorizontal2", latHorizontal2);
        startActivity(next);
>>>>>>> Stashed changes
    }
    public void botonUbi(View view){
        Intent next = new Intent(this, Ubicacion.class);
        startActivity(next);
    }
    //Metodo para hacer la consulta a la base de datos de mySQL
    private void consulta(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        id = jsonObject.getInt("id");
                        nombre= jsonObject.getString("nombre");
                        restricciones = jsonObject.getString("restricciones");
                        latVertical = jsonObject.getDouble("latVertical");
                        latHorizontal = jsonObject.getDouble("latHorizontal");
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