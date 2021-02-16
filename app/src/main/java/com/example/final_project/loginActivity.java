package com.example.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class loginActivity extends AppCompatActivity{

    private static final int RC_SIGN_IN =100;
    GoogleSignInClient  mGoogleSignInClient;
    private EditText mEmail , mPassword ;
    private Button mloginbtn;
    private TextView regTv, forgetTv;
    private ProgressBar mPbar;
    private FirebaseAuth mAuth;
    private SignInButton mGoogleLoginBtn;



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
        mGoogleLoginBtn = findViewById(R.id.googleIdBtn);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

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
                    mEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Email is Required");
                    mPassword.requestFocus();
                    return;
                }
                if (password.length() < 8) {
                    mPassword.setError("Password Must be 8 Characters");
                    mPassword.requestFocus();
                    return;
                }
                // authentication the user
                mPbar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mPbar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            FirebaseUser rUser = mAuth.getCurrentUser();

                            if (task.getResult().getAdditionalUserInfo().isNewUser()) {
                                String email = rUser.getEmail();
                                String userId = rUser.getUid();

                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("userId",userId);
                                hashMap.put("email",email);
                                hashMap.put("firstname","");
                                hashMap.put("lastname","");
                                hashMap.put("phoneNumber","");
                                hashMap.put("date_of_birth","");
                                //hashMap.put("age",age);  //for age
                                hashMap.put("imageURL","default");
                                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference mReference = mDatabase.getReference("Users");
                                mReference.child(userId).setValue(hashMap);
                            }






                            Intent it = new Intent(getApplicationContext(),User_Dashboard.class);
                            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(it);
                            finish();
                        } else {
                            Toast.makeText(loginActivity.this,"Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mPbar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        mGoogleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        // For Forgot Password
        forgetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                final AlertDialog.Builder passwoedResetDialog = new AlertDialog.Builder(view.getContext());
                passwoedResetDialog.setTitle("Reset Password ?");
                passwoedResetDialog.setMessage("Enter Your Email To Receive Reset Link");
                passwoedResetDialog.setView(resetMail);

                passwoedResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // extract the email and send reset link
                        String Mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(Mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(loginActivity.this, "Reset Link Send To Your Email", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(loginActivity.this, "Reset Link Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwoedResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // close the dialog
                    }
                });

                passwoedResetDialog.create().show();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(loginActivity.this, ""+user.getEmail(), Toast.LENGTH_LONG).show();

                            Intent it = new Intent(getApplicationContext(),User_Dashboard.class);
                            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(it);
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(loginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

                            //updateUI(null);
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}