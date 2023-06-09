package com.example.bookmanager.Model;

import java.sql.Date;

public class Book {
    private int id;
    private String name;
    private Date publicDate;
    private String author;
    private CategoryBook Category;

    public Book() {
    }

    public Book(int id, String name, Date publicDate, String author, CategoryBook category) {
        this.id = id;
        this.name = name;
        this.publicDate = publicDate;
        this.author = author;
        Category = category;
    }

    public Book(String name, Date publicDate, String author, CategoryBook category) {
        this.name = name;
        this.publicDate = publicDate;
        this.author = author;
        Category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CategoryBook getCategory() {
        return Category;
    }

    public void setCategory(CategoryBook category) {
        Category = category;
    }
}
