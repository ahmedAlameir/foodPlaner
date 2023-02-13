package com.example.foodplaner.Network;

import com.example.foodplaner.Model.Categories;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.Meals;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface APIInterface {
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    @GET("search.php?s")
    public Single<Meals> getMeals();

    @GET("random.php")
    Observable<Meals> getRandomMeal();

    @GET("categories.php")
    Observable<Categories> getCategories();
}
