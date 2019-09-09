package com.example.sqlite.entity;

import java.io.Serializable;

//建立字段
public class Students implements Serializable {
    public static final String students_inform="create table students_inform("+
        "students_id integer primary key autoincrement ,"+
        "students_name varchar(20) not null,"+
        "students_class varchar(20) not null,"+
        "students_age integer not null )";
     private int students_id;
     private String students_name;
     private String students_class;
     private int students_age;

     //有参
    public Students(int students_id, String students_name, String students_class, int students_age) {
        this.students_id = students_id;
        this.students_name = students_name;
        this.students_class = students_class;
        this.students_age = students_age;
    }
    //无参
    public Students(){

    }

    public int getStudents_id() {
        return students_id;
    }

    public void setStudents_id(int students_id) {
        this.students_id = students_id;
    }

    public String getStudents_name() {
        return students_name;
    }

    public void setStudents_name(String students_name) {
        this.students_name = students_name;
    }

    public String getStudents_class() {
        return students_class;
    }

    public void setStudents_class(String students_class) {
        this.students_class = students_class;
    }

    public int getStudents_age() {
        return students_age;
    }

    public void setStudents_age(int students_age) {
        this.students_age = students_age;
    }

    @Override
    public String toString() {
        return "Students{" +
                "students_id=" + students_id +
                ", students_name='" + students_name + '\'' +
                ", students_class='" + students_class + '\'' +
                ", students_age=" + students_age +
                '}';
    }
}
