package com.cg.studentmanager.Model;

public class ClassStudent {
    private int idClass;
    private String classname;

    public ClassStudent() {
    }

    public ClassStudent(int idClass, String classname) {
        this.idClass = idClass;
        this.classname = classname;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
