package com.example.foodplaner.rebo;

import android.content.Context;


import com.example.foodplaner.Model.User;

import com.example.foodplaner.Network.CallBack.CategoriesCallBack;
import com.example.foodplaner.Network.CallBack.ChipListCallback;
import com.example.foodplaner.Network.CallBack.FilteredCallBack;
import com.example.foodplaner.Network.CallBack.RandomMealCallBack;

import com.example.foodplaner.Network.NetworkDelegate;
import com.example.foodplaner.Network.RemoteDataInterface;
import com.example.foodplaner.Model.PlanMeal;
import com.example.foodplaner.PlanDB.PlanConcreteLocalSource;
import com.example.foodplaner.database.MealLocalSource;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class Repository implements RepositoryInterface{
    Context context;
    RemoteDataInterface remote;
    PlanConcreteLocalSource planConcreteLocalSource;
    MealLocalSource mealLocalSource;
    private static Repository repo=null;

    public Repository(RemoteDataInterface remotesource, Context context) {
        remote=remotesource;
        this.context=context;
        planConcreteLocalSource=PlanConcreteLocalSource.getInstance(context);
        mealLocalSource=MealLocalSource.getInstance(context);
    }

    public static Repository getInstance(RemoteDataInterface remotesource,Context context){
        if (repo==null){
            repo=new Repository(remotesource,context);
        }
        return repo;

    }


    @Override
    public Completable insertMeal(PlanMeal meal) {
        return planConcreteLocalSource.insert(meal);
    }

    @Override
    public void deleteMeal(PlanMeal meal) {
         planConcreteLocalSource.delete(meal);

    }

    @Override
    public void getAllMeals(NetworkDelegate networkDelegate, String l) {
            remote.getData(networkDelegate,l);
    }

    public void addMealToFav(User user){
        mealLocalSource.insertMeal(user);
    }


    public void getRandomMeal(RandomMealCallBack callBack){
        remote.getRandomMeal(callBack);
    }
    public void geCategories(CategoriesCallBack callBack){
        remote.getCategories(callBack);
    }


    public Single<List<PlanMeal>> getStoredMeals() {
        return planConcreteLocalSource.getAllStoredMeals();
    }

    public void getCategoryList(ChipListCallback callback) {
        remote.getCategoriesList(callback);
    }

    public void getAreaList(ChipListCallback callback) {
        remote.getAreaList(callback);
    }

    public void getIngredientList(ChipListCallback callback) {
        remote.getIngredientList(callback);

    }

    public void getCategoryFiltered(String s, FilteredCallBack callBack) {
        remote.getCategoryFiltered( s, callBack);
    }

    public void getAreaFiltered(String s, FilteredCallBack callBack) {
        remote.getAreaFiltered( s,  callBack);
    }

    public void getIngredientFiltered(String s, FilteredCallBack callBack) {
        remote.getIngredientFiltered( s,  callBack);
    }

    public void getGetMeal(String idMeal,FilteredCallBack callBack) {
        remote.getGetMeal(idMeal,callBack);
    }
}
