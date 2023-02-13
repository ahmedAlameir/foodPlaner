package com.example.foodplaner.AllMeals.Presenter;

import com.example.foodplaner.Model.Meal;

public interface AllMealPresenterInterface {
    public void getMeals(String l);
    public void addToFav(Meal meal);
    public void addToPlan(Meal meal);
}
