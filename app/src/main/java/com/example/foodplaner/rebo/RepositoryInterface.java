package com.example.foodplaner.rebo;


import com.example.foodplaner.Network.NetworkDelegate;
import com.example.foodplaner.Model.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface RepositoryInterface {
    public Completable insertMeal(PlanMeal meal);
    public void deleteMeal(PlanMeal meal);
    public void getAllMeals(NetworkDelegate networkDelegate, String l);
    public Single<List<PlanMeal>> getStoredMeals();

}
