package com.example.raihan.sharefoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                Call<FoodRequestObject> call = AppClient.getApiClient().create(IApi_Vinfo.class).createFoodRequest(
                        new FoodRequestObject("1",1,"SUST",10,23,"Chicken","14:0:0","REQ"));
                call.enqueue(new Callback<FoodRequestObject>() {
                    @Override
                    public void onResponse(Call<FoodRequestObject> call, Response<FoodRequestObject> response) {

                    }

                    @Override
                    public void onFailure(Call<FoodRequestObject> call, Throwable t) {

                    }
                });
            }
        });


    }
}
