package com.example.foodplaner.Model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PlanMeals")
public class PlanMeal {
    public PlanMeal() {
    }

    @PrimaryKey
    @NonNull
    public String mealId;
    @Embedded
    private  Meal meal;
    public String day;

    public PlanMeal(Meal meal,String day){
        this.meal=meal;
        this.mealId=meal.getIdMeal();
        this.day=day;
    }

    @NonNull
    public String getIdMeal() {
        return mealId;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.mealId = idMeal;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}
