package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Создаем класс из хелпера сикуэльлайт

public class DBHelper extends SQLiteOpenHelper{
    private static final String TAG = "SQLite";

    // Версия базы
    private static final int DATABASE_VERSION = 1;

    // Название базы
    private static final String DATABASE_NAME = "MY_DB.db";

    // Таблица и ее столбцы
    private static final String TABLE_MY = "MY_Table";
    private static final String COLUMN_ID ="MY_Id";
    private static final String COLUMN_TITLE ="MY_Title";
    private static final String COLUMN_TEXT = "MY_Text";

    public DBHelper(Context context){
        /* супер-инициализирует конструктор класса хелпера от которого мы наследуемся */
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    // Создаем таблицы нашей базы
    public void onCreate(SQLiteDatabase db) {
        String scriptDB ="CREATE TABLE "+TABLE_MY+"("+COLUMN_ID+" INTEGER PRIMARY_KEY "+COLUMN_TITLE+" TEXT "+COLUMN_TEXT+" TEXT "+")";
        db.execSQL(scriptDB);
    }

    @Override
    //если обновилась структура базы, уничтожаем ее и создаем заново
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY);
        onCreate(db);
    }
    //типа метод для заполним базу чуток
    public void initMY_DB(){
        //открываем на запись
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //вставлям поля
        values.put("MY_Title","Пример1");
        values.put("MY_Text","Типа текст 1");
        //записываем таблицу
        db.insert("MY_Table",null,values);
        db.close();
    }
}
