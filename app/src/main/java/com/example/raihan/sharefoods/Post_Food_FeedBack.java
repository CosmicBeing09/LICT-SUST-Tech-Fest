package com.example.raihan.sharefoods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raihan.sharefoods.Objects.RecordObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.raihan.sharefoods.AppClient.Base_URL;

public class Post_Food_FeedBack extends AppCompatActivity {

    EditText person,location;
    Button button;
    FoodRequestObject foodRequestObject;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__food__request);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        time = sf.format(new Date());

        Intent intent = getIntent();
        foodRequestObject = intent.getParcelableExtra("object");
        foodRequestObject.setFoodStatus("REC");
        person = findViewById(R.id.foodVolunQuantity);
        location = findViewById(R.id.foodVolunLocation);
        button = findViewById(R.id.feedback);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();
                IApi_Vinfo iApi_vinfo = retrofit.create(IApi_Vinfo.class);
                Call<FoodRequestObject> call = iApi_vinfo.createFoodRequest(foodRequestObject);

                call.enqueue(new Callback<FoodRequestObject>() {
                    @Override
                    public void onResponse(Call<FoodRequestObject> call, Response<FoodRequestObject> response) {
                        Toast.makeText(Post_Food_FeedBack.this,response.body().toString(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<FoodRequestObject> call, Throwable t) {

                        Toast.makeText(Post_Food_FeedBack.this,"Failed",Toast.LENGTH_LONG).show();

                    }
                });

              RecordObject recordObject =   new RecordObject(MainActivity.global_ID,location.getText().toString(),
                        Integer.valueOf(person.getText().toString()),foodRequestObject.getDonator(),"2019-04-19T11:27:08.459247Z");

                Call<List<RecordObject>> call1 = iApi_vinfo.postRecordObject(recordObject);

                call1.enqueue(new Callback<List<RecordObject>>() {
                    @Override
                    public void onResponse(Call<List<RecordObject>> call, Response<List<RecordObject>> response) {
                        //Toast.makeText(Post_Food_FeedBack.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<RecordObject>> call, Throwable t) {

                    }
                });

            }
        });

    }
}
