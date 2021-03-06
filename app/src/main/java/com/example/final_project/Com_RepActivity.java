package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Com_RepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com__rep);

        initData();
        initRecyclerView();
    }
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;





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
        recyclerView = (RecyclerView) findViewById(R.id.comRepRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}