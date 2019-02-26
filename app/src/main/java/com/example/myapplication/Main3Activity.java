package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class Main3Activity extends AppCompatActivity {
    DBHelper mydb;
    EditText editSearch;
    RecyclerView R1;
    Adapter mAdapter;
    MyDataModel mydata;
    ArrayList<MyDataModel> AllData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mydb=new DBHelper(this);
        mydb.getWritableDatabase();
        editSearch = findViewById(R.id.edit_Search);
        //Экземпляр нашей модели данных, служит для первой строки с заголовками таблицы
        mydata= new MyDataModel("ID","Имя","Фамилия","Группа");
        //Создаем массив наших данных
        AllData = new ArrayList<>();
        AllData.add(mydata);
        //Делаем запрос, чтобы вывести все записи на активность
        Cursor res =mydb.getAllData();
        while (res.moveToNext()) {
            AllData.add(new MyDataModel(res.getString(0), res.getString(1), res.getString(2), res.getString(3)));
        }
        //Добавляем ReciclerLayout
        R1 = findViewById(R.id.r1);
        R1.setLayoutManager(new LinearLayoutManager(this));
        // Адаптер для вывода данных в RecyclerLayout
        mAdapter= new Adapter(this, AllData);
        R1.setAdapter(mAdapter);

    }

    public void btn_Search_Click(View view) {
        //Поиск по базе
        Cursor res=mydb.searchData(editSearch.getText().toString());
        AllData.clear();
        AllData.add(mydata);
        if(res.getCount() == 0) {
            // show message
            Toast.makeText(this, R.string.NotFound, Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()) {

            AllData.add(new MyDataModel(res.getString(0),res.getString(1),res.getString(2),res.getString(3)));
            //обновляем данные на экране
            mAdapter.notifyDataSetChanged();

        }



    }
}
