package com.example.raihan.sharefoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.raihan.sharefoods.AppClient.Base_URL;

public class Registration extends AppCompatActivity {

    EditText username,email,password,confirmPassword;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.user_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password1);
        confirmPassword = findViewById(R.id.password2);

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://sustta.herokuapp.com/api/v1/rest-auth/").addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();
                IApi_Vinfo iApi_vinfo = retrofit.create(IApi_Vinfo.class);
                Call<RegisterObject> call = iApi_vinfo.createRegistration(new RegisterObject(username.getText().toString().trim(),
                        email.getText().toString().trim(),password.getText().toString().trim(),confirmPassword.getText().toString().trim()));
//
                call.enqueue(new Callback<RegisterObject>() {
                    @Override
                    public void onResponse(Call<RegisterObject> call, Response<RegisterObject> response) {
                        Toast.makeText(Registration.this,response.body().toString(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<RegisterObject> call, Throwable t) {

                    }
                });


            }
        });
    }
}
