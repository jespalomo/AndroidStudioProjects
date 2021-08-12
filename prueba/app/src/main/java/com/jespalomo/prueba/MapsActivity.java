package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.jespalomo.prueba.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback{
    private static final int colores []= {0xff3be155,0xfff5a905,0xfff50505};
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Pais pais1=new Pais();
    Pais pais2=new Pais();
    ListElement vuelo;
    private static final int COLOR_BLACK_ARGB = 0xff000333;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pais1 = (Pais) getIntent().getSerializableExtra("Pais1");
        pais2 = (Pais) getIntent().getSerializableExtra("Pais2");
        vuelo = (ListElement) getIntent().getSerializableExtra("Vuelo");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
        }
        return super.onOptionsItemSelected(item);
    }

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final LatLng aeropuerto1 = new LatLng(vuelo.getLatVertical1(), vuelo.getLatHorizontal1());
        final LatLng aeropuerto2 = new LatLng(vuelo.getLatVertical2(), vuelo.getLatHorizontal2());

        mMap.addMarker(new MarkerOptions().position(aeropuerto1).title(vuelo.getNombre1()));

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(aeropuerto2)
                .title(vuelo.getNombre2());


        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        aeropuerto1,
                        aeropuerto2)
                .color(colores[pais2.getAlerta()])
                );
        polyline1.setTag("Ruta mas corta");

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(aeropuerto1)
                .zoom(7)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        CustomInfoWindowAdapter customInfoWindow = new CustomInfoWindowAdapter(this);
        mMap.setInfoWindowAdapter(customInfoWindow);


        Marker m2 = mMap.addMarker(markerOptions2);
        m2.setTag(pais2);
        m2.showInfoWindow();
    }



    /**
     * Styles the polyline, based on type.
     * @param polyline The polyline object that needs styling.
     */

}