package com.example.toni.busapp;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,AdapterView.OnItemSelectedListener,ActivityCompat.OnRequestPermissionsResultCallback{

    private GoogleMap mMap;
    private DatabaseReference ref;
    private Map<String, Bus> buses;
    private Map<String, Marker> markers;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        buses = new HashMap<>();
        markers = new HashMap<>();
        ref = FirebaseDatabase.getInstance().getReference("buses");
        ref.addValueEventListener(new ValueEventListener() {
            Bus b;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buses.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    b = s.getValue(Bus.class);
                    buses.put(b.getKey(), b);
                    if(b.getLineNumber()==0){
                        updateMap();
                        continue;
                    }

                    if (markers.containsKey(b.getKey()) == false) {
                        MarkerOptions mOpt = new MarkerOptions();
                        mOpt.position(new LatLng(b.getLocation().getLongitude(), b.getLocation().getLatitude()));
                        mOpt.title(Integer.toString(b.getLineNumber()) + " "
                                + getResources().getString(getResources().getIdentifier("bus"+Integer.toString(b.getLineNumber()),
                                "string","com.example.toni.busapp")));
                        mOpt.snippet("Speed: " + Integer.toString(b.getSpeed()) + " km/h");
                        mOpt.icon(BitmapDescriptorFactory.fromResource(
                                getResources().getIdentifier(
                                        "bus_icon","drawable","com.example.toni.busapp")));
                        Marker m = mMap.addMarker(mOpt);
                        markers.put(b.getKey(), m);
                    }

                }

                updateMap();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateMap() {
        LatLng latLng;
        Bus b;
        for (final HashMap.Entry<String, Bus> e : buses.entrySet()) {
            b=e.getValue();
            latLng = new LatLng(b.getLocation().getLongitude(), b.getLocation().getLatitude());
            final Marker m = markers.get(b.getKey());
            if(b.getLineNumber()==0){
                try {
                    m.remove();
                }catch(Exception ex){}
                buses.remove(b.getKey());
                markers.remove(b.getKey());
                continue;
            }
            m.setPosition(latLng);
            m.setSnippet("Speed: " + Integer.toString(b.getSpeed()) + " km/h");
            if (m.isInfoWindowShown()) {
                m.showInfoWindow();
            }



        }
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
        mMap.setMinZoomPreference(13);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            Log.println(Log.INFO,"location ",Boolean.toString(mMap.isMyLocationEnabled()));
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);


        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.51,16.46)));
        BitmapDescriptor bmp = BitmapDescriptorFactory.fromResource(getResources().getIdentifier(
                "bus_stop","drawable","com.example.toni.busapp"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(43.510866, 16.438423))
                                            .title("1, 5, 6, 11, 16, 18")
                                            .icon(bmp));
        mMap.addMarker(new MarkerOptions().position(new LatLng(43.507520, 16.442319))
                                            .title("2, 3, 5, 8, 9, 11, 15, 18")
                                            .icon(bmp));
        mMap.addMarker(new MarkerOptions().position(new LatLng(43.512235, 16.444045))
                                            .title("6, 15")
                                            .icon(bmp));

        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s=parent.getItemAtPosition(position).toString();
        if(!s.equals("Prikaži sve")) {
            s = s.substring(0, 2).trim();
            for (final HashMap.Entry<String, Bus> e : buses.entrySet()) {
                if (!s.equals(Integer.toString(e.getValue().getLineNumber()))) {
                    markers.get(e.getValue().getKey()).setVisible(false);
                }
                else{
                    markers.get(e.getValue().getKey()).setVisible(true);

                }
            }
        }
        else{
            for (final HashMap.Entry<String, Bus> e : buses.entrySet()) {
                markers.get(e.getValue().getKey()).setVisible(true);
            }

            }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult (int requestCode,
                                     String[] permissions,
                                     int[] grantResults){
        if(requestCode==1 && grantResults[0]==PERMISSION_GRANTED){
            LocationManager lm = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            if(!lm.isProviderEnabled(GPS_PROVIDER)){
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
            mMap.setMyLocationEnabled(true);
        }
    }
}
