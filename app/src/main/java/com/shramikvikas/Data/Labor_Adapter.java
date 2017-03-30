package com.shramikvikas.Data;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shramikvikas.R;

import java.util.ArrayList;

/**
 * Created by Prateek on 06-02-2017.
 */

public class Labor_Adapter extends RecyclerView.Adapter<Labor_Adapter.view_holder> {
    protected ArrayList<Labor_obj> data;
    private RecyclerView recycler;
    private Typeface medium,light;
  private Drawable cardBackgroundCollapsed,cardBackgroundExpanded;
    public static ArrayList<Labor_obj> recents;
    private Intent message,share,call;
    private boolean anySelected;
        private int expandedPosition,lastPosition = -1,previousExpanded=-1;

    public Labor_Adapter(ArrayList<Labor_obj> list,RecyclerView recyclerView) {
            super(); data=list;recycler=recyclerView; expandedPosition=-1;
        anySelected=false;
        medium= Typeface.createFromAsset(recyclerView.getContext().getAssets(),"fonts/Raleway-Medium.ttf");
        recents= new ArrayList<>();
            }
    @Override
    public Labor_Adapter.view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new view_holder(v);
    }

    @Override
    public void onBindViewHolder(final view_holder holder, final int position) {
        final boolean isExpanded=position==expandedPosition;

        if(isExpanded)
        { holder.collapsed.setVisibility(View.GONE);
            holder.expandedcard.setVisibility(View.VISIBLE);
            expandedDefination(holder, position);
       }
        else{
            holder.expandedcard.setVisibility(View.GONE);
            holder.collapsed.setVisibility(View.VISIBLE);
            collapsedDefination(holder, position);
           }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (anySelected&&data.get(holder.getAdapterPosition()).isSelected){
                   data.get(holder.getAdapterPosition()).isSelected=false;
                    notifyItemChanged(position);

                }
                else
                {                    data.get(holder.getAdapterPosition()).isSelected=true;
                    anySelected=true;
                    notifyItemChanged(position);                                 }
                return true;
            }
        });

              holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(expandedPosition!=-1&& expandedPosition!=position)
                   {notifyItemChanged(expandedPosition);                 }
                    expandedPosition = isExpanded ? -1 : position;
                    notifyItemChanged(position);
            }
        });
        setAnimation(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void collapsedDefination(final Labor_Adapter.view_holder holder, final int position)
    {if(data.get(holder.getAdapterPosition()).isSelected)
    {holder.itemView.setBackgroundColor(Color.CYAN);
    }
        else{
        holder.itemView.setBackgroundColor(Color.WHITE);
   }
         holder.name.setText(data.get(position).getName());
         holder.skills.setText(data.get(position).getSkills());
         holder.ratingBar.setRating(((float) data.get(position).getRating()));
}
    private void setAnimation(View view, int position){

        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.fade_in);
            view.startAnimation(animation);
            lastPosition = position;
        }}


        void expandedDefination(Labor_Adapter.view_holder holder,final int position )
         {if(data.get(holder.getAdapterPosition()).isSelected)
         {             holder.itemView.setBackgroundColor(Color.CYAN);
         }
         else{
             holder.itemView.setBackgroundColor(Color.WHITE);}
             holder.name_expanded.setText(data.get(position).getName());
              holder.rating_expanded.setRating(((float) data.get(position).getRating()));
              holder.phoneno.setText(data.get(position).getPhone());
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

    }

    class view_holder extends RecyclerView.ViewHolder {
        private TextView name,skills,name_expanded,phoneno;
        private RatingBar ratingBar,rating_expanded;
        private ImageButton share;
        public RelativeLayout expandedcard,collapsed;

       public view_holder(View itemView) {
        super(itemView);

           cardBackgroundCollapsed= itemView.getBackground();
           collapsed=(RelativeLayout)itemView.findViewById(R.id.collapsed);
           expandedcard=(RelativeLayout)itemView.findViewById(R.id.expanded_details);
                     share=(ImageButton)itemView.findViewById(R.id.share_expanded);
           name_expanded=(TextView)itemView.findViewById(R.id.name_exp);
           name_expanded.setTypeface(medium);
           rating_expanded=(RatingBar)itemView.findViewById(R.id.rating_expanded);
           phoneno=(TextView)itemView.findViewById(R.id.phone_expanded);
           name= (TextView)itemView.findViewById(R.id.name_collapsed);
           name.setTypeface(medium);
           ratingBar= (RatingBar)itemView.findViewById(R.id.rating_collapsed);
           skills=(TextView)itemView.findViewById(R.id.skill);
                                  }
                                                         }

}
