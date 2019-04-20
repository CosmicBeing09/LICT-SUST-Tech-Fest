package com.example.raihan.sharefoods;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.raihan.sharefoods.Objects.Profile_Object;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.raihan.sharefoods.AppClient.Base_URL;

public class Locate_Volunteer_Map extends FragmentActivity implements OnMapReadyCallback {

    ImageButton search;
    EditText searchEditText;
    private GoogleMap mMap;
    Address hostAddress;
    LatLng t_latLng;
    List<Address> distances = new ArrayList<>();
    public String userLocation;
    public FoodRequestObject foodRequestObject;
    ArrayList<String> nearbyUser = new ArrayList<>();
    Button createPost;
    Switch aSwitch;
    Fragment fragment;

    DatabaseReference def,push,del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate__volunteer__map);

        search = findViewById(R.id.search_imageButton);
        searchEditText =findViewById(R.id.search_radius);
        createPost = findViewById(R.id.createPost);
        aSwitch= findViewById(R.id.viewSwitch);

        del = FirebaseDatabase.getInstance().getReference();
        push = FirebaseDatabase.getInstance().getReference();
        def = FirebaseDatabase.getInstance().getReference();

        del = del.child("push");
        del.removeValue();

        Intent intent = getIntent();
        userLocation = intent.getStringExtra("location");
        foodRequestObject = intent.getParcelableExtra("object");
        final ArrayList<Profile_Object> profile_objects = new ArrayList<>();



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
                markerOptions.title(userLocation);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions);


                circleOptions.center(t_latLng);
                circleOptions.radius(Integer.valueOf(radius) * 1000);
                circleOptions.strokeColor(Color.CYAN);
                circleOptions.fillColor(0x4D000080);
                mMap.addCircle(circleOptions);

                Call<List<Profile_Object>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getProfileinfo();
                call.enqueue(new Callback<List<Profile_Object>>() {
                    @Override
                    public void onResponse(Call<List<Profile_Object>> call, Response<List<Profile_Object>> response) {
                        Geocoder geocoder = new Geocoder(Locate_Volunteer_Map.this);
                        List<Address> addressList = null;
                        MarkerOptions markerOptions = new MarkerOptions();
                        String volunteerLocation;
                        for(Profile_Object profile_object: response.body())
                        {

                            volunteerLocation = profile_object.getAddress().toString().trim();
//                            Toast.makeText(Locate_Volunteer_Map.this,volunteerLocation,Toast.LENGTH_SHORT).show();

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

                                            nearbyUser.add(profile_object.getUser().getUsername().toString().trim());
                                            profile_objects.add(profile_object);

                                            markerOptions.position(latLng);
                                            markerOptions.title(volunteerLocation);
                                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                                            mMap.addMarker(markerOptions);



                                        }

                                    }
                                    notification();
                                }

                            } catch (Exception e) {
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Profile_Object>> call, Throwable t) {

                    }
                });


            }
        });

        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();
                IApi_Vinfo iApi_vinfo = retrofit.create(IApi_Vinfo.class);
                Call<FoodRequestObject> call = iApi_vinfo.createFoodRequest(foodRequestObject);

                call.enqueue(new Callback<FoodRequestObject>() {
                    @Override
                    public void onResponse(Call<FoodRequestObject> call, Response<FoodRequestObject> response) {
                        Toast.makeText(Locate_Volunteer_Map.this,"Request Created",Toast.LENGTH_LONG).show();
                        DatabaseReference def = FirebaseDatabase.getInstance().getReference();
                        def.child("notification").push().setValue(new notification("New Food Distribution Request",userLocation));
                        Intent i = new Intent(Locate_Volunteer_Map.this,MainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<FoodRequestObject> call, Throwable t) {

                        Toast.makeText(Locate_Volunteer_Map.this,"Failed",Toast.LENGTH_LONG).show();

                    }
                });



            }
        });


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(aSwitch.isChecked())
                {
                    fragment = new Volunteer_preview_fragment();
                    if(fragment!=null)
                    {
                        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.map,fragment).addToBackStack("Tag");

                        aSwitch.setText("Info");
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("arrayList",profile_objects);
                        fragment.setArguments(bundle);
                        ft.commit();
                    }

                }
                else {
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                    aSwitch.setText("Map");
                    ft.remove(fragment).commit();




                }
            }
        });

    }

    void notification()
    {
        //nayeem

        push.child("fcm-token").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                dataSnapshot.getKey();
                Token d = dataSnapshot.getValue(Token.class);
                for(int i = 0;i<nearbyUser.size();i++){
                    if(nearbyUser.get(i).equals(dataSnapshot.getKey().trim())){

                        def.child("push").child(d.getToken()).child("token").setValue(d.getToken());
                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
            addressList = geocoder.getFromLocationName(userLocation,1);

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
