package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {


    private DBHelper mydb;
    EditText editID,editFirstName,editLastName,editGr;
    Button buttonInsert,buttonNext,buttonShow,buttonDelete;
    Cursor res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mydb=new DBHelper(this);
        mydb.getWritableDatabase();
        editID= findViewById(R.id.edit_ID);
        editFirstName= findViewById(R.id.edit_first_name);
        editLastName= findViewById(R.id.edit_last_name);
        editGr= findViewById(R.id.edit_group);
        buttonNext= findViewById(R.id.btn_Next);
        buttonShow= findViewById(R.id.btn_Show);
        buttonDelete= findViewById(R.id.btn_Delete);
        buttonInsert= findViewById(R.id.btn_Insert);
        res = mydb.getAllData();
        res.moveToFirst();


    }
//Создаем общее меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation,menu);
        return true;
    }
//Обработка нажатия меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.navigation_clear:
                btn_Erase(null);
                return true;
            case R.id.navigation_find:
                btn_Search_Click1(null);
                return true;
            case R.id.navigation_exit:
                //Закрыть приложение(работает с API 16)
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Перемещение по записям, если запись последняя, то подгружаем данные и перемещаемся на первую
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
//Вставляем данные записи в текстовые поля на активности
            editID.setText(res.getString(0));
            editFirstName.setText(res.getString(1));
            editLastName.setText(res.getString(2));
            editGr.setText(res.getString(3));

        }

//Вставка новой записи

    public void btnInsertClick(View view) {
        boolean isInserted =mydb.insertData(editFirstName.getText().toString(),editLastName.getText().toString(),editGr.getText().toString());
        if (isInserted)
            Toast.makeText(this, getString(R.string.SaveOk), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, getString(R.string.ErrorSave), Toast.LENGTH_SHORT).show();
        res = mydb.getAllData();

    }
//Показываем все записи с помощью всплывающего сообщения, функция реализующая сообщения ниже
    public void btn_Show_Click(View view) {
        Cursor res=mydb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage(getString(R.string.Error),getString(R.string.NotFound));
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("ИД :").append(res.getString(0)).append("\n");
            buffer.append("Имя :").append(res.getString(1)).append("\n");
            buffer.append("Фамилия :").append(res.getString(2)).append("\n");
            buffer.append("Группа :").append(res.getString(3)).append("\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }
//Кнопка удалить запись
    public void btn_Delete_Click(View view) {
        boolean isDeleted=mydb.deleteRow(res.getInt(0));
        if (isDeleted)
            Toast.makeText(this, getString(R.string.SaveOk), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, getString(R.string.ErrorSave), Toast.LENGTH_SHORT).show();



    }
//Реализация всплывающего сообщения
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void btn_Erase(View view) {
    mydb.eraseData();
    }
//Кнопка поиск открывает активность с фукциями поиска
    public void btn_Search_Click1(View view) {
        Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(intent);
    }
}
