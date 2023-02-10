package com.example.foodplaner.Favourite.screen;

import com.example.foodplaner.Favourite.adaptor.FavouriteClickListener;
import com.example.foodplaner.Home.adaptor.home_category.CategoryClickListener;
import com.example.foodplaner.Home.adaptor.home_selection.SelectionClickListener;

public class FavouritePresenter {

    private FavouriteClickListener listener;

    public void setOnCategoryClickListener(FavouriteClickListener listener) {
        this.listener = listener;
    }

    public void onMealClick(String meal) {
        if (listener != null) {
            listener.onItemClick(meal);
        }
    }
}
