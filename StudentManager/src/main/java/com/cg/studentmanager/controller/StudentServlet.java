package com.cg.studentmanager.controller;

import com.cg.studentmanager.Dao.ClassDao;
import com.cg.studentmanager.Model.ClassStudent;
import com.cg.studentmanager.Model.Student;
import com.cg.studentmanager.StudentService.ClassService;
import com.cg.studentmanager.StudentService.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "StudentServlet", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    private StudentService studentService = new StudentService();
    private ClassService classService = new ClassService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editStudent(req, resp);
                break;
            default:
                showStudent(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(req, resp);
                break;
            case "edit":
                showEditProduct(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            default:
                showStudent(req, resp);
        }
    }
    public void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("studentClass",classService.findAll());
        request.getRequestDispatcher("create.jsp").forward(request,response);
    }
    public void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String gender = request.getParameter("gender");
        int idclass = Integer.parseInt(request.getParameter("studentClassId"));
        ClassStudent classStudent = classService.findById(idclass);
        Student student = new Student(name,dob,gender,classStudent);
        studentService.create(student);
        request.setAttribute("student",student);
        request.getRequestDispatcher("create.jsp").forward(request,response);
    }

    private void editStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        int idclass = Integer.parseInt(req.getParameter("studentClassId"));
        ClassStudent classStudent = classService.findById(idclass);
        Student student = new Student(id, name, Date.valueOf(dob),gender,classStudent);
        studentService.update(student);
        req.setAttribute("student",student);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    private void showEditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        // lấy customer có id bằng với id trong parameter;
        // gửi customer qua bên edit.jsp;
        // điều hướng tới trang edit.jsp;
        Student student = studentService.findById(id);
        req.setAttribute("student", student);
        req.setAttribute("studentClass", classService.findAll());
        req.getRequestDispatcher("edit.jsp")
                .forward(req, resp);
    }

    private void showStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students",studentService.findAll());
        request.getRequestDispatcher("demo.jsp").forward(request,response);
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentService.delete(id);
        showStudent(req, resp);
    }
}
