package com.shramikvikas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Labor_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labors_fragment);
        RecyclerView recyclerView= (RecyclerView)findViewById(R.id.recycle_view);
        ArrayList<Labor_obj> labor_list= new ArrayList<Labor_obj>();
        Labor_obj ob= new Labor_obj("Rajat","+919644330695");ob.addSkill("Hardworking");ob.addSkill("Smart");ob.addSkill("Nice");
        labor_list.add(ob);
        Labor_obj ob2= new Labor_obj("Prateek","+919650072504"); ob2.addSkill("Hardworking");ob2.addSkill("Smart");ob2.addSkill("Nice");
        labor_list.add(ob2);
        Labor_obj ob3= new Labor_obj("Nikhil","+91971054107");ob3.addSkill("Hardworking");ob3.addSkill("Smart");ob3.addSkill("Nice");
        labor_list.add(ob3);
        Labor_obj ob4= new Labor_obj("Sid","+91971054107");ob4.addSkill("Hardworking");ob4.addSkill("Smart");ob4.addSkill("Nice");
        labor_list.add(ob4);
        Labor_obj ob5= new Labor_obj("Muskan","+91971054107") ;ob5.addSkill("Hardworking");ob5.addSkill("Smart");ob5.addSkill("Nice");
        labor_list.add(ob5);
        Labor_obj ob6= new Labor_obj("Akhil", "+91971054107");ob6.addSkill("Hardworking");ob6.addSkill("Smart");ob6.addSkill("Nice");
        labor_list.add(ob6);
        Labor_Adapter adapter= new Labor_Adapter(labor_list);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(this);

recyclerView.setLayoutManager(llm);
recyclerView.setAdapter(adapter);
        }


    }

