package com.example.sqlite.service;

import com.example.sqlite.entity.Students;

import java.util.List;

public interface StudentsService {

    List<Students> getAllStudents();
    //增加
    void insert(Students students);
    void modifyStu(Students students);
    void delete(String students_name);
}
