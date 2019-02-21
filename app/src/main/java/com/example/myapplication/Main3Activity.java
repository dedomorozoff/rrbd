package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class Main3Activity extends AppCompatActivity {
    DBHelper mydb;
    Cursor res;
    EditText editSearch;
    RecyclerView R1;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mydb=new DBHelper(this);
        mydb.getWritableDatabase();
        editSearch = (EditText)findViewById(R.id.edit_Search);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        R1 = findViewById(R.id.r1);
        R1.setLayoutManager(new LinearLayoutManager(this));
        mAdapter= new MyRecyclerViewAdapter(this, animalNames);
        //mAdapter.setClickListener(this);
        R1.setAdapter(mAdapter);

    }

    public void btn_Search_Click(View view) {





    }
}
