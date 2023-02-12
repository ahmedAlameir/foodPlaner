package com.example.foodplaner.Network;

import com.example.foodplaner.Model.Meal;

import java.util.ArrayList;

public interface NetworkDelegate {
    void getData(ArrayList<Meal> meals);

}
