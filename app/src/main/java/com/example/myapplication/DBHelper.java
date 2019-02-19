package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Создаем класс из хелпера сикуэльлайт

public class DBHelper extends SQLiteOpenHelper{
    private static final String TAG = "SQLite";

    // Версия базы
    private static final int DB_VERSION = 2;

    // Название базы
    private static final String DB_NAME = "MY_DB.db";

    // Таблица и ее столбцы
    private static final String DB_Table = "students";
    private static final String C_ID ="ID";
    private static final String C_first_name ="first_name";
    private static final String C_last_name = "last_name";
    private static final String C_group = "gr";

    public DBHelper(Context context){
        /* супер-инициализирует конструктор класса хелпера от которого мы наследуемся */
        super(context,DB_NAME,null,DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    // Создаем таблицы нашей базы
    public void onCreate(SQLiteDatabase db) {
        String scriptDB = "CREATE TABLE students (ID integer primary key autoincrement not null,first_name text, last_name text, gr text)";
        db.execSQL(scriptDB);
    }

    @Override
    //если обновилась структура базы, уничтожаем ее и создаем заново
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_Table);
        onCreate(db);
    }
    //типа метод для заполнения
    public boolean insertData(String first_name, String last_name, String gr){
        //открываем на запись
        SQLiteDatabase db = this.getWritableDatabase();
        //создаем штуку для вставки
        ContentValues values=new ContentValues();
        //вставлям поля
        values.put(C_first_name,first_name);
        values.put(C_last_name,last_name);
        values.put(C_group,gr);

        //записываем таблицу
        long result = db.insert(DB_Table,null ,values);
      if (result == -1) return false;
          else
              return true;
    }
    //возвращает всю таблицу
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+DB_Table,null);
        return res;
    }
    //Очистить таблицу
    public  boolean eraseData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_Table,null,null);
        db.execSQL("update sqlite_sequence set seq=0 WHERE Name='"+DB_Table+"'");
        return true;
    }
    //Метод для удаления строки
    public boolean deleteRow(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result =db.delete(DB_Table,C_ID+"="+id,null);
        if (result == -1) return false;
        else
            return true;
    }
    //Метод для поиска по весем полям
    public Cursor searchData(String search){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+DB_Table+" where "+C_first_name+" like " +search+" or "+ C_last_name+" like "+search+" or "+C_group+" like "+search,null);
        return res;

    }


}
