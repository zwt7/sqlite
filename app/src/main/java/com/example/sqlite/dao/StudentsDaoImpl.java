package com.example.sqlite.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite.entity.Students;
import com.example.sqlite.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentsDaoImpl implements StudentsDao{
    private DBUtil helper;
    private SQLiteDatabase db;

    public StudentsDaoImpl(Context context){
        helper=new DBUtil(context);
    }

    @Override
    public List<Students> selectAllStu() {
        List<Students> students=null;
        db=helper.getReadableDatabase();
        String sql="select *from students_inform";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null&&cursor.getCount()>0){
            students=new ArrayList<>();
            while(cursor.moveToNext()){
                Students students1=new Students();
                students1.setStudents_id(cursor.getInt(cursor.getColumnIndex("students_id")));
                students1.setStudents_name(cursor.getString(cursor.getColumnIndex("students_name")));
                students1.setStudents_class(cursor.getString(cursor.getColumnIndex("students_class")));
                students1.setStudents_age(cursor.getInt(cursor.getColumnIndex("students_age")));

                students.add(students1);
            }
            cursor.close();

        }
        db.close();
        return students;
    }

    @Override
    public void insert(Students students) {
        db=helper.getReadableDatabase();
        String sql="insert into students_inform values(null,?,?,?)";
        db.execSQL(sql,new Object[]{
            students.getStudents_name(),
            students.getStudents_class(),
            students.getStudents_age()});
            db.close();
    }

    @Override
    public void update(Students students) {
        db=helper.getWritableDatabase();
        String sql="update students_inform set students_name=? where students_class=?";
        db.execSQL(sql,new Object[]{
           students.getStudents_name(),
           students.getStudents_class()});

        }


    @Override
    public void delete(String students_name) {
        db=helper.getWritableDatabase();
        String sql="delete from stuents_inform where students_name=?";
        db.execSQL(sql,new Object[]{students_name});

    }
}
