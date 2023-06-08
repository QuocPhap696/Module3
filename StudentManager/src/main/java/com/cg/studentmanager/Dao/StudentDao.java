package com.cg.studentmanager.Dao;

import com.cg.studentmanager.Model.ClassStudent;
import com.cg.studentmanager.Model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/Student";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "qp055696";

    private final String SELECT_STUDENT_BY_ID = "SELECT s.*, c.classname FROM student s left join class c on s.idclass = c.idclass WHERE s.idstudent = ?;";
    private final String SELECT_USERS ="SELECT s.*, c.classname FROM student s left join class c on s.idclass = c.idclass";
    private final String INSERT_USERS= "INSERT INTO `Student`.`student` (`name`, `dob`, `gender`, `idclass`) VALUES (?, ?, ?, ?);";
    private final String UPDATE_STUDENT = "UPDATE `Student`.`student` SET `name` = ?, `dob` = ?, `gender` = ?, `idclass` = ? WHERE (`idstudent` = ?);";
    private final String DELETE_USER = "DELETE FROM `student` WHERE (`idstudent` = ?);";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        // Step 1: tạo 1 kết nối xuống db để gọi câu lệnh SELECT or UPDATE, Delete, vv
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USERS)) {
            System.out.println(preparedStatement);
            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                //(truyên vào tên cột)
                int idstudent = rs.getInt("idstudent");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                Date dob = Date.valueOf(rs.getString("dob"));
                String gender = rs.getString("gender");
                int idClass = rs.getInt("idClass");
                String classname = rs.getString("classname");
                students.add(new Student(idstudent, name,dob, gender, new ClassStudent(idClass,classname)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }
    public void insertUser(Student student) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, student.getDob());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setInt(4,student.getClassStudent().getIdClass());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(Student student) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, student.getDob());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setInt(4, student.getClassStudent().getIdClass());
            preparedStatement.setInt(5, student.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Student findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_STUDENT_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                //(truyên vào tên cột)
                int idStudent = rs.getInt("idstudent");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                Date dob = Date.valueOf(rs.getString("dob"));
                String gender = rs.getString("gender");
                int idclass = rs.getInt("idclass");
                String classname= rs.getString("classname");
                return new Student(idStudent, name, dob,gender, new ClassStudent(idclass, classname));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteUser(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
