package com.example.sqlite.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite.entity.Students;

public class DBUtil extends SQLiteOpenHelper {
     public DBUtil(Context context){
         super(context,"students.db",null,3);
     }
    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(Students.students_inform);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("drop table if exists students_inform");
         onCreate(db);

    }
}
