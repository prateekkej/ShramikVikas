package com.shramikvikas;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prateek on 07-02-2017.
 */

public class Recent_Fragment extends Fragment {
      public static Recents_adapter recents_adapter;
    public   ListView listView;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        recents_adapter.notifyDataSetChanged();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.recent_layout,container,false);
       listView= (ListView)v.findViewById(R.id.recents_list);
        TextView no_recents =(TextView)v.findViewById(R.id.textView9);
        recents_adapter = new Recents_adapter(getContext(),Labor_Adapter.recents);
        if(recents_adapter.getCount()==0)
        {            listView.setVisibility(View.GONE);
            no_recents.setVisibility(View.VISIBLE);
        }
        listView.setAdapter(recents_adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                recents_adapter.remove(recents_adapter.getItem(i));
                return true;
            }
        });


        return v;
    }
}
class Recents_adapter extends ArrayAdapter<Labor_obj>
{

    public Recents_adapter(Context context, ArrayList<Labor_obj> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
               if (convertView==null)
{    convertView= LayoutInflater.from(getContext()).inflate(R.layout.card,parent,false);
}
        final Labor_obj data= getItem(position);
        TextView name_collapsed= (TextView)convertView.findViewById(R.id.name_collapsed);
        TextView skills= (TextView)convertView.findViewById(R.id.skill);
        ImageView img= (ImageView) convertView.findViewById(R.id.image_collapsed);
        ImageView dial= (ImageView) convertView.findViewById(R.id.dial);
        name_collapsed.setText(data.getName());
        skills.setText(data.getSkills());
        img.setImageResource(R.drawable.placeholder);
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent call= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.getPhone()));
                view.getContext().startActivity(call);

            }
        });
        return convertView;
    }
}