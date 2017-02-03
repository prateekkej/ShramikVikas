package com.shramikvikas;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Registration extends AppCompatActivity {
    private ImageView pass;
    private TextView pin;
    private boolean pass_status=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        pass = (ImageView)findViewById(R.id.pass);
        pin= (TextView)findViewById(R.id.numericpin);
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
