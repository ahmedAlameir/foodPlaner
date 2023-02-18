package com.example.foodplaner.MyPlan.Presenter;

import android.util.Log;

import com.example.foodplaner.Model.PlanMeal;
import com.example.foodplaner.MyPlan.View.MyPlanViewInterface;
import com.example.foodplaner.Network.RepositoryInterface;

public class MyPlanPresenter implements MyPlanPresenterInterface{
    private RepositoryInterface _repo;
    private MyPlanViewInterface _view;
    public MyPlanPresenter(MyPlanViewInterface view,RepositoryInterface repo){
        _view=view;
        _repo=repo;
    }


    @Override
    public void getMeal() {
        _view.showData(_repo.getStoredMeals());

    }

    @Override
    public void deleteFromPlan(PlanMeal meal) {
        _repo.deleteMeal(meal);

    }





}
