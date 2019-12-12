package com.example.facebook_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String name = "Note 1";
    private final int REQUEST_CODE = 20;

    CallbackManager callbackManager = CallbackManager.Factory.create();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ImageView Spongebob = (ImageView) findViewById(R.id.Profile);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);




        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Intent i = new Intent(MainActivity.this, NoteActivity.class);
                        startActivityForResult(i, REQUEST_CODE);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });




        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            name = data.getExtras().getString("text");
            // Toast the name to display temporarily on screen
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
