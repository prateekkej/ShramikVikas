package com.shramikvikas;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionValues;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prateek on 06-02-2017.
 */

public class Labor_Adapter extends RecyclerView.Adapter<Labor_Adapter.view_holder> {
   private ArrayList<Labor_obj> data;
    private RecyclerView recycler;
        private int expandedPosition,lastPosition = -1,previousExpanded;
    public Labor_Adapter(ArrayList<Labor_obj> list,RecyclerView recyclerView) {
            super(); data=list;recycler=recyclerView; expandedPosition=-1;
            }
    @Override
    public Labor_Adapter.view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new view_holder(v);
    }
private Intent message,share,call;
    @Override
    public void onBindViewHolder(final view_holder holder, final int position) {
        final boolean isExpanded=position==expandedPosition;
        if(isExpanded)
        { holder.collapsed.setVisibility(View.GONE);
            holder.expandedcard.setVisibility(View.VISIBLE);

       }
        else{
            holder.expandedcard.setVisibility(View.GONE);
            holder.collapsed.setVisibility(View.VISIBLE);
           }
        holder.name_expanded.setText(data.get(position).getName());
        holder.rating_expanded.setRating(((float) data.get(position).getRating()));
        holder.phoneno.setText(data.get(position).getPhone());
        holder.dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.get(position).getPhone()));
                view.getContext().startActivity(call);
            }
        });
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message= new Intent(Intent.ACTION_SENDTO);
                message.setData(Uri.parse("smsto:"+ data.get(position).getPhone()));
                message.putExtra("sms_body","Hello!!I would like to hire you for a day .If interested give me a miss call");
                view.getContext().startActivity(message);
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share= new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT,"Labor Name:"+ data.get(position).getName()+"\n" +
                        "Phone :" + data.get(position).getPhone() + "\n"+
                        "Special:" +data.get(position).getSkills() );
                view.getContext().startActivity(share);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    expandedPosition = isExpanded ? -1 : position;
                    notifyItemChanged(position);
            }
        });
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
        setAnimation(holder.itemView,position);
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
    private TextView name,skills,name_expanded,phoneno;
        private RatingBar ratingBar,rating_expanded;
        private ImageView phone;
        private ImageButton dial,message,share;
       public RelativeLayout expandedcard,collapsed;

       public view_holder(View itemView) {
        super(itemView);
           collapsed=(RelativeLayout)itemView.findViewById(R.id.collapsed);
           expandedcard=(RelativeLayout)itemView.findViewById(R.id.expanded_details);
           share=(ImageButton)itemView.findViewById(R.id.share_expanded);
           dial= (ImageButton)itemView.findViewById(R.id.dial_expanded);
           message=(ImageButton)itemView.findViewById(R.id.message_expanded);
           name_expanded=(TextView)itemView.findViewById(R.id.name_exp);
           rating_expanded=(RatingBar)itemView.findViewById(R.id.rating_expanded);
           phoneno=(TextView)itemView.findViewById(R.id.phone_expanded);
                name= (TextView)itemView.findViewById(R.id.name_collapsed);
        ratingBar= (RatingBar)itemView.findViewById(R.id.rating_collapsed);
           phone=(ImageView)itemView.findViewById(R.id.dial);
           skills=(TextView)itemView.findViewById(R.id.skill);
    }
}

}
