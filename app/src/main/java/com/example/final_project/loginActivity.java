package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void forget_lab(View view) {
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
    }
}