package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {


    private DBHelper mydb;
    EditText editID,editFirstName,editLastName,editGr;
    Button buttonInsert,buttonNext,buttonShow,buttonDelete;
    int numR;
    Cursor res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mydb=new DBHelper(this);
        mydb.getWritableDatabase();
        editID=(EditText) findViewById(R.id.edit_ID);
        editFirstName=(EditText) findViewById(R.id.edit_first_name);
        editLastName=(EditText) findViewById(R.id.edit_last_name);
        editGr=(EditText) findViewById(R.id.edit_group);
        buttonNext=(Button) findViewById(R.id.btn_Next);
        buttonShow=(Button) findViewById(R.id.btn_Show);
        buttonDelete=(Button) findViewById(R.id.btn_Delete);
        buttonInsert=(Button) findViewById(R.id.btn_Insert);
        res = mydb.getAllData();
        res.moveToFirst();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation,menu);
        return true;
    }


    public void btn_Next_Click(View view) {

        if(res.getCount() == 0 || res==null){
            // show message
            showMessage(getString(R.string.Error),getString(R.string.NotFound));
            return;
        }
        if (res.isLast()) {
                res = mydb.getAllData();
                res.moveToFirst();
            }
            else
                res.moveToNext();

            editID.setText(res.getString(0));
            editFirstName.setText(res.getString(1));
            editLastName.setText(res.getString(2));
            editGr.setText(res.getString(3));

        }



    public void btnInsertClick(View view) {
        boolean isInserted =mydb.insertData(editFirstName.getText().toString(),editLastName.getText().toString(),editGr.getText().toString());
        if (isInserted == true)
            Toast.makeText(this, getString(R.string.SaveOk), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, getString(R.string.ErrorSave), Toast.LENGTH_SHORT).show();
        res = mydb.getAllData();

    }

    public void btn_Show_Click(View view) {
        Cursor res=mydb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage(getString(R.string.Error),getString(R.string.NotFound));
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
        boolean isDeleted=mydb.deleteRow(res.getInt(0));
        if (isDeleted == true)
            Toast.makeText(this, getString(R.string.SaveOk), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, getString(R.string.ErrorSave), Toast.LENGTH_SHORT).show();


Cursor
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void btn_Erase(View view) {
    mydb.eraseData();
    Cursor res=mydb.getAllData();
    }

    public void btn_Search_Click1(View view) {
        Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(intent);
    }
}
