package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {
    private var mydb: DBHelper? = null
    var editID: EditText? = null
    var editFirstName: EditText? = null
    var editLastName: EditText? = null
    var editGr: EditText? = null
    var buttonInsert: Button? = null
    var buttonNext: Button? = null
    var buttonShow: Button? = null
    var buttonDelete: Button? = null
    var res: Cursor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mydb = DBHelper(this)
        mydb!!.writableDatabase
        editID = findViewById(R.id.edit_ID)
        editFirstName = findViewById(R.id.edit_first_name)
        editLastName = findViewById(R.id.edit_last_name)
        editGr = findViewById(R.id.edit_group)
        buttonNext = findViewById(R.id.btn_Next)
        buttonShow = findViewById(R.id.btn_Show)
        buttonDelete = findViewById(R.id.btn_Delete)
        buttonInsert = findViewById(R.id.btn_Insert)
        res = mydb!!.allData
//        res.moveToFirst()
    }

    //Создаем общее меню
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    //Обработка нажатия меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_clear -> {
                btn_Erase(null)
                true
            }
            R.id.navigation_find -> {
                btn_Search_Click1(null)
                true
            }
            R.id.navigation_exit -> {
                //Закрыть приложение(работает с API 16)
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Перемещение по записям, если запись последняя, то подгружаем данные и перемещаемся на первую
    fun btn_Next_Click(view: View?) {
        if (res!!.count == 0 || res == null) { // show message
            showMessage(getString(R.string.Error), getString(R.string.NotFound))
            return
        }
        if (res!!.isLast) {
            res = mydb!!.allData
//            res.moveToFirst()
        } else res!!.moveToNext()
        //Вставляем данные записи в текстовые поля на активности
        editID!!.setText(res!!.getString(0))
        editFirstName!!.setText(res!!.getString(1))
        editLastName!!.setText(res!!.getString(2))
        editGr!!.setText(res!!.getString(3))
    }

    //Вставка новой записи
    fun btnInsertClick(view: View?) {
        val isInserted = mydb!!.insertData(editFirstName!!.text.toString(), editLastName!!.text.toString(), editGr!!.text.toString())
        if (isInserted) Toast.makeText(this, getString(R.string.SaveOk), Toast.LENGTH_SHORT).show() else Toast.makeText(this, getString(R.string.ErrorSave), Toast.LENGTH_SHORT).show()
        res = mydb!!.allData
    }

    //Показываем все записи с помощью всплывающего сообщения, функция реализующая сообщения ниже
    fun btn_Show_Click(view: View?) {
        val res = mydb!!.allData
        if (res.count == 0) { // show message
            showMessage(getString(R.string.Error), getString(R.string.NotFound))
            return
        }
        val buffer = StringBuilder()
        while (res.moveToNext()) {
            buffer.append("ИД :").append(res.getString(0)).append("\n")
            buffer.append("Имя :").append(res.getString(1)).append("\n")
            buffer.append("Фамилия :").append(res.getString(2)).append("\n")
            buffer.append("Группа :").append(res.getString(3)).append("\n\n")
        }
        // Show all data
        showMessage("Data", buffer.toString())
    }

    //Кнопка удалить запись
    fun btn_Delete_Click(view: View?) {
        val isDeleted = mydb!!.deleteRow(res!!.getInt(0))
        if (isDeleted) Toast.makeText(this, getString(R.string.SaveOk), Toast.LENGTH_SHORT).show() else Toast.makeText(this, getString(R.string.ErrorSave), Toast.LENGTH_SHORT).show()
    }

    //Реализация всплывающего сообщения
    fun showMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    fun btn_Erase(view: View?) {
        mydb!!.eraseData()
    }

    //Кнопка поиск открывает активность с фукциями поиска
    fun btn_Search_Click1(view: View?) {
        val intent = Intent(this@Main2Activity, Main3Activity::class.java)
        startActivity(intent)
    }
}