package com.cg.studentmanager.Model;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private Date dob;
    private String gender;
    private ClassStudent classStudent;

    public Student() {
    }

    public Student(String name, Date dob, String gender, ClassStudent classStudent) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.classStudent = classStudent;
    }

    public Student(int id, String name, Date dob, String gender, ClassStudent classStudent) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.classStudent = classStudent;
    }

    public Student(int id, String name, Date dob, String gender) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.classStudent = classStudent;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ClassStudent getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(ClassStudent classStudent) {
        this.classStudent = classStudent;
    }
}
