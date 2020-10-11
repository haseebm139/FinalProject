package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEmail , mPassword  ;
    private Button mloginbtn;
    private TextView regTv, forgetTv;
    private ProgressBar mPbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEmail = (EditText)findViewById(R.id.email);
        mPassword =(EditText)findViewById(R.id.password);
        forgetTv = (TextView) findViewById(R.id.forgetTv);
        mPbar = (ProgressBar) findViewById(R.id.logProBar);
        mloginbtn = (Button) findViewById(R.id.btnlogin);
        regTv = (TextView) findViewById(R.id.regTv);
        regTv.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regTv:
                Intent it = new Intent(loginActivity.this,RegistrationActivity.class);
                startActivity(it);
                break;
            case R.id.btnlogin:
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if (TextUtils.isEmpty(email) || (TextUtils.isEmpty(password))) {
                    Toast.makeText(loginActivity.this,"All Field Required", Toast.LENGTH_SHORT).show();
                } else {
                    login(email,password);
                }

        }


    }

    private void login(String email, String password) {
        mPbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mPbar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Intent it = new Intent(loginActivity.this,User_Dashboard.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);
                    finish();

                } else {
                    Toast.makeText(loginActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    /*public void forget_lab(View view) {
        Intent it = new Intent(loginActivity.this,ForgetPassActivity.class);
        startActivity(it);
    }

    public void login_btn(View view) {
        Intent it = new Intent(loginActivity.this,User_Dashboard.class);
        startActivity(it);
    }

    public void reg_lab(View view) {
        Intent it = new Intent(loginActivity.this,RegistrationActivity.class);
        startActivity(it);
    }*/
}