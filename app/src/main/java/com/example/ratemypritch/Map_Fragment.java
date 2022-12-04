package com.example.ratemypritch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Map_Fragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_,container,false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.clear();
                //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(49.94159,-119.39562)));

                LatLng pritchard = new LatLng(49.94159,-119.39562);
                googleMap.addMarker(new MarkerOptions()
                        .position(pritchard)
                        .title(("Pritchard")));

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(pritchard));


            }
        });
        return view;
    }
}