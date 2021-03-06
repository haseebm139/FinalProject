package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ElectritianActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electritian);

        initData();
        initRecyclerView();
    }

    private void initData() {

        userList = new ArrayList<>();
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
        userList.add(new ModelClass("ABC","Jamshoro","---------------------------------------------------------------------------------------------------",R.drawable.ic_person));
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.electriRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}