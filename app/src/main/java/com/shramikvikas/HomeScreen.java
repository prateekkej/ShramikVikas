package com.shramikvikas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shramikvikas.Adapters.fpAdapter;
import com.shramikvikas.Fragments.Profile_Fragment;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeScreen extends AppCompatActivity {
    OkHttpClient client;
    Request req;
    Response response;
    private String url, res;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private ImageView imageView;
    private TextView name;
    private TextView email;
    private TextView address;
    private TextView phone;
    private TextView gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        client = new OkHttpClient();
        url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";
        req = new Request.Builder().url(url).build();
        loadContent();
        final ViewPager vp = (ViewPager) findViewById(R.id.pager);
        fpAdapter adapter = new fpAdapter(getSupportFragmentManager(), HomeScreen.this);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(3);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vp);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_circle_black_48dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_black_48dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_recent_actors_black_48dp);

        name = (TextView) findViewById(R.id.profile_name);
        email = (TextView) findViewById(R.id.profile_email);
        address = (TextView) findViewById(R.id.address_profile);
        phone = (TextView) findViewById(R.id.phone_profile);
        gender = (TextView) findViewById(R.id.gender_profile);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //setDataToView(user);

        /*authListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("SetTextI18n")
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d("HomeScreen", "onAuthStateChanged");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setDataToView(user);

                    //loading image by Picasso
                    if (user.getPhotoUrl() != null) {
                        Log.d("HomeScreen", "photoURL: " + user.getPhotoUrl());
                        Picasso.with(HomeScreen.this).load(user.getPhotoUrl()).into(imageView);
                    }
                } else {
                    //user auth state is not existed or closed, return to Login activity
                    startActivity(new Intent(HomeScreen.this, LoginActivity.class));
                    finish();
                }
            }
        };*/
    }


    protected void loadContent() {
        new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPostExecute(Void aVoid) {
                try {
                   // auth.addAuthStateListener(authListener);
                    Log.v("Json:", res);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    response = client.newCall(req).execute();
                    res = response.body().string();
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }.execute();
    }


   /* @SuppressLint("SetTextI18n")
    private void setDataToView(FirebaseUser user) {
        Toast.makeText(HomeScreen.this, "Error", Toast.LENGTH_LONG).show();
        email.setText("User Email: " + user.getEmail());
        name.setText("User name: " + user.getDisplayName());
       // phone.setText(user.get());
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }*/
}
