package com.example.raihan.sharefoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.raihan.sharefoods.Objects.Profile_Object;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    TextView name,address,phone_no,username,email,voluteer;
    List<Profile_Object> profile_array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.profile_name);
        address = findViewById(R.id.profile_address);
        phone_no = findViewById(R.id.profile_phoneNo);
        username = findViewById(R.id.profile_userName);
        email = findViewById(R.id.profile_email);
        voluteer = findViewById(R.id.profile_volunteer);


        name.setText(MainActivity.myprofile.getUser().getFirstName());
        username.setText(profile_array.get(1).getUser().getUsername());
        phone_no.setText(profile_array.get(1).getPhoneNumber());
        email.setText(profile_array.get(1).getUser().getEmail());
        address.setText(profile_array.get(1).getAddress());
        voluteer.setText(profile_array.get(1).getVolunter());
    }
}
