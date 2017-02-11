package com.shramikvikas;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Registration extends AppCompatActivity {
    private ImageView pass;
    private TextView pin;
    private Button signup;
    private boolean pass_status=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        Typeface bell_font = Typeface.createFromAsset(getAssets(),"fonts/bellb.ttf");
        TextView reigster= (TextView)findViewById(R.id.textView4);
        reigster.setTypeface(bell_font);
        pass = (ImageView)findViewById(R.id.pass);
        pin= (TextView)findViewById(R.id.numericpin);
        signup=(Button)findViewById(R.id.signup);
        signup.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Raleway-Medium.ttf"));
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass_status==true)
                {pass.setImageResource(R.drawable.ic_lock_open_black_48dp);
                    pin.setTransformationMethod(null);
                    pass_status=false;
                }else{
                    pass.setImageResource(R.drawable.ic_lock_outline_black_48dp);
                    pin.setTransformationMethod(new PasswordTransformationMethod());
                    pass_status=true;
                }

            }
        });

    }
}
