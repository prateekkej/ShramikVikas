package com.shramikvikas.Fragments;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shramikvikas.Data.Labor_Adapter;
import com.shramikvikas.Data.Labor_obj;
import com.shramikvikas.R;

import java.util.ArrayList;

/**
 * Created by Prateek on 07-02-2017.
 */

public class Recent_Fragment extends Fragment {
    public static Recents_adapter recents_adapter;
    public static ListView listView;
    static TextView no_recents;
    private ImageButton del;
    private Typeface medium,light;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recent_layout, container, false);
        medium= Typeface.createFromAsset(container.getContext().getAssets(),"fonts/Raleway-Medium.ttf");
        light= Typeface.createFromAsset(container.getContext().getAssets(),"fonts/Raleway-Light.ttf");
        listView = (ListView) v.findViewById(R.id.recents_list);
        no_recents = (TextView) v.findViewById(R.id.textView9);
        no_recents.setTypeface(medium);
        recents_adapter = new Recents_adapter(getContext(), Labor_Adapter.recents);
        recents_adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                if (recents_adapter.getCount() == 0) {
                    no_recents.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                } else {
                    listView.setVisibility(View.VISIBLE);
                    no_recents.setVisibility(View.GONE);
                }
            }
        });
        listView.setAdapter(recents_adapter);
        LayoutTransition transition = new LayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
        listView.setLayoutTransition(transition);

        return v;

    }

    class Recents_adapter extends ArrayAdapter<Labor_obj> {
        private TextView name_expanded, phoneno;
        private RatingBar rating_expanded;
        boolean isExpanded;
        int expanded_position = -1;
        private ImageButton dial_expanded, message_expanded, sharevia,del;
        private Intent call, share, message;
        private RelativeLayout r, s;

        public Recents_adapter(Context context, ArrayList<Labor_obj> objects) {
            super(context, 0, objects);
            isExpanded = false;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.card, parent, false);
            }
            final Labor_obj data = getItem(position);
            rating_expanded = (RatingBar) convertView.findViewById(R.id.rating_expanded);
            name_expanded = (TextView) convertView.findViewById(R.id.name_exp);
            name_expanded.setTypeface(medium);
            phoneno = (TextView) convertView.findViewById(R.id.phone_expanded);
            sharevia = (ImageButton) convertView.findViewById(R.id.share_expanded);
            rating_expanded.setRating((float) data.getRating());
            sharevia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_TEXT, "Labor Name:" + data.getName() + "\n" +
                            "Phone :" + data.getPhone() + "\n" +
                            "Special:" + data.getSkills());
                    view.getContext().startActivity(share);
                }
            });
            del=(ImageButton)convertView.findViewById(R.id.delete);
            del.setVisibility(View.VISIBLE);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recents_adapter.remove(data);
                    notifyDataSetChanged();
                }
            });
            name_expanded.setText(data.getName());
            phoneno.setText(data.getPhone());
            s = (RelativeLayout) convertView.findViewById(R.id.collapsed);
            s.setVisibility(View.GONE);
            r = (RelativeLayout) convertView.findViewById(R.id.expanded_details);
            r.setVisibility(View.VISIBLE);

            return convertView;
        }
    }
}

