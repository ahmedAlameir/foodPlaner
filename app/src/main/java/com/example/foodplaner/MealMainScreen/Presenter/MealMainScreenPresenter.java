package com.example.foodplaner.MealMainScreen.Presenter;



import com.example.foodplaner.MealMainScreen.View.MealMainScreenViewInterface;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.PlanMeal;
import com.example.foodplaner.rebo.RepositoryInterface;

import io.reactivex.rxjava3.core.Completable;

public class MealMainScreenPresenter implements MealMainScreenPresenterInterface{
    private MealMainScreenViewInterface _view;
    private RepositoryInterface _repo;
    public MealMainScreenPresenter(MealMainScreenViewInterface view, RepositoryInterface repo){
        _view=view;
        _repo=repo;
    }

    @Override
    public void addToFav(Meal meal) {


    }

    @Override
    public Completable addToPlan(PlanMeal meal) {
        return _repo.insertMeal(meal);
    }
}
