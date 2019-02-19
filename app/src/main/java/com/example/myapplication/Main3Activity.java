package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;


public class Main3Activity extends AppCompatActivity {
    DBHelper mydb;
    Cursor res;
    EditText editSearch;
    RecyclerView R1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mydb=new DBHelper(this);
        mydb.getWritableDatabase();
        editSearch = (EditText)findViewById(R.id.edit_Search);
        R1 =(RecyclerView) findViewById(R.id.r1);
    }

    public void btn_Search_Click(View view) {
        res=mydb.searchData(editSearch.getText().toString());


    }
}
