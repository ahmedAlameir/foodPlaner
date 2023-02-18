package com.example.foodplaner.AllMeals.Presenter;

import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.PlanMeal;

import io.reactivex.rxjava3.core.Completable;

public interface AllMealPresenterInterface {
    public void getMeals(String l);
    public void addToFav(Meal meal);
    public Completable addToPlan(PlanMeal meal);
}
