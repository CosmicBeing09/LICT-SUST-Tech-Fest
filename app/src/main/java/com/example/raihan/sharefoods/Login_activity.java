package com.example.raihan.sharefoods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_activity extends AppCompatActivity {
EditText userName,phoneNo,password;
String name,phone;
public static String user_ID;
public static Profile_Object user_object;
int flag = 0;
Integer i = 0;
Button button,register;
ArrayList<Profile_Object> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        userName = findViewById(R.id.login_username);
        phoneNo = findViewById(R.id.login_phone);


        button =(Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = userName.getText().toString().trim();
                phone = phoneNo.getText().toString().trim();



                Call<List<Profile_Object>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getProfileinfo();
                call.enqueue(new Callback<List<Profile_Object>>() {
                    @Override
                    public void onResponse(Call<List<Profile_Object>> call, Response<List<Profile_Object>> response) {
                        for(Profile_Object profile_object: response.body())
                        {
                            arrayList.add(profile_object);
                            if(name.equals(profile_object.getUser().getUsername().trim()) && phone.equals(profile_object.getPhoneNumber().trim()))
                            {
                              flag = 1;
                              user_ID = String.valueOf(arrayList.size());
                              user_object = profile_object;

                              i++;
                              break;

                            }

                        }
                        if(flag == 1)
                        {
                            Intent intent = new Intent(Login_activity.this,MainActivity.class);
                            Toast.makeText(Login_activity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            intent.putExtra("username",name);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Login_activity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Profile_Object>> call, Throwable t) {

                    }
                });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_activity.this,Registration.class);
                startActivity(intent);
            }
        });

    }
}
