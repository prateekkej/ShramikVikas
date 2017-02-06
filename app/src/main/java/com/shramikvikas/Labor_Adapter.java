package com.shramikvikas;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prateek on 06-02-2017.
 */

public class Labor_Adapter extends RecyclerView.Adapter<Labor_Adapter.view_holder> {
   private ArrayList<Labor_obj> data;
        private int lastPosition = -1;
    public Labor_Adapter(ArrayList<Labor_obj> list) {
            super(); data=list;
            }

    @Override
    public Labor_Adapter.view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new view_holder(v);
    }
private Intent call;
    @Override
    public void onBindViewHolder(final view_holder holder, final int position) {
    holder.name.setText(data.get(position).getName());
        holder.skills.setText(data.get(position).getSkills());
                holder.ratingBar.setRating(((float) data.get(position).getRating()));
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.get(position).getPhone()));
                view.getContext().startActivity(call);
            }
        });
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void setAnimation(View view, int position){

        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.fade_in);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }
    class view_holder extends RecyclerView.ViewHolder{
    public TextView name,skills;private RatingBar ratingBar;private ImageView phone;
       public view_holder(View itemView) {
        super(itemView);
                name= (TextView)itemView.findViewById(R.id.textView9);
        ratingBar= (RatingBar)itemView.findViewById(R.id.rating);
           phone=(ImageView)itemView.findViewById(R.id.imageView3);
           skills=(TextView)itemView.findViewById(R.id.skill);
    }
}
}