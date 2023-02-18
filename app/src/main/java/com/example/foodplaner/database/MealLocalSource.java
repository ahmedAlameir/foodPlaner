package com.example.foodplaner.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;

import java.util.List;

public class MealLocalSource {
    private static MealLocalSource instance;


    private UserDAO userDAO;
    private LiveData<List<User>> meals;
    private MealLocalSource (Context context){
        UserDatabase db=UserDatabase.getInstance(context.getApplicationContext());
        userDAO = db.userDAO();
    }
    public static MealLocalSource getInstance(Context context){
        if(instance ==null){
            instance = new MealLocalSource(context);
        }
        return instance;
    }
    public LiveData<List<User>> getMeals(String userName){ return  userDAO.getAllMeals(userName);}
    public void deleteMeal(User meal){
        new Thread(() -> {
            userDAO.deleteMeals(meal);
        }).start();
    }
    public void insertMeal(User meal){
        new Thread(() -> {
            userDAO.insertMeals(meal);
        }).start();
    }

}
