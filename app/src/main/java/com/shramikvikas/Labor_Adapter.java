package com.shramikvikas;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prateek on 06-02-2017.
 */

public class Labor_Adapter extends RecyclerView.Adapter<Labor_Adapter.view_holder> {
   private ArrayList<Labor_obj> data;
    public Labor_Adapter(ArrayList<Labor_obj> list) {
            super(); data=list;
    }

    @Override
    public Labor_Adapter.view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new view_holder(v);
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {
    holder.name.setText(data.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }




class view_holder extends RecyclerView.ViewHolder{
    public TextView name;
    public CardView item;
    public view_holder(View itemView) {
        super(itemView);
        item=(CardView)itemView.findViewById(R.id.cardd);
        name= (TextView) item.findViewById(R.id.textView9);
    }
}
}
