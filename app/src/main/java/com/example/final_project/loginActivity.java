package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity{
    private EditText mEmail , mPassword  ;
    private Button mloginbtn;
    private TextView regTv, forgetTv;
    private ProgressBar mPbar;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        forgetTv = (TextView) findViewById(R.id.forgetTv);
        mPbar = (ProgressBar) findViewById(R.id.logProBar);
        mloginbtn = (Button) findViewById(R.id.btnlogin);
        regTv = (TextView) findViewById(R.id.regTv);

        mAuth = FirebaseAuth.getInstance();

        forgetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(loginActivity.this,ForgetPassActivity.class);
                startActivity(it);

            }
        });
        regTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(loginActivity.this,RegistrationActivity.class);
                startActivity(it);

            }
        });
        // Login Button Click Listener
        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Email is Required");
                }
                if (password.length() < 8) {
                    mPassword.setError("Password Must be 8 Characters");
                }
                // authentication the user
                mPbar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mPbar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {

                            //it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |//Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(new Intent(getApplicationContext(),User_Dashboard.class));
                            //finish();
                        } else {
                            Toast.makeText(loginActivity.this,"Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mPbar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}