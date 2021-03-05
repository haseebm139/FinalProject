package com.example.final_project;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexzh.circleimageview.CircleImageView;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.w3c.dom.Text;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {


    private ShapeableImageView profileImageView;
    private TextView uploadProbtn, mUser, mEmail, mPhone;
    private StorageReference storageReference;
    private static final int GALLERY_REQUEST = 1000;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private String userID;
    private FirebaseUser user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child(mAuth.getCurrentUser().getUid());

        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImageView);
            }
        });

        profileImageView = findViewById(R.id.proimageView);
        uploadProbtn = findViewById(R.id.uploadpro);

        mUser = findViewById(R.id.tvname);
        mEmail = findViewById(R.id.tvemail);
        mPhone = findViewById(R.id.tvphone);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass userProfile = snapshot.getValue(UserHelperClass.class);
                if(userProfile != null){
                    String userName = userProfile.username;
                    String userEmail = userProfile.email;
                    String userPhone = userProfile.phoneNumber;

                    mUser.setText(userName);
                    mEmail.setText(userEmail);
                    mPhone.setText(userPhone);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        uploadProbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Gallery
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode ==  GALLERY_REQUEST){
                if(resultCode == Activity.RESULT_OK){
                    if(data != null){
                        Uri imageUri = data.getData();
                        uploadImageToFirebase(imageUri);
                        CropImage.activity(imageUri).start(this);
                    }
                    if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                        CropImage.ActivityResult result = CropImage.getActivityResult(data);
                            if(resultCode == RESULT_OK){
                                Uri imageUri = result.getUri();
                            }else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                                Exception error = result.getError();
                            }
                    }
                }
            }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        // upload image to firebase database
        final StorageReference fileRef = storageReference.child(mAuth.getCurrentUser().getUid());
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImageView);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "Failed Image", Toast.LENGTH_SHORT).show();
            }
        });
    }
}