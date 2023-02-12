package com.example.foodplaner.Network;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplaner.Model.Meal;

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

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return null;
    }
}
