package com.example.foodplaner.Network;

import com.example.foodplaner.Model.Categories;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.Meals;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    @GET("search.php?s")
    public Single<Meals> getMeals();
    @GET("search.php")
    public Single<Meals> getMeals(@Query("f") String letter);


    @GET("random.php")
    Observable<Meals> getRandomMeal();

    @GET("categories.php")
    Observable<Categories> getCategories();
    @GET("list.php")
    Observable<Meals> getCategoriesList(@Query("c")String q);
    @GET("list.php")
    Observable<Meals> getAreaList(@Query("a")String q);
    @GET("list.php")
    Observable<Meals> getIngredientList(@Query("i")String q);
    @GET("filter.php")
    Observable<Meals> getCategoriesFiltered(@Query("c")String q);
    @GET("filter.php")
    Observable<Meals> getAreaFiltered(@Query("a")String q);
    @GET("filter.php")
    Observable<Meals> getIngredientFiltered(@Query("i")String q);
    @GET("lookup.php")
    Observable<Meals> getMeal(@Query("i")String q);

}
