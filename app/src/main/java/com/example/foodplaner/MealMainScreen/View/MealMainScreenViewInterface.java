package com.example.foodplaner.MealMainScreen.View;

import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.PlanMeal;

import io.reactivex.rxjava3.core.Completable;

public interface MealMainScreenViewInterface {
    public Completable addToPlan(PlanMeal meal);
    public void addToFav(Meal meal);
}
