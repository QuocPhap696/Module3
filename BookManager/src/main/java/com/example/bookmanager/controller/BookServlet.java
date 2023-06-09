package com.example.bookmanager.controller;

import com.example.bookmanager.BookService.BookService;
import com.example.bookmanager.BookService.CategoryService;
import com.example.bookmanager.Model.Book;
import com.example.bookmanager.Model.CategoryBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "BookServlet", urlPatterns = "/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createBook(req, resp);
                break;
            case "edit":
                editBook(req, resp);
                break;
            default:
                showBook(req,resp);
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
                showEditBook(req, resp);
                break;
            case "delete":
                deleteBook(req, resp);
                break;
            default:
                showBook(req,resp);
        }
    }
    public void createBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date publicDate = Date.valueOf(request.getParameter("publicDate"));
        String author = request.getParameter("author");
        int idcategory = Integer.parseInt(request.getParameter("categoryID"));
        CategoryBook categoryBook = categoryService.findById(idcategory);
        Book book = new Book(name,publicDate,author,categoryBook);
        bookService.create(book);
        request.setAttribute("book",book);
        request.getRequestDispatcher("create.jsp").forward(request,response);
    }
    public void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categoryBook",categoryService.findAll());
        request.getRequestDispatcher("create.jsp").forward(request,response);
    }
    public void showBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("book",bookService.findAll());
        request.getRequestDispatcher("show.jsp").forward(request,response);
    }

    private void editBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idBook = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Date publicDate = Date.valueOf(req.getParameter("publicDate"));
        String author = req.getParameter("author");
        int idcategory = Integer.parseInt(req.getParameter("categoryBook"));
        CategoryBook categoryBook = categoryService.findById(idcategory);
        Book book = new Book(idBook,name, publicDate,author,categoryBook);
        bookService.update(book);
        req.setAttribute("book",book);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    private void showEditBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        // lấy customer có id bằng với id trong parameter;
        // gửi customer qua bên edit.jsp;
        // điều hướng tới trang edit.jsp;
        Book book = bookService.findById(id);
        req.setAttribute("book", book);
        req.setAttribute("idcategory", categoryService.findAll());
        req.getRequestDispatcher("edit.jsp")
                .forward(req, resp);
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookService.delete(id);
        showBook(req, resp);
    }
}
