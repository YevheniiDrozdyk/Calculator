package com.indev.calculator.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.indev.calculator.R;
import com.indev.calculator.adapter.ListChildrenAdapter;
import com.indev.calculator.model.ListChildrenModel;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private EditText mEditFirstName;
    private EditText mEditLastName;
    private EditText mEditEmail;
    private EditText mEditPassword;
    private ListView mListView;
    private int mItemPosition;
    private ArrayList<ListChildrenModel> mListChildren;
    private ListChildrenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiateEditTextUser();
        setOnFabClick();

        mListChildren = new ArrayList<>();
        mListChildren.add(new ListChildrenModel("CHILDREN"));
        mListView = (ListView) findViewById(R.id.listChildren);
        adapter = new ListChildrenAdapter(RegistrationActivity.this, mListChildren);
        mListView.setAdapter(adapter);
    }

    private void initiateEditTextUser() {
        setContentView(R.layout.activity_registration);
        mEditFirstName = (EditText) findViewById(R.id.editFirstName);
        mEditLastName = (EditText) findViewById(R.id.editLastName);
        mEditEmail = (EditText) findViewById(R.id.editEmail);
        mEditPassword = (EditText) findViewById(R.id.editPassword);
    }

    private void registerUser(String email, String password) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Register user...");
        mProgressDialog.show();
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mProgressDialog.dismiss();
                    mAuth.signOut();
                    finish();
                } else {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Please, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onSendClick(View view) {

        String all = "";
        for (int i = 0; i < mListChildren.size(); i++) {
            {
                all += mListChildren.get(i).getArrayList().get(i).getValue() + "\n";
            }
        }
        Toast.makeText(RegistrationActivity.this, all, Toast.LENGTH_LONG).show();

        String firstName = mEditFirstName.getText().toString();
        String lastName = mEditLastName.getText().toString();
        String email = mEditEmail.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        if (email.equals("")) {
            Toast.makeText(getApplicationContext(), "Please. write email!", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(getApplicationContext(), "Please. write password!", Toast.LENGTH_SHORT).show();
        } else {
            //registerUser(email, password);
        }
    }

    private void setOnFabClick() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListChildren.add(new ListChildrenModel("CHILDREN"));
                adapter = new ListChildrenAdapter(RegistrationActivity.this, mListChildren);
                mListView.setAdapter(adapter);
                mListView.setSelection(++mItemPosition);
            }
        });
    }
}