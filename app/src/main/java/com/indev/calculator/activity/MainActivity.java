package com.indev.calculator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.indev.calculator.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onForgottenPassClick(View v) {
        Intent intent = new Intent(this, RecoveryPassActivity.class);
        startActivity(intent);

    }

    public void onSendClick(View v) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void onFaceBookClick(View v) {

    }

    public void onGoogleClick(View v) {

    }

    public void onTwitterClick(View v) {

    }

    public void onRegistrationClick(View v) {

    }


}
