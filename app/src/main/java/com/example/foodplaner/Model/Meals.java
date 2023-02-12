package com.example.foodplaner.Model;

import java.util.ArrayList;

public class Meals {
    ArrayList<Meal> meals;

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> _meals) {
        meals = _meals;
    }

    public Meals(ArrayList<Meal> _meals) {
        meals = _meals;
    }
    public Meals(){}
}
