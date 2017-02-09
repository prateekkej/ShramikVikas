package com.shramikvikas;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.*;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;
public class LoginActivity extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener {
    private SignInButton signinbutton;
    private GoogleSignInOptions signInOptions;
    private GoogleApiClient apiClient;
    private Button register;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri link = Uri.parse("https://www.google.com/search?q=Labor+Network");
                Intent web = new Intent(Intent.ACTION_VIEW, link);
                startActivity(web);
            }
        });
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent im = new Intent(LoginActivity.this, Registration.class);
                startActivity(im);
                finish();
            }
        });

            signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
            signinbutton = (SignInButton) findViewById(R.id.signInButton);
            signinbutton.setSize(SignInButton.SIZE_WIDE);
            signinbutton.setColorScheme(SignInButton.COLOR_AUTO);
            apiClient = new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).addApi(AppIndex.API).build();
            signinbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("Google Button pressed", "");
                    Intent signin = Auth.GoogleSignInApi.getSignInIntent(apiClient);
                    startActivityForResult(signin, 100);
                }
            });
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                /*if(loginResult.getAccessToken() != null) {
                    Log.i(TAG, "Access Token:: " + loginResult.getAccessToken());
                    facebookSuccess();
                }
                //Toast.makeText(LoginActivity.this, "Profile accessed", Toast.LENGTH_SHORT).show();
                else*/
                    if (loginResult != null) {
                    Profile profile = Profile.getCurrentProfile();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent im= new Intent(LoginActivity.this,HomeScreen.class);
                        startActivity(im);
                    if (profile != null) {
                        displayMessage(profile);
                    }
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                Log.d("Facebook cancel","");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Facebook error","");
                Toast.makeText(LoginActivity.this, "Profile error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*private void facebookSuccess(){
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            displayMessage(profile);
        }
    }*/

    private  void displayMessage(Profile profile){
        if(profile != null)
        {

            ImageView fimg = (ImageView) findViewById(R.id.logo);
            Uri profileURI = profile.getProfilePictureUri(150,150);
            Picasso.with(LoginActivity.this).load(profileURI).resize(150,150).into(fimg);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        displayMessage(Profile.getCurrentProfile());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(count == 1) {
            if (requestCode == 100) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                    GoogleSignInAccount account = result.getSignInAccount();
                    ImageView uimg = (ImageView) findViewById(R.id.logo);
                    Picasso.with(getApplicationContext()).load(account.getPhotoUrl()).into(uimg);
                    Intent im = new Intent(LoginActivity.this, HomeScreen.class);
                    startActivity(im);
                } else {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
            callbackManager.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
Toast.makeText(getApplicationContext(),"Connection Error :"+ connectionResult.getErrorCode(),Toast.LENGTH_SHORT ).show();
    }
}

