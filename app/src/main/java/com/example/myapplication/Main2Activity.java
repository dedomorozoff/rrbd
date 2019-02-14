package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {


    private DBHelper mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mydb=new DBHelper(this);
        mydb.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation,menu);
        return true;
    }


    public void btn_Next_Click(View view) {
    }

    public void btnInsertClick(View view) {
        mydb.insertData("Vasya","Pupkin","IS54");

    }

    public void btn_Show_Click(View view) {
        Cursor res=mydb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("ИД :"+ res.getString(0)+"\n");
            buffer.append("Имя :"+ res.getString(1)+"\n");
            buffer.append("Фамилия :"+ res.getString(2)+"\n");
            buffer.append("Группа :"+ res.getString(3)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }

    public void btn_Delete_Click(View view) {
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
