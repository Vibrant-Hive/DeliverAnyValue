package com.deliver.any.value.activity.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.deliver.any.value.R;
import com.deliver.any.value.activity.ServicesActivity;
import com.deliver.any.value.databinding.FragmentAreaMapBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class AreaMapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentAreaMapBinding binding;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Marker marker;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAreaMapBinding.inflate(inflater, container, false);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity().getApplicationContext());
        fetchLocation();
        return binding.getRoot();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        locateMe(googleMap);
        locateLaundry(googleMap);

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity().getApplicationContext(), R.raw.style_json));
        googleMap.setOnMarkerClickListener(marker -> {
            if(marker.equals(this.marker)){
                startActivity(new Intent(requireActivity().getApplicationContext(), ServicesActivity.class));
            }
            return true;
        });
    }

    private void locateLaundry(GoogleMap googleMap) {
        LatLng vipLatLng = new LatLng(currentLocation.getLatitude() - 0.003, currentLocation.getLongitude() - 0.003);
        MarkerOptions vipMarker = new MarkerOptions().position(vipLatLng).title("VIP Laundry").icon(bitmapFromVector(requireActivity().getApplicationContext(), R.drawable.outline_local_laundry_service_black_48));
        marker = googleMap.addMarker(vipMarker);
    }

    private void locateMe(GoogleMap googleMap) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions myMarker = new MarkerOptions().position(latLng).title("You").icon(bitmapFromVector(requireActivity().getApplicationContext(), R.drawable.outline_person_pin_circle_black_48));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        Marker myLoc = googleMap.addMarker(myMarker);
        Objects.requireNonNull(myLoc).showInfoWindow();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation();
            }
        }
    }

    private BitmapDescriptor bitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        Objects.requireNonNull(vectorDrawable).setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;
                SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                Objects.requireNonNull(supportMapFragment).getMapAsync(this);
            }
        });
    }
}