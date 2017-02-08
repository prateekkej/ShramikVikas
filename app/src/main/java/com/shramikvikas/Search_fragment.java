package com.shramikvikas;

import android.animation.LayoutTransition;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Search_fragment extends Fragment {
private RecyclerView recyclerView;
    private ArrayList<Labor_obj> labor_list;
    private Labor_Adapter adapter;
    private RecyclerView.LayoutManager llm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= LayoutInflater.from(getContext()).inflate(R.layout.labors_fragment,container,false);
        recyclerView= (RecyclerView)v.findViewById(R.id.recycle_view);
        labor_list = new ArrayList<Labor_obj>();
        recyclerView.setBackgroundResource(R.drawable.tab);
        Labor_obj ob= new Labor_obj("Rajat","+919644330695");ob.addSkill("Hardworking");ob.addSkill("Smart");ob.addSkill("Nice");ob.setRating(3.5);        labor_list.add(ob);
        Labor_obj ob2= new Labor_obj("Prateek","+919650072504"); ob2.addSkill("Hardworking");ob2.addSkill("Smart");ob2.addSkill("Nice");ob2.setRating(5);
        labor_list.add(ob2);
        Labor_obj ob3= new Labor_obj("Nikhil","+91971054107");ob3.addSkill("Hardworking");ob3.addSkill("Smart");ob3.addSkill("Nice");ob3.setRating(5);
        labor_list.add(ob3);
        Labor_obj ob4= new Labor_obj("Sid","+91971054107");ob4.addSkill("Hardworking");ob4.addSkill("Smart");ob4.addSkill("Nice");ob4.setRating(5);
        labor_list.add(ob4);
        Labor_obj ob5= new Labor_obj("Muskan","+91971054107") ;ob5.addSkill("Hardworking");ob5.addSkill("Smart");ob5.addSkill("Nice");ob5.setRating(4);
        labor_list.add(ob5);
        Labor_obj ob6= new Labor_obj("Akhil", "+91971054107");ob6.setRating(4.5);ob6.addSkill("Hardworking");ob6.addSkill("Smart");ob6.addSkill("Nice");
        labor_list.add(ob6);
        adapter = new Labor_Adapter(labor_list,recyclerView);
        llm= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        return v;
    }
    }

