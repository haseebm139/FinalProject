package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    /*private TextView profileEmail, profileName, profileNumber;
    private ImageView profileImage;
    private Button saveProfile;
    private ProgressDialog mProgressDialog;

    private DatabaseReference dataRef;
    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    private String userID;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        /*String user_id = getIntent().getStringExtra("user_id");
        dataRef = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(user_id);
        profileImage = findViewById(R.id.proimageView);
        profileEmail = findViewById(R.id.edtEmail);
        profileName = findViewById(R.id.edtUsername);
        profileNumber = findViewById(R.id.edtPhoneNo);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading User Data");
        mProgressDialog.setMessage("Please Wait a While we load the user Data");
        mProgressDialog.setCanceledOnTouchOutside(false);*/

        /*dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String mName = snapshot.child("name").getValue().toString();
                String mEmail = snapshot.child("email").getValue().toString();
                String mNumber = snapshot.child("phoneNumber").getValue().toString();
                String mProfilePic = snapshot.child("imageURL").getValue().toString();

                profileEmail.setText(mEmail);
                profileName.setText(mName);
                profileNumber.setText(mNumber);

                //Picasso.with(ProfileActivity.this,load(profileImage));

                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }
}