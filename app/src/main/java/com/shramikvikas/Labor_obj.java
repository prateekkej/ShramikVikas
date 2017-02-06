package com.shramikvikas;

import java.util.ArrayList;

/**
 * Created by Prateek on 06-02-2017.
 */

public class Labor_obj {
    private String name,a,phone;
    private ArrayList<String> skills;
    protected Labor_obj(String a,String b)
    {name=a;
        phone=b;
    skills=new ArrayList<>();}
    public void setName(String a){name=a;}
    public void setPhone(String a){phone=a;}
    public String getName(){return name;}
    public String getPhone(){return phone;}
    public ArrayList<String> getSkills(){return skills;}
    public void addSkill(String a)
    {skills.add(a);
    }

}
