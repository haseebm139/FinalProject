package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Objects;

public class ForgetPassActivity extends AppCompatActivity {

    private EditText mEmail;
    private Button mSendBtn;
    private TextView mRsetTv;
    private ProgressBar mPBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ForgetPassActivity.this,MainActivity.class));
            }
        });
        mEmail = findViewById(R.id.email);
        mSendBtn = findViewById(R.id.rSetBtn);
        mRsetTv = findViewById(R.id.resetTv);
        mPBar = findViewById(R.id.proBar);
        mAuth = FirebaseAuth.getInstance();

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPBar.setVisibility(View.VISIBLE);
                mAuth.fetchSignInMethodsForEmail(mEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.getResult().getSignInMethods().isEmpty()) {
                            mPBar.setVisibility(View.GONE);
                            mRsetTv.setText(R.string.forNewAccount);
                        } else {
                            mAuth.sendPasswordResetEmail(mEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mPBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        mRsetTv.setText("An Email to reset password has been sent to you email address");
                                    } else {
                                        mRsetTv.setText(task.getException().getMessage());
                                    }

                                }
                            });
                        }
                    }
                });
            }
        });



    }


}