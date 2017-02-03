package com.shramikvikas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.*;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener {
    private SignInButton signinbutton;
    private GoogleSignInOptions signInOptions;
    private GoogleApiClient apiClient;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent im= new Intent(MainActivity.this,Registration.class);
                startActivity(im);
                finish();
            }
        });
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        signinbutton = (SignInButton) findViewById(R.id.signInButton);
        signinbutton.setSize(SignInButton.SIZE_WIDE);
        signinbutton.setColorScheme(SignInButton.COLOR_AUTO);
        apiClient = new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signin = Auth.GoogleSignInApi.getSignInIntent(apiClient);
                startActivityForResult(signin, 100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                GoogleSignInAccount account = result.getSignInAccount();
                ImageView uimg = (ImageView) findViewById(R.id.imageView2);
                Picasso.with(getApplicationContext()).load(account.getPhotoUrl()).into(uimg);
            } else {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}