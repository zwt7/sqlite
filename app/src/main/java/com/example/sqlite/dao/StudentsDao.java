package com.example.sqlite.dao;

import com.example.sqlite.entity.Students;

import java.util.List;

public interface StudentsDao {

    List<Students> selectAllStu();
    //增
    void insert(Students students);
    void update(Students students);
    void delete(String students_name);

}
