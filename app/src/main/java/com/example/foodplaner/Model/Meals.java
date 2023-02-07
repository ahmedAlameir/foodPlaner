package com.example.foodplaner.Model;

import java.util.ArrayList;

public class Meals {
    ArrayList<Meal> Meals;

    public ArrayList<Meal> getMeals() {
        return Meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        Meals = meals;
    }

    public Meals(ArrayList<Meal> meals) {
        Meals = meals;
    }
    public Meals(){}
}
