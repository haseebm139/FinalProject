package com.example.final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileEmail;
    private EditText profileName, profileNumber;
    private Button saveProfile;

    private DatabaseReference dataRef;
    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileNumber = findViewById(R.id.profileNumber);
        saveProfile = findViewById(R.id.saveProfile);



        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateAndsave();

            }
        });

        /*mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        DocumentReference dr = fStore.collection("Users").document(userID);
        dr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                
                    profileName.setText(documentSnapshot.getString("firstname"));
            }
        });*/
    }

    private void ValidateAndsave() {
        if (TextUtils.isEmpty(profileName.getText().toString()))
        {
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(profileNumber.getText().toString()))
        {
            Toast.makeText(this, "Please Enter Your Phone No", Toast.LENGTH_SHORT).show();
        }else {
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("firstname", profileName.getText().toString());
            userMap.put("phNumber", profileNumber.getText().toString());

            dataRef.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

        }
    }
}