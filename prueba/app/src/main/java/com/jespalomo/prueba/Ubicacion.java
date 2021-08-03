package com.jespalomo.prueba;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jespalomo.prueba.databinding.ActivityUbicacionBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ubicacion extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityUbicacionBinding binding;
    private int id, numClinicas;

    private double latVertical,latHorizontal;
    //private String nombre, nombre1;

    Clinica c = new Clinica();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocalizacion();
        id = getIntent().getIntExtra("idPais",0);
        numClinicas = getIntent().getIntExtra("numClinicas",0);
        latVertical = getIntent().getDoubleExtra("latVertical", 0.00);
        latHorizontal = getIntent().getDoubleExtra("latHorizontal", 0.00);

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
    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        LocationManager locationManager = (LocationManager) Ubicacion.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(miUbicacion).title("Ubicacion actual"));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(14)
                        .bearing(90)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        buscaClinica(id,c,googleMap);
        /*
        LatLng a = new LatLng(latVertical, latHorizontal);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(a)
                .zoom(5)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        */
    }

    private void consulta(String URL, Clinica c){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        c.setId(jsonObject.getInt("id"));
                        c.setLatVertical(jsonObject.getDouble("latVertical"));
                        c.setLatHorizontal(jsonObject.getDouble("latHorizontal"));
                        c.setNombre(jsonObject.getString("nombre"));
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
    private void buscaClinica(int id, Clinica c, GoogleMap googleMap){
        mMap = googleMap;
        LatLng cli;
        consulta("http://192.168.0.44/dev/consultaubi.php?idPais="+id+"", c);
        //Toast.makeText(getApplicationContext(), c.getNombre(), Toast.LENGTH_SHORT).show();
        cli= new LatLng(c.getLatVertical(), c.getLatHorizontal());
        mMap.addMarker(new MarkerOptions().position(cli).title(c.getNombre()));
    }
}