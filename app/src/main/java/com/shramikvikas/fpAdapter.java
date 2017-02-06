package com.shramikvikas;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Prateek on 07-02-2017.
 */

public class fpAdapter extends FragmentPagerAdapter {
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
protected fpAdapter(FragmentManager fm,Context context){
    super(fm);
}
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new Profile_Fragment();
        }else if(position==1)
        {return new Search_fragment();
        }
        else
        {            return new Recent_Fragment();
        }
    }
}
