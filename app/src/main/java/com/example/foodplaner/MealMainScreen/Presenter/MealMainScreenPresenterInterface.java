package com.example.foodplaner.MealMainScreen.Presenter;

import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.PlanMeal;

import io.reactivex.rxjava3.core.Completable;

public interface MealMainScreenPresenterInterface {
    public void addToFav(Meal meal);
    public Completable addToPlan(PlanMeal meal);
}
