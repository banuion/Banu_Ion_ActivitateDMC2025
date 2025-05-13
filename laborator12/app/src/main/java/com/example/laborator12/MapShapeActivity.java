package com.example.laborator12;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.location.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MapShapeActivity extends FragmentActivity
        implements OnMapReadyCallback {

    private GoogleMap map;
    private String shapeType;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_shape);
        shapeType = getIntent().getStringExtra("shapeType");

        SupportMapFragment frag = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_shape_fragment);
        frag.getMapAsync(this);
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Request location permission if needed
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        FusedLocationProviderClient locClient =
                LocationServices.getFusedLocationProviderClient(this);
        locClient.getLastLocation().addOnSuccessListener(loc -> {
            LatLng center;
            if (loc != null) {
                center = new LatLng(loc.getLatitude(), loc.getLongitude());
            } else {
                 //Bucuresti
                center = new LatLng(44.4268, 26.1025);
            }
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12f));
            if ("Polygon".equals(shapeType)) drawPolygon(center);
            else drawCircle(center);
        });
    }

    private void drawPolygon(LatLng c) {
        double d = 0.01;
        PolygonOptions opts = new PolygonOptions()
                .add(
                        new LatLng(c.latitude + d, c.longitude - d),
                        new LatLng(c.latitude + d, c.longitude + d),
                        new LatLng(c.latitude - d, c.longitude + d),
                        new LatLng(c.latitude - d, c.longitude - d)
                )
                .fillColor(0x44FF0000)
                .strokeColor(Color.RED)
                .strokeWidth(4f);
        map.addPolygon(opts);
    }

    private void drawCircle(LatLng c) {
        CircleOptions opts = new CircleOptions()
                .center(c)
                .radius(500)        // meters
                .fillColor(0x440000FF)
                .strokeColor(Color.BLUE)
                .strokeWidth(4f);
        map.addCircle(opts);
    }

    @Override
    public void onRequestPermissionsResult(int req, String[] perms, int[] res) {
        super.onRequestPermissionsResult(req, perms, res);
        if (req == 1 && res.length>0
                && res[0] == PackageManager.PERMISSION_GRANTED) {
            // retry now that permission is granted
            onMapReady(map);
        }
    }
}
