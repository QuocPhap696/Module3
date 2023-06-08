package com.cg.studentmanager.StudentService;

import com.cg.studentmanager.Dao.ClassDao;
import com.cg.studentmanager.Dao.StudentDao;
import com.cg.studentmanager.Model.ClassStudent;
import com.cg.studentmanager.Model.Student;

import java.util.List;

public class ClassService {
    private ClassDao classDao = new ClassDao();

    public List<ClassStudent> findAll(){
        return classDao.findAll();
    }

    public ClassStudent findById(int id){

        return classDao.findById(id);
    }

}
