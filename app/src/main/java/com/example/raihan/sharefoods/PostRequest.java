package com.example.raihan.sharefoods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.raihan.sharefoods.AppClient.Base_URL;

public class PostRequest extends AppCompatActivity {

    EditText EfoodId,EdonatorID,Elocation,Equantity,EexpireTime,Edescription,EpickupTime,EfoodStatus;
     Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);

        Elocation = findViewById(R.id.foodDonatorLocation);
        Equantity = findViewById(R.id.foodDonatorQuantity);
        EexpireTime = findViewById(R.id.foodDonatorExpiary);
        Edescription = findViewById(R.id.foodDonatorDescription);
        EpickupTime = findViewById(R.id.foodDonatorPickupTime);

        submit = findViewById(R.id.foodDonatorSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               FoodRequestObject foodRequestObject = new FoodRequestObject(6,Elocation.getText().toString().trim(),
                       Integer.valueOf(Equantity.getText().toString().trim()),Integer.valueOf(EexpireTime.getText().toString().trim()),
                       Edescription.getText().toString().trim(),EpickupTime.getText().toString().trim()+":00:00","REQ");


                Intent intent = new Intent(PostRequest.this,Locate_Volunteer_Map.class);
                intent.putExtra("location",Elocation.getText().toString().trim());
                intent.putExtra("object",foodRequestObject);
                startActivity(intent);

            }
        });


    }
}
