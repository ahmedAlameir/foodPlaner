package com.example.foodplaner.List.screen;

import android.content.Context;
import android.widget.Switch;

import com.example.foodplaner.List.adapter.ListClickListener;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;
import com.example.foodplaner.database.MealLocalSource;
import com.example.foodplaner.rebo.Repository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ListPresenter {
    
    private ListInterface listInterface;
    private Repository repo;
    private ChosenFilter chosenFilter;
    MealLocalSource localSource;
    private Context context;
    private ListClickListener listener;
    private ArrayList<String> chipName;
    public ListPresenter(ListInterface listInterface, Repository repository, Context context){
        this.listInterface=listInterface;
        localSource = MealLocalSource.getInstance(context);
        repo = repository;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setOnListClickListener(ListClickListener listener) {
        this.listener = listener;
    }

    public void onListClick(Meal meal) {
        if (listener != null) {
            repo.getGetMeal(meal.idMeal,detailMeal ->{
                listener.onItemClick(detailMeal.getMeals().get(0));

            });
        }
    }

    public void fetchCategory() {
        chosenFilter = ChosenFilter.Category;
        repo.getCategoryList(meals -> {
            chipName = new ArrayList<>();
            for (Meal meal:meals.getMeals()) {
                chipName.add(meal.getStrCategory());
            }
            listInterface.setChipData(chipName);
        });
    }

    public void fetchArea() {
        chosenFilter = ChosenFilter.Area;

        repo.getAreaList(meals -> {
            chipName = new ArrayList<>();
            for (Meal meal:meals.getMeals()) {
                chipName.add(meal.getStrArea());
            }
            listInterface.setChipData(chipName);
        });
    }

    public void fetchIngredient() {
        chosenFilter = ChosenFilter.Ingredient;

        repo.getIngredientList(meals -> {
            chipName = new ArrayList<>();
            for (Meal meal:meals.getMeals()) {
                chipName.add(meal.getStrIngredient());
            }
            listInterface.setChipData(chipName);
        });
    }

    public void fetchData(String s) {
        switch(chosenFilter) {
            case Category:
                repo.getCategoryFiltered(s,meals->{
                    listInterface.setMealData(meals.getMeals());
                });
                break;
            case Area:
                repo.getAreaFiltered(s,meals->{
                    listInterface.setMealData(meals.getMeals());
                });                break;
            case Ingredient:
                repo.getIngredientFiltered(s,meals->{
                    listInterface.setMealData(meals.getMeals());
                });                break;
        }
    }

    public void saveData(Meal item) {
        repo.getGetMeal(item.idMeal,meals -> {
            localSource.insertMeal(new User(FirebaseAuth.getInstance().getUid(), meals.getMeals().get(0)));

        });
    }
}
enum ChosenFilter{
    Category,
    Area,
    Ingredient
}
