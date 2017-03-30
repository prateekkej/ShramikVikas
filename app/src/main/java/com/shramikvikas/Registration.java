package com.shramikvikas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity{
    private ImageView pass;
    private TextView pin;
    private EditText fname,lname,phone,email,address;
    private Button signup;
    private boolean pass_status=true;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        ImageButton back = (ImageButton) findViewById(R.id.Home);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.navigateUpFromSameTask(Registration.this);
            }
        });
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.emailid);
        phone = (EditText) findViewById(R.id.phNumber);
        address = (EditText) findViewById(R.id.address);
        pass = (ImageView) findViewById(R.id.pass);
        pin = (TextView) findViewById(R.id.numericpin);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //private void  saveUserInformation();

        signup = (Button) findViewById(R.id.signup);
        signup.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf"));


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*RequestBody requestBody= new FormBody.Builder().add("firstName",fname.getText().toString())
                        .add("lastName",lname.getText().toString())
                        .add("emailId",email.getText().toString())
                        .add("phoneNumber",phone.getText().toString())
                        .add("address",address.getText().toString())
                        .add("Password",pin.getText().toString()).build();
            */
                //method calling to save user data
                saveUserInformation();
                //Firebase connectivity to save username and password
                final ProgressDialog progressDialog = ProgressDialog.show(Registration.this, "Please wait...", "Processing...", true);
                (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pin.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(Registration.this, LoginActivity.class);
                                    startActivity(i);
                                } else {
                                    Log.e("ERROR", task.getException().toString());
                                    Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass_status == true) {
                    pass.setImageResource(R.drawable.ic_lock_open_black_48dp);
                    pin.setTransformationMethod(null);
                    pass_status = false;
                } else {
                    pass.setImageResource(R.drawable.ic_lock_outline_black_48dp);
                    pin.setTransformationMethod(new PasswordTransformationMethod());
                    pass_status = true;
                }

            }
        });
    }
    //method to save details on firebase
    private void saveUserInformation(){
        String firstname = fname.getText().toString().trim();
        String lastname = lname.getText().toString().trim();
        String haddress = address.getText().toString();
        String phnumber = phone.getText().toString();

        UserInformation userInformation = new UserInformation(firstname, lastname, haddress, phnumber);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);

    }

}