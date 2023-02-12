package com.example.foodplaner.Network;

import androidx.lifecycle.LiveData;

import com.example.foodplaner.Model.Meal;

import java.util.List;

public interface RepositoryInterface {
    public void insertMeal(Meal meal);
    public void deleteMeal(Meal meal);
    public void getAllMeals(NetworkDelegate networkDelegate);
    public LiveData<List<Meal>> getStoredMeals();

}
