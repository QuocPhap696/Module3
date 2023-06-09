package com.example.bookmanager.BookService;

import com.example.bookmanager.Dao.CategoryDao;
import com.example.bookmanager.Model.CategoryBook;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();
    public List<CategoryBook> findAll(){
        return categoryDao.findAll();
    }

    public CategoryBook findById(int id){

        return categoryDao.findById(id);
    }

}
