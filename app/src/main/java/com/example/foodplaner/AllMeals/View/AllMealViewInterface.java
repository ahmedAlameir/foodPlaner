package com.example.foodplaner.AllMeals.View;

import com.example.foodplaner.Model.Meal;

import java.util.ArrayList;

public interface AllMealViewInterface {
    public void showData(ArrayList<Meal> meals);
    public void addToFav(Meal meal);
    public void addToPlan(Meal meal);
}
