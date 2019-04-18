package com.example.raihan.sharefoods;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Locate_Volunteer_Map extends FragmentActivity implements OnMapReadyCallback {

    ImageButton search;
    EditText searchEditText;
    private GoogleMap mMap;
    Address hostAddress;
    LatLng t_latLng;
    List<Address> distances = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate__volunteer__map);

        search = findViewById(R.id.search_imageButton);
        searchEditText =findViewById(R.id.search_radius);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String radius = searchEditText.getText().toString().trim();
                CircleOptions circleOptions = new CircleOptions();
                MarkerOptions markerOptions = new MarkerOptions();
                mMap.clear();

                markerOptions.position(t_latLng);
                markerOptions.title("IICT SUST");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions);


                circleOptions.center(t_latLng);
                circleOptions.radius(Integer.valueOf(radius) * 1000);
                circleOptions.strokeColor(Color.CYAN);
                circleOptions.fillColor(0x4D000080);
                mMap.addCircle(circleOptions);

                Call<List<FoodRequestObject>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getFoodRequestObject();
                call.enqueue(new Callback<List<FoodRequestObject>>() {
                    @Override
                    public void onResponse(Call<List<FoodRequestObject>> call, Response<List<FoodRequestObject>> response) {
                        Geocoder geocoder = new Geocoder(Locate_Volunteer_Map.this);
                        List<Address> addressList = null;
                        MarkerOptions markerOptions = new MarkerOptions();
                        String volunteerLocation;
                        for(FoodRequestObject foodRequestObject: response.body())
                        {

                            volunteerLocation = foodRequestObject.getLocation().toString().trim();
                            Toast.makeText(Locate_Volunteer_Map.this,volunteerLocation,Toast.LENGTH_SHORT).show();

                            try {
                                addressList = geocoder.getFromLocationName(volunteerLocation, 1);
                                if (addressList != null) {
                                    for (int i = 0; i < addressList.size(); i++) {
                                        Address userAddress = addressList.get(i);

                                        LatLng latLng = new LatLng(userAddress.getLatitude(), userAddress.getLongitude());

                                        distances.add(addressList.get(0));
                                        float results[] = new float[100];
                                        Location.distanceBetween(userAddress.getLatitude(), userAddress.getLongitude(), hostAddress.getLatitude(), hostAddress.getLongitude(), results);

                                        if (results[0] / 1000 <= Float.valueOf(radius)) {

                                            markerOptions.position(latLng);
                                            markerOptions.title(volunteerLocation);
                                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                                            mMap.addMarker(markerOptions);



                                        }

                                    }

                                }

                            } catch (Exception e) {
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<FoodRequestObject>> call, Throwable t) {

                    }
                });


            }
        });


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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Geocoder geocoder = new Geocoder(Locate_Volunteer_Map.this);
        List<Address> addressList = null;
        MarkerOptions markerOptions = new MarkerOptions();


        try{
            addressList = geocoder.getFromLocationName("IICT SUST",1);

            if(addressList!=null)
            {
                for (int i=0;i<addressList.size();i++)
                {
                    hostAddress = addressList.get(i);

                    t_latLng = new LatLng(hostAddress.getLatitude(),hostAddress.getLongitude());

                    markerOptions.position(t_latLng);
                    markerOptions.title("IICT SUST");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));




                    mMap.addMarker(markerOptions);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(t_latLng,10));



                }

                addressList.add(addressList.get(0));

            }

        }catch (Exception e){}
    }
}
