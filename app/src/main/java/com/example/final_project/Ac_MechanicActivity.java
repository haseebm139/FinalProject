package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Ac_MechanicActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac__mechanic);

        initData();
        initRecyclerView();
    }



    private void initData() {

        userList = new ArrayList<>();
        userList.add(new ModelClass("Muhammad Faizan","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.images));
        userList.add(new ModelClass("Hamza Khan","Kotri","---------------------------------------------------------------------------------------------------",R.drawable.images1));
        userList.add(new ModelClass("Huzafa Ali","Sehwan","---------------------------------------------------------------------------------------------------",R.drawable.images2));
        userList.add(new ModelClass("Imdad Ali","Dadu","---------------------------------------------------------------------------------------------------",R.drawable.images3));
        userList.add(new ModelClass("Asghar Ali","Hyderabad","---------------------------------------------------------------------------------------------------",R.drawable.images4));
        userList.add(new ModelClass("Fayaz Khan","Thano Bola Khan","---------------------------------------------------------------------------------------------------",R.drawable.images5));
        userList.add(new ModelClass("Asad Jamali","Bhan","---------------------------------------------------------------------------------------------------",R.drawable.images6));
        userList.add(new ModelClass("Razaque ","Kotri","---------------------------------------------------------------------------------------------------",R.drawable.images7));
        userList.add(new ModelClass("Faizan Ahmed","Hyderabad","---------------------------------------------------------------------------------------------------",R.drawable.images8));
        userList.add(new ModelClass("Madad Ali","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.images9));
        userList.add(new ModelClass("Hyder Khan","Dadu","---------------------------------------------------------------------------------------------------",R.drawable.images10));
        userList.add(new ModelClass("Hamza Ali","Kotri","---------------------------------------------------------------------------------------------------",R.drawable.images11));
    }


    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.acMechRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}