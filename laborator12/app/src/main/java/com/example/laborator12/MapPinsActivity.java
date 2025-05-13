package com.example.laborator12;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import java.util.ArrayList;
import java.util.List;

public class MapPinsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap map;
    private final List<LatLng> points = new ArrayList<>();
    private Polyline polyline;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_pins);
        SupportMapFragment frag = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_pins_fragment);
        frag.getMapAsync(this);
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
       //iasi de la ion
        LatLng iasi = new LatLng(47.1585, 27.6014);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(iasi, 12f));
        map.setOnMapClickListener(this);
    }

    @Override public void onMapClick(LatLng latLng) {
        map.addMarker(new MarkerOptions().position(latLng));
        points.add(latLng);

        if (polyline != null) polyline.remove();
        polyline = map.addPolyline(new PolylineOptions()
                .addAll(points)
                .color(Color.BLUE)
                .width(5f));
    }
}
