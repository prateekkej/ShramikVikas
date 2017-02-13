package com.shramikvikas.CustomViews;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Prateek on 11-02-2017.
 */

public class ourTextView extends TextView {
    public ourTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface normal= Typeface.createFromAsset(context.getAssets(),"fonts/Raleway-Light.ttf");
        this.setTypeface(normal);
    }
}
