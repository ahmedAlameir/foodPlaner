package com.example.foodplaner.Network;

import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.Meals;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    @GET("search.php")
    public Single<Meals> getMeals(@Query("f") String letter);


    @GET("random.php")
    Observable<Meal> getRandomMeal();

}
