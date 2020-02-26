package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//Создаем класс из хелпера сикуэльлайт
class DBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // Создаем таблицы нашей базы
    override fun onCreate(db: SQLiteDatabase) {
        val scriptDB = "CREATE TABLE students (ID integer primary key autoincrement not null,first_name text, last_name text, gr text)"
        db.execSQL(scriptDB)
    }

    //если обновилась структура базы, уничтожаем ее и создаем заново
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $DB_Table")
        onCreate(db)
    }

    //типа метод для заполнения
    fun insertData(first_name: String?, last_name: String?, gr: String?): Boolean { //открываем на запись
        val db = this.writableDatabase
        //создаем штуку для вставки
        val values = ContentValues()
        //вставлям поля
        values.put(C_first_name, first_name)
        values.put(C_last_name, last_name)
        values.put(C_group, gr)
        //записываем таблицу
        val result = db.insert(DB_Table, null, values)
        return if (result == -1L) false else true
    }

    //возвращает всю таблицу
    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("select * from $DB_Table", null)
        }

    //Очистить таблицу
    fun eraseData(): Boolean {
        val db = this.writableDatabase
        db.delete(DB_Table, null, null)
        db.execSQL("update sqlite_sequence set seq=0 WHERE Name='$DB_Table'")
        return true
    }

    //Метод для удаления строки
    fun deleteRow(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(DB_Table, "$C_ID=$id", null).toLong()
        return if (result == -1L) false else true
    }

    //Метод для поиска по весем полям
    fun searchData(search: String?): Cursor {
        val res: Cursor
        res = if (search == null) {
            val db = this.writableDatabase
            db.rawQuery("select * from $DB_Table", null)
        } else {
            val db = this.writableDatabase
            //типа условие поиска
            val selection = "$C_first_name like ? or $C_last_name like ?"
            //типа что ищем
            val selectionArgs = arrayOf("%$search%", "%$search%")
            //Ищем методом query
            db.query(DB_Table, null, selection, selectionArgs, null, null, null, null)
        }
        return res
    }

    companion object {
        private const val TAG = "SQLite"
        // Версия базы
        private const val DB_VERSION = 2
        // Название базы
        private const val DB_NAME = "MY_DB.db"
        // Таблица и ее столбцы
        private const val DB_Table = "students"
        private const val C_ID = "ID"
        private const val C_first_name = "first_name"
        private const val C_last_name = "last_name"
        private const val C_group = "gr"
    }
}