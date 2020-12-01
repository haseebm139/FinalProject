package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Dashboard extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private TextView textName, nav_name, nav_email, nav_number, fname, lname, mFname;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USERS = "Users";
    private String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__dashboard);
        //receive data from login screen
            Intent intent = getIntent();
            email = intent.getStringExtra(email);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);

        Log.v("EMAILADD", userRef.orderByChild("email").equalTo(email).toString());

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation);
        drawer = findViewById(R.id.drawer);
        textName = findViewById(R.id.textName);
        nav_name = findViewById(R.id.nav_name);
        nav_email = findViewById(R.id.nav_email);
        nav_number = findViewById(R.id.nav_number);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)) {
                        textName.setText(ds.child("firstName").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();





        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                switch (item.getItemId()){
                    case R.id.login:
                        Toast.makeText(User_Dashboard.this,"Login", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contectUS:
                        Toast.makeText(User_Dashboard.this,"ContectUs", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });


       /* View view = navigationView.getHeaderView(0);
        TextView email = findViewById(R.id.email);
        TextView name = findViewById(R.id.name);
        TextView number = findViewById(R.id.numb);

        name.setText("Haseeb Memon");
        email.setText("haseebm139@gmail.com");
        number.setText("+923322671412");
*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(User_Dashboard.this,loginActivity.class));
           /* case R.id.changePassword:
                startActivity(new Intent(this,ForgetPassActivity.class));*/
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}