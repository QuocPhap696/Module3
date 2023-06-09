package com.example.bookmanager.Model;

public class CategoryBook {
    private int idcategory;
    private String namecategory;

    public CategoryBook() {
    }

    public CategoryBook(int idcategory, String namecategory) {
        this.idcategory = idcategory;
        this.namecategory = namecategory;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }
}
