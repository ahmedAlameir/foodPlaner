package com.example.foodplaner.Home.screen;

import android.content.Context;
import android.util.Log;

import com.example.foodplaner.Home.adaptor.home_category.CategoryClickListener;
import com.example.foodplaner.Home.adaptor.home_selection.SelectionClickListener;
import com.example.foodplaner.Model.Category;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.rebo.Repository;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {
    private Repository repo;
    private CategoryClickListener categoryListener;
    private Context context;
    private SelectionClickListener selectionListener;
    HomeInterface home;
    private static final String TAG = "HomePresenter";
    public HomePresenter(HomeInterface home, Repository repo,Context context){
        this.home = home;
        this.repo = repo;
        this.context =context;
    }

    public Context getContext() {
        return context;
    }

    public void setOnCategoryClickListener(CategoryClickListener listener) {
        this.categoryListener = listener;
    }
    public void fetchMeals(){
        List<Meal>meals=new ArrayList<>();
        for (int i=0;i<10;i++) {
            repo.getRandomMeal(meal -> {

                meals.addAll(meal.getMeals());
                home.setDataInSelectionList(meals);
            });
        }
    }

    public void onCategoryClick(String Category) {
        if (categoryListener != null) {
            categoryListener.onItemClick(Category);
        }
    }
    public void setOnSelectionClickListener(SelectionClickListener listener) {
        this.selectionListener = listener;
    }

    public void onSelectionClick(Meal meal) {
        if (selectionListener != null) {
            selectionListener.onItemClick(meal);
        }
    }

    public void fetchCategory() {
        repo.geCategories(categories -> {
            home.setCategoriesInSelectionList(categories.getCategories());
        });
    }

    public void sendMealOfTheDay() {
        repo.getGetMeal("52867",meals -> {
            home.goToMeal(meals.getMeals().get(0));
        });
    }
}
