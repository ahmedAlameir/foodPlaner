package com.example.foodplaner.Home.screen;

import com.example.foodplaner.Home.adaptor.home_category.CategoryClickListener;

public class HomePresenter {
    private CategoryClickListener listener;

    public void setOnCategoryClickListener(CategoryClickListener listener) {
        this.listener = listener;
    }

    public void onCategoryClick(String Category) {
        if (listener != null) {
            listener.onItemClick(Category);
        }
    }
}
