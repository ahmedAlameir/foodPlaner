package com.example.foodplaner.AllMeals.Presenter;

import com.example.foodplaner.AllMeals.View.AllMealViewInterface;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.PlanMeal;
import com.example.foodplaner.Network.NetworkDelegate;
import com.example.foodplaner.rebo.RepositoryInterface;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Completable;

public class AllMealsPresenter implements AllMealPresenterInterface, NetworkDelegate {
    private AllMealViewInterface _view;
    private RepositoryInterface _repo;

    public AllMealsPresenter(AllMealViewInterface view,RepositoryInterface repo){
        _view=view;
        _repo=repo;
    }

    @Override
    public void getMeals(String l) {
        _repo.getAllMeals(this,l);


    }

    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public Completable addToPlan(PlanMeal meal) {
        return _repo.insertMeal(meal);

    }


    @Override
    public void getData(ArrayList<Meal> meals) {
        _view.showData(meals);

    }
}
