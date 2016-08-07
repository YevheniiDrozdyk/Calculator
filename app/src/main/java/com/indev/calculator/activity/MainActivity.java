package com.indev.calculator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.indev.calculator.R;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginFacebook;
    private CallbackManager callbackFacebookManager;
    private final String ERROR_CONNECTION = "You have some error with Internet connection!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        onFacebookClick();
    }

    public void onForgottenPassClick(View v) {
        Intent intent = new Intent(this, RecoveryPassActivity.class);
        startActivity(intent);

    }

    public void onSendClick(View v) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    private void onFacebookClick() {
        callbackFacebookManager = CallbackManager.Factory.create();
        loginFacebook = (LoginButton) findViewById(R.id.btnFacebook);
        loginFacebook.registerCallback(callbackFacebookManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, ERROR_CONNECTION, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackFacebookManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onGoogleClick(View v) {

    }

    public void onTwitterClick(View v) {

    }

    public void onRegistrationClick(View v) {

    }


}
