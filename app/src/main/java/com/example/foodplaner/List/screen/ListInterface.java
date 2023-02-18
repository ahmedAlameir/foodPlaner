package com.example.foodplaner.List.screen;

import com.example.foodplaner.Model.Meal;

import java.util.ArrayList;

public interface ListInterface {
    public void setChipData(ArrayList<String> chipNames);
    public void setMealData(ArrayList<Meal> meals);
}
