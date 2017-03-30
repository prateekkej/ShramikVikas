package com.shramikvikas;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.shramikvikas.Adapters.fpAdapter;

import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeScreen extends AppCompatActivity {
    OkHttpClient client;
    Request req;
    Response response;
    private String url,res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        client= new OkHttpClient();
      url= "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";
        req= new Request.Builder().url(url).build();
        loadContent();
       final ViewPager vp=(ViewPager)findViewById(R.id.pager);
       fpAdapter adapter= new fpAdapter(getSupportFragmentManager(),HomeScreen.this);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(3);
        TabLayout tabLayout= (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vp);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_circle_black_48dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_black_48dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_recent_actors_black_48dp);
    }
    protected void loadContent()
    {
        new AsyncTask<Void,Void,Void>(){


            @Override
            protected void onPostExecute(Void aVoid) {
                try{
                Log.v("Json:" ,res);
            }catch (Exception e)
                {e.printStackTrace();}}

            @Override
            protected Void doInBackground(Void... voids) {
                try{
response= client.newCall(req).execute();
                    res=response.body().string();
                    return null;
                }catch(Exception e ){
                    e.printStackTrace();
                    return null;
                }
            }
        }.execute();
    }
}
