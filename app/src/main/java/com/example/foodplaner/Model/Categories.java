package com.example.foodplaner.Model;

import java.util.ArrayList;

public class Categories {
    private ArrayList<Category>categories;

    public Categories() {
    }

    public Categories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
