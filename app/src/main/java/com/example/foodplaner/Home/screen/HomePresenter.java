package com.example.foodplaner.Home.screen;

import com.example.foodplaner.Home.adaptor.home_category.CategoryClickListener;
import com.example.foodplaner.Home.adaptor.home_selection.SelectionClickListener;

public class HomePresenter {
    private CategoryClickListener categoryListener;
    private SelectionClickListener selectionListener;

    public void setOnCategoryClickListener(CategoryClickListener listener) {
        this.categoryListener = listener;
    }

    public void onCategoryClick(String Category) {
        if (categoryListener != null) {
            categoryListener.onItemClick(Category);
        }
    }
    public void setOnSelectionClickListener(SelectionClickListener listener) {
        this.selectionListener = listener;
    }

    public void onSelectionClick(String meal) {
        if (selectionListener != null) {
            selectionListener.onItemClick(meal);
        }
    }

}
