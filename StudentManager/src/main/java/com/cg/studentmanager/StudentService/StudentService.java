package com.cg.studentmanager.StudentService;

import com.cg.studentmanager.Dao.StudentDao;
import com.cg.studentmanager.Model.Student;

import java.util.List;

public class StudentService {

    private StudentDao studentDao = new StudentDao();
    public List<Student> findAll() {
        //call xuong database => parse database tra ve thanh list object
        return studentDao.findAll();
    }
    public void create(Student student){
        studentDao.insertUser(student);
    }
    public void update(Student student){
       studentDao.updateUser(student);
    }

    public Student findById(int id) {
        return studentDao.findById(id);
    }

    public void delete(int id){
         studentDao.deleteUser(id);
    }
}
