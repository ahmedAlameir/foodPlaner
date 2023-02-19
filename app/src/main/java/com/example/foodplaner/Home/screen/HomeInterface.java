package com.example.foodplaner.Home.screen;

import com.example.foodplaner.Model.Categories;
import com.example.foodplaner.Model.Category;
import com.example.foodplaner.Model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface HomeInterface {
    public void setDataInSelectionList(List<Meal> meals);

    void setCategoriesInSelectionList(List<Category> categories);

    void goToMeal(Meal meal);
}
