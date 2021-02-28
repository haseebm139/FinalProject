package com.example.final_project;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {



    private TextView mUser , mEmail, mPhone, mBirthday ;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        mUser = findViewById(R.id.tvname);
        mEmail = findViewById(R.id.tvemail);
        mPhone = findViewById(R.id.tvphone);
        mBirthday = findViewById(R.id.edtDOB);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass userProfile = snapshot.getValue(UserHelperClass.class);
                if(userProfile != null){
                    String userName = userProfile.username;
                    String userEmail = userProfile.email;
                    String userPhone = userProfile.phoneNumber;
                    String userBirthday = userProfile.birthday;

                    mUser.setText(userName);
                    mEmail.setText(userEmail);
                    mPhone.setText(userPhone);
                    mBirthday.setText(userBirthday);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}