package com.shramikvikas;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.shramikvikas.Adapters.fpAdapter;

public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
       final ViewPager vp=(ViewPager)findViewById(R.id.pager);
       fpAdapter adapter= new fpAdapter(getSupportFragmentManager(),HomeScreen.this);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(3);
        TabLayout tabLayout= (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vp);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_circle_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_recent_actors_white_24dp);



    }
}
