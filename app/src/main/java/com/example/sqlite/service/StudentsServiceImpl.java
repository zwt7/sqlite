package com.example.sqlite.service;

import android.content.Context;

import com.example.sqlite.dao.StudentsDao;
import com.example.sqlite.entity.Students;
import com.example.sqlite.dao.StudentsDaoImpl;

import java.util.List;

public class StudentsServiceImpl implements StudentsService{

    private StudentsDao studentsDao;

    public StudentsServiceImpl(Context context){
        studentsDao=new StudentsDaoImpl(context);
    }

    @Override
    public List<Students> getAllStudents() {
        return studentsDao.selectAllStu();
    }

    @Override
    public void insert(Students students) {
        studentsDao.insert(students);

    }

    @Override
    public void modifyStu(Students students) {
        studentsDao.update(students);

    }

    @Override
    public void delete(String students_name) {
        studentsDao.delete(students_name);

    }
}
