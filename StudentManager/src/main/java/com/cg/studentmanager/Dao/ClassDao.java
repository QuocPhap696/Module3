package com.cg.studentmanager.Dao;

import com.cg.studentmanager.Model.ClassStudent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDao {
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/Student";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "qp055696";

    private final String SELECT_CLASS = "SELECT * FROM CLASS;";
    private final String SELECT_CLASS_BY_ID = "SELECT * FROM class WHERE idclass = ?";


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

    public List<ClassStudent> findAll(){
        List<ClassStudent> roles = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CLASS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int idclass = rs.getInt("idClass");
                String classname = rs.getString("classname");
                roles.add(new ClassStudent(idclass, classname));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return roles;
    }
    public ClassStudent findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CLASS_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                int idclass = rs.getInt("idClass");
                String classname = rs.getString("classname");

                return new ClassStudent(idclass, classname);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
