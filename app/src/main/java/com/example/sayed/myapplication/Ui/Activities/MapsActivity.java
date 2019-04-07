package com.example.sayed.myapplication.Ui.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sayed.myapplication.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.map_selectlocation)
    Button mapSelectlocation;
    @BindView(R.id.map_floating)
    FloatingActionButton mapFloating;
    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private FusedLocationProviderClient mLocationClient;
    private float ZOOM_CAMERA = 5f;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private boolean MLocationPemissionsGranted = false;
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        getLocationPermission();

        // initMap();

        mapFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDeviceLocation();
            }
        });

        mapSelectlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                intent.putExtra("lat", latitude);
                intent.putExtra("long", longitude);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
    }

    private void getDeviceLocation() {

        Log.d(TAG, "getDeviceLocation : getting current location");
        mLocationClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (MLocationPemissionsGranted) {
                Task taskLocation = mLocationClient.getLastLocation();
                taskLocation.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location ");
                            Toast.makeText(MapsActivity.this, "found location", Toast.LENGTH_LONG).show();

                            Location result = (Location) task.getResult();
                            latitude = result.getLatitude();
                            longitude = result.getLongitude();
                            LatLng latLng = new LatLng(latitude, longitude);


                            Toast.makeText(MapsActivity.this, "" + result.getLongitude() + "" + result.getLatitude(), Toast.LENGTH_LONG).show();

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));

                            mMap.addMarker(new MarkerOptions().position(latLng)).showInfoWindow();


                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapsActivity.this, "current location is null", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        } catch (SecurityException e) {
            Log.d(TAG, "getDeviceLocation: SecurityException" + e.getMessage());

        }

    }




    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        Toast.makeText(this, "map is ready", Toast.LENGTH_SHORT).show();

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                MLocationPemissionsGranted = true;
                initMap();


            } else {
                ActivityCompat.requestPermissions(this,
                        permissions, REQUEST_CODE_PERMISSIONS);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions, REQUEST_CODE_PERMISSIONS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        MLocationPemissionsGranted = false;
        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                            MLocationPemissionsGranted = false;
                            return;

                        }
                    }
                    MLocationPemissionsGranted = true;
                    //init the map
                    initMap();
                }
            }
        }
    }
}
