package com.example.bookmanager.Dao;

import com.example.bookmanager.Model.CategoryBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/bookManager";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "qp055696";

    private final String SELECT_CATEGORY = "SELECT * FROM CATEGORY;";
    private final String SELECT_CATEGORY_BY_ID = "SELECT * FROM CATEGORY WHERE idcategory = ?";

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

    public List<CategoryBook> findAll(){
        List<CategoryBook> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CATEGORY)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int idcategory = rs.getInt("idcategory");
                String namecategory = rs.getString("namecategory");
                categories.add(new CategoryBook(idcategory, namecategory));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public CategoryBook findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CATEGORY_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                int idcategory = rs.getInt("idcategory");
                String namecategory = rs.getString("namecategory");

                return new CategoryBook(idcategory, namecategory);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
