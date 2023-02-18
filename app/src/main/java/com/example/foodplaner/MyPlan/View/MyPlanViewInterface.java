package com.example.foodplaner.MyPlan.View;

import com.example.foodplaner.Model.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MyPlanViewInterface {
    public void showData(Single<List<PlanMeal>> meals);
    public void deleteFromPlan(PlanMeal meal);

}
