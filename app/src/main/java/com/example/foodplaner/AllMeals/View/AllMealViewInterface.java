package com.example.foodplaner.AllMeals.View;

import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.PlanMeal;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Completable;

public interface AllMealViewInterface {
    public void showData(ArrayList<Meal> meals);
    public void addToFav(Meal meal);
    public Completable addToPlan(PlanMeal meal);


}
