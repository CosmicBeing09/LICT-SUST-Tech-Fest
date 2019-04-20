package com.example.raihan.sharefoods;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Volunteer_Response extends AppCompatActivity {

    RecyclerView feed;
    private Volunteer_Response_adapter mAdapter;
    public List<FoodRequestObject> requestArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer__response);

        feed = findViewById(R.id.volunteerResponse_recycler);

        mAdapter = new Volunteer_Response_adapter(requestArray, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Volunteer_Response.this);
        feed.setLayoutManager(mLayoutManager);

        final ProgressDialog Dialog = new ProgressDialog(Volunteer_Response.this);
        Dialog.setMessage("Please Wait.....");
        Dialog.show();

        Call<List<FoodRequestObject>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getFoodRequestObject();
        call.enqueue(new Callback<List<FoodRequestObject>>() {
            @Override
            public void onResponse(Call<List<FoodRequestObject>> call, Response<List<FoodRequestObject>> response) {
                for (FoodRequestObject requestObject : response.body()) {
                    if(requestObject.getDonator().equals(MainActivity.global_ID) && requestObject.getFoodStatus().trim().equals("PRO"))
                        requestArray.add(requestObject);
                     mAdapter.notifyDataSetChanged();
                     Dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<FoodRequestObject>> call, Throwable t) {

            }
        });

        feed.setItemAnimator(new DefaultItemAnimator());
        feed.setAdapter(mAdapter);
    }
}
