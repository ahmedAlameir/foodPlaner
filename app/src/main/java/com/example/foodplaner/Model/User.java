package com.example.foodplaner.Model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName="user",primaryKeys = {"userName", "mealId"})
public class User {
    @NonNull
    private String userName;
    @NonNull
    private String mealId;
    @Embedded
    private Meal meal;

    public User(String userName, Meal meal) {
        this.userName = userName;
        this.meal = meal;
        this.mealId = meal.idMeal;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Meal getMeal() {
        return meal;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
        this.mealId = meal.idMeal;
    }
    public void setMealId(String mealId)
    {
        this.mealId = mealId;
    }
}
