package com.example.bookmanager.Dao;

import com.example.bookmanager.Model.Book;
import com.example.bookmanager.Model.CategoryBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/bookManager";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "qp055696";
    private final String SELECT_BOOK_BY_ID = "SELECT b.*, c.namecategory FROM Book b left join Category c on b.idcategory = c.idcategory WHERE b.idBook = ?;";
    private final String SELECT_USERS = "SELECT *, c.namecategory FROM Book as b left join Category as c on b.idcategory = c.idcategory";
    private final String INSERT_USERS= " INSERT INTO `bookManager`.`Book` (`name`, `publicDate`, `author`, `idcategory`) VALUES (?,?,?,?);";
    private final String UPDATE_STUDENT = "UPDATE `bookManager`.`Book` SET `name` = ?, `publicDate` = ?, `author` = ?, `idcategory` = ? WHERE (`idBook` = ?);";
    private final String DELETE_BOOK = "DELETE FROM `Book` WHERE (`idBook` = ?);";
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

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
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
                int id = rs.getInt("idBook");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                Date publicDate = Date.valueOf(rs.getString("publicDate"));
                String author = rs.getString("author");
                int idcategory = rs.getInt("idcategory");
                String namecategory = rs.getString("namecategory");
                books.add(new Book(id,name,publicDate,author, new CategoryBook(idcategory,namecategory)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
    public void insertUser(Book book) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setDate(2, book.getPublicDate());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4,book.getCategory().getIdcategory());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateUser(Book book) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setDate(2, book.getPublicDate());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getCategory().getIdcategory());
            preparedStatement.setInt(5,book.getId());
//                preparedStatement.setString(5, book.getCategory().getNamecategory());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Book findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_BOOK_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                //(truyên vào tên cột)
                int idBook = rs.getInt("idBook");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                Date publicDate = Date.valueOf(rs.getString("publicDate"));
                String author = rs.getString("author");
                int idcategory = rs.getInt("idcategory");
                String namecategory= rs.getString("namecategory");
                return new Book(idBook, name, publicDate,author, new CategoryBook(idcategory, namecategory));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteBook(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
