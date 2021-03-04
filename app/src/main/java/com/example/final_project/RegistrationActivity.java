package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.AbstractList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEmail, mPassword, mCpassword, mUsername, mNumber, mDOB ;
    private Button mSubmitbtn, mCustomerBtn, mProviderBtn;
    private ProgressBar mPbar;
    private TextView mLogintv;
    private RadioGroup mRadioGro;
    private RadioButton mRadioBtn;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //toolbar = findViewById(R.id.toolbar);



        mEmail = findViewById(R.id.email);
        mUsername = findViewById(R.id.username);
        mNumber = findViewById(R.id.phNo);
        mDOB = findViewById(R.id.dob);
        mPassword = findViewById(R.id.password);
        mCpassword = findViewById(R.id.cpassword);
        mLogintv = findViewById(R.id.loginTv);
        mSubmitbtn = findViewById(R.id.btnSub);
        mRadioGro = findViewById(R.id.radioGro);
        mCustomerBtn = findViewById(R.id.customerBtn);
        mProviderBtn = findViewById(R.id.providerBtn);



        mPbar = findViewById(R.id.proBar);
        mAuth = FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);

        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        mProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RegistrationActivity.this,loginActivity.class);
            }
        });
        mDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDOB.setText(day+"-"+(month+1)+"-"+year);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }

    private void registerUser(){
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String cpassword = mCpassword.getText().toString().trim();

        final String username = mUsername.getText().toString().trim();
        final String phoneNo = mNumber.getText().toString().trim();
        final String date_of_birth = mDOB.getText().toString().trim();

        int cheakedId = mRadioGro.getCheckedRadioButtonId();
        RadioButton selected_gender = mRadioGro.findViewById(cheakedId);

        if (selected_gender == null) {
            Toast.makeText(RegistrationActivity.this,"Select Gender please", Toast.LENGTH_SHORT).show();
        } else {
            final String gender = selected_gender.getText().toString();
        }


        if (TextUtils.isEmpty(username)){
            mUsername.setError("Username is Required");
            mUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(cpassword)){
            mCpassword.setError("Required");
            mCpassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)){
            mEmail.setError("Email is Required");
            mEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            mPassword.setError("Password is Required");
            mPassword.requestFocus();
            return;
        }
        if(password.length() < 8) {
            mPassword.setError("Password must be 8 characters");
            mPassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Invalid Email...");
            mEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phoneNo)){
            mEmail.setError("Phone is Required");
            mEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(date_of_birth)){
            mDOB.setError("Date of Birth is Required");
            //mDOB.requestFocus();
            return;
        }

        mPbar.setVisibility(View.VISIBLE);

        //Register the user in Firebase
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser rUser = mAuth.getCurrentUser();
                    String email = rUser.getEmail();
                    String userId = rUser.getUid();
                    mReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("userId",userId);
                    hashMap.put("email",email);
                    hashMap.put("username",username);
                    hashMap.put("phoneNumber",phoneNo);
                    hashMap.put("date_of_birth",date_of_birth);
                    hashMap.put("password",password);
                    //hashMap.put("age",age);  //for age
                    hashMap.put("imageURL","");
                    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                    mReference.child(userId).setValue(hashMap);

                     mReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            mPbar.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                Intent it = new Intent(RegistrationActivity.this,loginActivity.class);
                                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(it);
                            } else {
                                Toast.makeText(RegistrationActivity.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                                mPbar.setVisibility(View.GONE);
                            }


                        }
                    });

                }else {
                    mPbar.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



}