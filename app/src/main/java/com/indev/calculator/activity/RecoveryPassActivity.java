package com.indev.calculator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.indev.calculator.R;
import com.indev.calculator.SendMail;

import java.util.Random;

public class RecoveryPassActivity extends AppCompatActivity {

    private EditText editEmail;
    private String email;
    private final String SUBJECT = "Recovery password";
    private final String MESSAGE = "Hi! It is a new password for your account: ";
    private final int PASSWORD_LENGTH = 8;
    private String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_pass);
    }

    public void onSendClick(View v) {
        editEmail = (EditText) findViewById(R.id.editRecoveryEmail);
        email = editEmail.getText().toString().trim();
        newPassword = generatePassword(PASSWORD_LENGTH);
        SendMail sendMail = new SendMail(this, email, SUBJECT, MESSAGE + newPassword);
        sendMail.execute();
    }

    private String generatePassword(int lengthOfCode) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRTSUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < lengthOfCode; i++) {
            int index = random.nextInt(alphabet.length() - 1);
            builder.append(alphabet.charAt(index));
        }
        return builder.toString();
    }
}
