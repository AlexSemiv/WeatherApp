package com.example.simpleweatherapp.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.simpleweatherapp.MainActivity;
import com.example.simpleweatherapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MyMapFragment extends SupportMapFragment implements
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        OnSuccessListener<Location>,
        GoogleMap.OnMapClickListener {
    private static final int ACCESS_FINE_LOCATION_CODE = 1011;
    private static final int ACCESS_COARSE_LOCATION_CODE = 1012;
    private GoogleMap mMap;
    private double mLatitude=0.0, mLongitude=0.0;
    private boolean permission_granted = false;

    public interface onMyMapListener{
        void getCurrentLatLng(double latitude,double longitude);
    }

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);

        mLatitude = getArguments().getDouble(MainActivity.START_LATITUDE);
        mLongitude=getArguments().getDouble(MainActivity.START_LONGITUDE);

        View map = layoutInflater.inflate(R.layout.fragment_map, viewGroup, false);

        SupportMapFragment mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(this);

        return map;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mLatitude,mLongitude)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(mLatitude,mLongitude)).title("Moscow!")); // change later
        mMap.setOnMapClickListener(this);

        checkPermission();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(!permission_granted) {
            checkPermission();
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public boolean onMyLocationButtonClick() {
        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this);

        return false;
    }

    @Override
    public void onSuccess(Location location) {
        double latitude,longitude;
        if(location!=null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            LatLng latLng = new LatLng(latitude,longitude);
            mMap.clear();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.addMarker(new MarkerOptions().position(latLng).title("I'm here!"));

            onMyMapListener listener =(onMyMapListener) getActivity();
            listener.getCurrentLatLng(latitude,longitude);
        }
    }

    private void checkPermission(){
        if(!permission_granted) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Coarse Permission")
                            .setMessage("You can't use the map without this permission. Could you grant it please?")
                            .setPositiveButton("OK", (dialog, which) -> requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CODE))
                            .setNegativeButton("Cancel", (dialog, which) -> {
                            })
                            .show();
                } else {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CODE);
                }
            } else {
                permission_granted = true;
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_FINE_LOCATION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permission_granted = true;
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
        }
    }
}
