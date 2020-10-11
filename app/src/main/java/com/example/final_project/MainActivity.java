package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if the user is new
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(),loginActivity.class));
            finish();
        }
    }

    public void reg_btn(View view) {
        Intent it = new Intent(MainActivity.this,loginActivity.class);
        startActivity(it);


    }
}