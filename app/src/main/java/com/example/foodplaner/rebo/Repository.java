package com.example.foodplaner.rebo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Network.CallBack.CategoriesCallBack;
import com.example.foodplaner.Network.CallBack.RandomMealCallBack;
import com.example.foodplaner.Network.NetworkDelegate;
import com.example.foodplaner.Network.RemoteDataInterface;

import java.util.List;

public class Repository implements RepositoryInterface{
    Context context;
    RemoteDataInterface remote;
    private static Repository repo=null;

    public Repository(RemoteDataInterface remotesource, Context context) {
        remote=remotesource;
        this.context=context;
    }

    public static Repository getInstance(RemoteDataInterface remotesource,Context context){
        if (repo==null){
            repo=new Repository(remotesource,context);
        }
        return repo;

    }


    @Override
    public void insertMeal(Meal meal) {

    }

    @Override
    public void deleteMeal(Meal meal) {

    }

    @Override
    public void getAllMeals(NetworkDelegate networkDelegate) {
            remote.getData(networkDelegate);
    }

    public void getRandomMeal(RandomMealCallBack callBack){
        remote.getRandomMeal(callBack);
    }
    public void geCategories(CategoriesCallBack callBack){
        remote.getCategories(callBack);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return null;
    }
}
