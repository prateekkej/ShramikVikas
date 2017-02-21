package com.shramikvikas;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Registration extends AppCompatActivity {
    private ImageView pass;
    private TextView pin;
    private EditText fname,lname,phone,email,address;
    private Button signup;
    private boolean pass_status=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        ImageButton back= (ImageButton)findViewById(R.id.Home);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.navigateUpFromSameTask(Registration.this);
            }
        });
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.emailid);
        phone=(EditText)findViewById(R.id.phNumber);
        address=(EditText)findViewById(R.id.address);
        pass = (ImageView)findViewById(R.id.pass);
        pin= (TextView)findViewById(R.id.numericpin);


        signup=(Button)findViewById(R.id.signup);
        signup.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Raleway-Medium.ttf"));
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody requestBody= new FormBody.Builder().add("firstName",fname.getText().toString())
                        .add("lastName",lname.getText().toString())
                        .add("emailId",email.getText().toString())
                        .add("phoneNumber",phone.getText().toString())
                        .add("address",address.getText().toString())
                        .add("Password",pin.getText().toString()).build();
            }
        });
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
