package com.example.bookmanager.BookService;

import com.example.bookmanager.Dao.BookDao;
import com.example.bookmanager.Model.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();
    public List<Book> findAll(){
        return bookDao.findAll();
    }
    public void create(Book book){
        bookDao.insertUser(book);
    }
    public void update(Book book){
        bookDao.updateUser(book);
    }

    public Book findById(int id) {
        return bookDao.findById(id);
    }

    public void delete(int id){
        bookDao.deleteBook(id);
    }
}
