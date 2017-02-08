package com.shramikvikas;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ViewPager vp=(ViewPager)findViewById(R.id.pager);
       fpAdapter adapter= new fpAdapter(getSupportFragmentManager(),HomeScreen.this);
        vp.setAdapter(adapter);
        TabLayout tabLayout= (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vp);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_circle_black_48dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_black_48dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_recent_actors_black_48dp);
        tabLayout.setBackgroundResource(R.drawable.tab);




    }
}
