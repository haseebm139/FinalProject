package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    private EditText mNPassword, mOPassword, mCPassword;
    private Button mCPassBtn;
    private ProgressBar mPBar;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePassword.this,User_Dashboard.class));
            }
        });
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mPBar = findViewById(R.id.proBar);
        mNPassword = findViewById(R.id.newPassword);
        mOPassword = findViewById(R.id.oldPassword);
        mCPassword = findViewById(R.id.cPassword);
        mCPassBtn = findViewById(R.id.btnChPass);

        mCPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = mOPassword.getText().toString();
                String newPass = mNPassword.getText().toString();
                String chPass = mCPassword.getText().toString();

                if (TextUtils.isEmpty(oldPass)){
                    mOPassword.setError("Required");
                    mOPassword.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(newPass)){
                    mNPassword.setError("Required");
                    mNPassword.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(chPass)){
                    mCPassword.setError("Required");
                    mCPassword.requestFocus();
                    return;
                }
                if(newPass.length() < 8) {
                    mCPassword.setError("Password must be 8 characters");
                    mCPassword.requestFocus();
                    return;
                }
                else if (!mCPassword.equals(mNPassword)){
                    mCPassword.setError("Confirm Password doesn't match with New Password ");
                    mCPassword.requestFocus();
                    return;
                } else {
                    //changePassword(mOPassword, mNPassword);
                }

            }


        });

    }
    /*private void changePassword(String mOPassword, final String mNPassword) {
        mPBar.setVisibility(View.VISIBLE);
        AuthCredential credential = EmailAuthProvider.getCredential(mUser.getEmail(),mOPassword);

    }*/


}