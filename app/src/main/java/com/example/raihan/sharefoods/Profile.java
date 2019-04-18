package com.example.raihan.sharefoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.data.BarEntry;

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

        Call<List<Profile_Object>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getProfileinfo();
        call.enqueue(new Callback<List<Profile_Object>>() {
            @Override
            public void onResponse(Call<List<Profile_Object>> call, Response<List<Profile_Object>> response) {
                for(Profile_Object profile_object: response.body())
                {
                    profile_array.add(profile_object);

                }
                name.setText(profile_array.get(1).getUser().getFirstName());
                username.setText(profile_array.get(1).getUser().getUsername());
                phone_no.setText(profile_array.get(1).getPhoneNumber());
                email.setText(profile_array.get(1).getUser().getEmail());
                address.setText(profile_array.get(1).getAddress());
                voluteer.setText(profile_array.get(1).getVolunter());
            }

            @Override
            public void onFailure(Call<List<Profile_Object>> call, Throwable t) {

            }
        });


    }
}
