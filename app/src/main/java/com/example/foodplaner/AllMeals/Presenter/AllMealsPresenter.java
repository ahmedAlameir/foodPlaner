package com.example.foodplaner.AllMeals.Presenter;

import com.example.foodplaner.AllMeals.View.AllMealViewInterface;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Network.NetworkDelegate;
import com.example.foodplaner.rebo.RepositoryInterface;

import java.util.ArrayList;

public class AllMealsPresenter implements AllMealPresenterInterface, NetworkDelegate {
    private AllMealViewInterface _view;
    private RepositoryInterface _repo;

    public AllMealsPresenter(AllMealViewInterface view,RepositoryInterface repo){
        _view=view;
        _repo=repo;
    }

    @Override
    public void getMeals() {
        _repo.getAllMeals(this);


    }

    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public void addToPlan(Meal meal) {

    }

    @Override
    public void getData(ArrayList<Meal> meals) {
        _view.showData(meals);

    }
}