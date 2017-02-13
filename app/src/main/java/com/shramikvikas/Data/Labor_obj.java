package com.shramikvikas.Data;

import java.util.ArrayList;

/**
 * Created by Prateek on 06-02-2017.
 */

public class Labor_obj {
    private String name,a,phone;
    private double rating;
    private ArrayList<String> skills;
    private String skill;
    boolean flag,isSelected;
    public Labor_obj(String a,String b)
    {name=a;
        phone=b;
        rating=0;
        flag=true;
        isSelected=false;
    skills=new ArrayList<>();}
    public void setName(String a){name=a;}
    public void setRating(double a){rating=a;}
    public void setPhone(String a){phone=a;}
    public String getName(){return name;}
    public double getRating(){return rating;}
    public String getPhone(){return phone;}
    public String getSkills(){
        return skill;    }
    public void addSkill(String a)
    {skills.add(a);
        if(flag==true){skill=a;flag=false;}
        else{
            skill+= "," + a;
        }
    }
}
