package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main3.*
import java.util.*

class Main3Activity : AppCompatActivity() {
    var mydb: DBHelper? = null
    var mAdapter: Adapter? = null
    var AllData= mutableListOf<MyDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        mydb = DBHelper(this)
        mydb!!.writableDatabase
        //Экземпляр нашей модели данных, служит для первой строки с заголовками таблицы
        var mydata = MyDataModel("ID", "Имя", "Фамилия", "Группа")
        //Создаем массив наших данных

        AllData?.add(mydata)
        //Делаем запрос, чтобы вывести все записи на активность
        val res = mydb!!.allData
        while (res.moveToNext()) {
            AllData!!.add(MyDataModel(res.getString(0), res.getString(1), res.getString(2), res.getString(3)))
        }
        //Добавляем ReciclerLayout
        r1.setLayoutManager(LinearLayoutManager(this))
        // Адаптер для вывода данных в RecyclerLayout
        mAdapter = Adapter(AllData)
        r1.setAdapter(mAdapter)
    }

    fun btn_Search_Click(view: View?) { //Поиск по базе
        val res = mydb!!.searchData(edit_Search.text.toString())
        AllData!!.clear()
        var mydata = MyDataModel("ID", "Имя", "Фамилия", "Группа")
        AllData!!.add(mydata)
        if (res.count == 0) { // show message
            Toast.makeText(this, R.string.NotFound, Toast.LENGTH_SHORT).show()
            return
        }
        while (res.moveToNext()) {
            AllData!!.add(MyDataModel(res.getString(0), res.getString(1), res.getString(2), res.getString(3)))
            //обновляем данные на экране
            mAdapter!!.notifyDataSetChanged()
        }
    }
}