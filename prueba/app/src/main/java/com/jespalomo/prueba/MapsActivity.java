package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.jespalomo.prueba.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private String city1, city2;
    private double latVertical1, latHorizontal1, latVertical2, latHorizontal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        city1 = getIntent().getStringExtra("city1");
        latVertical1 = getIntent().getDoubleExtra("latVertical1", 0.00);
        latHorizontal1 = getIntent().getDoubleExtra("latHorizontal1", 0.00);
        city2 = getIntent().getStringExtra("city2");
        latVertical2 = getIntent().getDoubleExtra("latVertical2", 0.00);
        latHorizontal2 = getIntent().getDoubleExtra("latHorizontal2", 0.00);
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
        LatLng city1_ = new LatLng(latVertical1, latHorizontal1);
        LatLng city2_ = new LatLng(latVertical2, latHorizontal2);
        mMap.addMarker(new MarkerOptions().position(city1_).title("Marker in " + city1));
        mMap.addMarker(new MarkerOptions().position(city2_).title("Marker in " + city2));
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        city1_,
                        city2_));

        polyline1.setTag("Ruta mas corta");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(city1_));
    }
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;

    /**
     * Styles the polyline, based on type.
     * @param polyline The polyline object that needs styling.
     */
    /*
    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }
        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.ic_arrow), 10));
                break;
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }
        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }
    */
}