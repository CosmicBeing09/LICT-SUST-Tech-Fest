package com.example.raihan.sharefoods;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView feed;
    public static String userLocation;
    private Show_foodRequest_Adapter mAdapter;
    public List<FoodRequestObject> requestArray = new ArrayList<>();
    public static List<Profile_Object> fullProfile = new ArrayList<>();  //////////////////// Full database profile
    String username;

    public static Profile_Object myprofile;    //////////////// User Profile object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocation = getIntent().getStringExtra("username");
        username = userLocation.trim();
        getUserInfo();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,PostRequest.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        feed = findViewById(R.id.Show_Food_Request_Recycler);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mAdapter = new Show_foodRequest_Adapter(requestArray, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        feed.setLayoutManager(mLayoutManager);

        final ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
        Dialog.setMessage("Please Wait.....");
        Dialog.show();

        Call<List<FoodRequestObject>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getFoodRequestObject();
        call.enqueue(new Callback<List<FoodRequestObject>>() {
            @Override
            public void onResponse(Call<List<FoodRequestObject>> call, Response<List<FoodRequestObject>> response) {
                for (FoodRequestObject requestObject : response.body()) {
                    requestArray.add(requestObject);
                    mAdapter.notifyDataSetChanged();
                    Dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<FoodRequestObject>> call, Throwable t) {

            }
        });
        requestArray.add(new FoodRequestObject("aaa", 1, "ssdd", 2, 5, "afaf", "sssff", "safsa"));
        feed.setItemAnimator(new DefaultItemAnimator());
        feed.setAdapter(mAdapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myreq) {
            // Handle the camera action

        } else if (id == R.id.nav_dashboard) {

            Intent intent = new Intent(MainActivity.this, BarChart_Activity.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

        }

         else if (id == R.id.nav_response) {

        } else if (id == R.id.nav_leaderboard) {

        }
        else if(id==R.id.nav_history){

        }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        void getUserInfo()
        {
            Call<List<Profile_Object>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getProfileinfo();
            call.enqueue(new Callback<List<Profile_Object>>() {
                @Override
                public void onResponse(Call<List<Profile_Object>> call, Response<List<Profile_Object>> response) {
                    for (Profile_Object profile_object : response.body()) {
                        fullProfile.add(profile_object);
                        ///Change needed must
                        String s = profile_object.getUser().getUsername().trim();
                        if (s.equals(username)) {
                            myprofile = profile_object;
                            Log.e("aaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssssssssssssss");
                        }
                    }

                }

                @Override
                public void onFailure(Call<List<Profile_Object>> call, Throwable t) {

                }
            });
        }
    }
