package com.example.foodplaner.List.screen;

import com.example.foodplaner.Home.adaptor.home_category.CategoryClickListener;
import com.example.foodplaner.Home.adaptor.home_selection.SelectionClickListener;
import com.example.foodplaner.List.adapter.ListClickListener;

public class ListPresenter {
    private ListClickListener listener;

    public void setOnListClickListener(ListClickListener listener) {
        this.listener = listener;
    }

    public void onListClick(String Category) {
        if (listener != null) {
            listener.onItemClick(Category);
        }
    }
}
