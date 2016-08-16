package com.indev.calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.indev.calculator.R;

public class RecoveryPassActivity extends AppCompatActivity {

    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_pass);
    }

    public void onSendClick(View v) {

        editEmail = (EditText) findViewById(R.id.editRecoveryEmail);
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, editEmail.getText().toString());

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
